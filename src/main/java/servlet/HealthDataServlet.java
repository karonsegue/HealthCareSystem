package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HealthDataDAO;
import entity.HealthData;

@WebServlet("/HealthDataServlet")
public class HealthDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Session取得
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String userId = (String) session.getAttribute("userId");

        // 入力値取得
        String bodyTemperatureStr = request.getParameter("bodyTemperature");

        String sleepHourStr = request.getParameter("sleepHour");

        String sleepMinuteStr = request.getParameter("sleepMinute");

        // 空欄チェック
        if (bodyTemperatureStr == null
                || bodyTemperatureStr.isEmpty()
                || sleepHourStr == null
                || sleepHourStr.isEmpty()
                || sleepMinuteStr == null
                || sleepMinuteStr.isEmpty()) {

            request.setAttribute("errorMessage","値が未入力です");

            request.getRequestDispatcher("/health_data.jsp").forward(request, response);

            return;
        }

        double bodyTemperature;
        int sleepHour;
        int sleepMinute;

        try {

            bodyTemperature = Double.parseDouble(bodyTemperatureStr);

            sleepHour = Integer.parseInt(sleepHourStr);

            sleepMinute = Integer.parseInt(sleepMinuteStr);

        } catch (NumberFormatException e) {

            request.setAttribute("errorMessage","数値を正しく入力してください");

            request.getRequestDispatcher("/health_data.jsp").forward(request, response);

            return;
        }

        // 睡眠時間を分へ変換
        int sleepTime = (sleepHour * 60) + sleepMinute;

        // 今日の日付
        LocalDate today = LocalDate.now();

        Date sqlDate = Date.valueOf(today);

        HealthDataDAO dao = new HealthDataDAO();

        // 今日の記録確認
        if (dao.existsTodayRecord(userId,sqlDate)) {

            session.setAttribute("errorMessage","本日は既に記録済みです");

            response.sendRedirect("HealthDataListServlet");

            return;
        }

        // status判定
        String status = "NORMAL";

        // 体温判定
        if (bodyTemperature < 35.0 || bodyTemperature >= 39.0) {

            status = "DANGER";

        } else if (bodyTemperature < 36.5 || bodyTemperature >= 37.5) {

            status = "WARNING";
        }

        // 睡眠判定
        if (sleepTime < 300 || sleepTime >= 600) {

            status = "DANGER";

        } else if (status.equals("NORMAL") && (sleepTime < 360 || sleepTime >= 480)) {

            status = "WARNING";
        }

        // Entity作成
        HealthData healthData = new HealthData();

        healthData.setUserId(userId);
        healthData.setBodyTemperature(bodyTemperature);
        healthData.setSleepTime(sleepTime);
        healthData.setRecordDate(today);
        healthData.setEditCount(0);
        healthData.setStatus(status);

        // 登録
        boolean result = dao.insertHealthData(healthData);

        if (result) {

            response.sendRedirect("HealthDataListServlet");

        } else {

            request.setAttribute("errorMessage","記録の登録に失敗しました");

            request.getRequestDispatcher("/health_data.jsp").forward(request, response);
        }
    }
}
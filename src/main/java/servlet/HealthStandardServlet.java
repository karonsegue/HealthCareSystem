package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HealthStandardDAO;
import entity.HealthStandard;

@WebServlet("/HealthStandardServlet")
public class HealthStandardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Session取得
        HttpSession session =request.getSession();

        String userId =(String) session.getAttribute("userId");

        // 入力値取得
        String bodyTemperatureStr =request.getParameter("averageBodyTemperature");

        String sleepHourStr = request.getParameter("sleepHour");

        String sleepMinuteStr = request.getParameter("sleepMinute");

        // 空欄チェック
        if (bodyTemperatureStr == null || bodyTemperatureStr.isEmpty()) {

            request.setAttribute("errorMessage", "平均体温を入力してください");

            request.getRequestDispatcher("/standard_setting.jsp").forward(request, response);

            return;
        }

        if (sleepHourStr == null || sleepHourStr.isEmpty()
                || sleepMinuteStr == null || sleepMinuteStr.isEmpty()) {

            request.setAttribute("errorMessage","平均睡眠時間を入力してください");

            request.getRequestDispatcher("/standard_setting.jsp").forward(request, response);

            return;
        }

        double averageBodyTemperature;
        int averageSleepTime;

        try {
            averageBodyTemperature =Double.parseDouble(bodyTemperatureStr);

            int sleepHour = Integer.parseInt(sleepHourStr);

            	int sleepMinute = Integer.parseInt(sleepMinuteStr);

            	averageSleepTime = sleepHour * 60 + sleepMinute;

        } catch (NumberFormatException e) {

            request.setAttribute("errorMessage","数値を入力してください");

            request.getRequestDispatcher("/health_standard.jsp").forward(request, response);

            return;
        }

        // 範囲チェック
        if (averageBodyTemperature < 35.0 || averageBodyTemperature > 42.0) {

            request.setAttribute("errorMessage","体温は35.0～42.0の範囲で入力してください");

            request.getRequestDispatcher("/standard_setting.jsp").forward(request, response);

            return;
        }

        if (averageSleepTime < 0 || averageSleepTime > 1440) {

            request.setAttribute("errorMessage","睡眠時間を正しく入力してください");

            request.getRequestDispatcher("/standard_setting.jsp").forward(request, response);

            return;
        }

        // Entity作成
        HealthStandard healthStandard = new HealthStandard();

        healthStandard.setUserId(userId);
        healthStandard.setAverageBodyTemperature(averageBodyTemperature);
        healthStandard.setAverageSleepTime(averageSleepTime);

        // DAO実行
        HealthStandardDAO dao = new HealthStandardDAO();

        boolean result = dao.insertHealthStandard(healthStandard);

        if (result) {

            response.sendRedirect("health_list.jsp");

        } else {

            request.setAttribute("errorMessage","基準値の登録に失敗しました");

            request.getRequestDispatcher("/standard_setting.jsp").forward(request, response);
        }
    }
}

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HealthDataDAO;
import entity.HealthData;

@WebServlet("/EditRecordServlet")
public class EditRecordServlet extends HttpServlet {

    // 編集画面表示
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int recordId = Integer.parseInt(request.getParameter("recordId"));

        HealthDataDAO dao = new HealthDataDAO();

        HealthData healthData = dao.getHealthDataById(recordId);

        if (healthData == null) {

            request.setAttribute("errorMessage","対象データが存在しません");

            request.getRequestDispatcher("/health_list.jsp").forward(request, response);

            return;
        }

        // 編集回数チェック
        if (healthData.getEditCount() >= 2) {

        	session.setAttribute("errorMessage","編集回数が上限に達しています");

        	response.sendRedirect("HealthDataListServlet");

            return;
        }

        request.setAttribute("healthData",healthData);

        request.getRequestDispatcher("/edit_record.jsp").forward(request, response);
    }

    // 更新処理
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        String bodyTemperatureStr = request.getParameter("bodyTemperature");

        String sleepHourStr = request.getParameter("sleepHour");

        	String sleepMinuteStr = request.getParameter("sleepMinute");

        String recordIdStr = request.getParameter("recordId");

        // 空欄チェック
        if (bodyTemperatureStr == null 
        		|| bodyTemperatureStr.isEmpty() 
        		|| sleepHourStr == null
        		|| sleepHourStr.isEmpty()
        		|| sleepMinuteStr == null
        		|| sleepMinuteStr.isEmpty()) {

            request.setAttribute("errorMessage","値が未入力です");

            request.getRequestDispatcher("/edit_record.jsp").forward(request, response);

            return;
        }

        int recordId = Integer.parseInt(recordIdStr);

        double bodyTemperature = Double.parseDouble(
                        bodyTemperatureStr);

        int sleepHour = Integer.parseInt(sleepHourStr);

        	int sleepMinute = Integer.parseInt(sleepMinuteStr);

        	int sleepTime = sleepHour * 60 + sleepMinute;

        HealthDataDAO dao = new HealthDataDAO();

        HealthData oldData = dao.getHealthDataById(recordId);

        if (oldData == null) {

            request.setAttribute("errorMessage","対象データが存在しません");

            request.getRequestDispatcher("/health_list.jsp").forward(request, response);

            return;
        }

        // 状態判定
        String status = "NORMAL";

        boolean danger = false;
        boolean warning = false;

        // 体温判定
        if (bodyTemperature < 35.0 || bodyTemperature >= 39.0) {

            danger = true;

        } else if (bodyTemperature < 36.5 || bodyTemperature >= 37.5) {

            warning = true;
        }

        // 睡眠判定
        if (sleepTime < 300 || sleepTime >= 600) {

            danger = true;

        } else if (sleepTime < 360 || sleepTime >= 480) {

            warning = true;
        }

        if (danger) {

            status = "DANGER";

        } else if (warning) {

            status = "WARNING";
        }

        HealthData healthData = new HealthData();

        healthData.setRecordId(recordId);

        healthData.setUserId(oldData.getUserId());

        healthData.setBodyTemperature(bodyTemperature);

        healthData.setSleepTime(sleepTime);

        healthData.setStatus(status);

        healthData.setRecordDate(oldData.getRecordDate());

        healthData.setEditCount(oldData.getEditCount() + 1);

        boolean result = dao.updateHealthData(healthData);

        if (result) {

            response.sendRedirect("HealthDataListServlet");

        } else {

            request.setAttribute("errorMessage","更新に失敗しました");

            request.getRequestDispatcher("/edit_record.jsp").forward(request, response);
        }
    }
}
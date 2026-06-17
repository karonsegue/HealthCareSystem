package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HealthDataDAO;
import entity.HealthData;

@WebServlet("/HealthDataListServlet")
public class HealthDataListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Session取得
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        // userId取得
        String userId = (String) session.getAttribute("userId");

        // DAO生成
        HealthDataDAO dao = new HealthDataDAO();

        // 一覧取得
        ArrayList<HealthData> healthDataList = dao.getHealthDataList(userId);

        // requestへ保存
        request.setAttribute("healthDataList",healthDataList);
        
        String errorMessage =
                (String)session.getAttribute("errorMessage");

        if(errorMessage != null){

            request.setAttribute("errorMessage",errorMessage);

            session.removeAttribute("errorMessage");
        }

        // JSPへ遷移
        request.getRequestDispatcher("/health_list.jsp").forward(request, response);
    }
}
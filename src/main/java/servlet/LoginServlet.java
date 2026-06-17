package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PatientDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値取得
        String userId = request.getParameter("userId");

        String password = request.getParameter("password");

        // 空欄チェック
        if (userId == null || userId.isEmpty()) {

            request.setAttribute("errorMessage","IDを入力してください");

            request.getRequestDispatcher("/login.jsp").forward(request, response);

            return;
        }

        if (password == null || password.isEmpty()) {

            request.setAttribute("errorMessage","パスワードを入力してください");

            request.getRequestDispatcher("/login.jsp").forward(request, response);

            return;
        }

        PatientDAO patientDAO = new PatientDAO();

        // ログイン認証
        boolean result = patientDAO.login(userId,password);

        if (result) {

            // Session作成
            HttpSession session = request.getSession();

            // userId保存
            session.setAttribute("userId",userId);

            response.sendRedirect("HealthDataListServlet");

        } else {

            request.setAttribute("errorMessage","IDまたはパスワードが正しくありません");

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
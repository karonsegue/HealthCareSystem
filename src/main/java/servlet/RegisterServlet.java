package servlet;

import java.io.IOException;

import entity.Patient;
import dao.PatientDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値取得
        String userId =
                request.getParameter("userId");

        String password =
                request.getParameter("password");

        String userPhoneNumber =
                request.getParameter("userPhoneNumber");

        String familyPhoneNumber =
                request.getParameter("familyPhoneNumber");

        // 空欄チェック
        if (userId == null || userId.isEmpty()) {

            request.setAttribute("errorMessage","IDを入力してください");

            request.getRequestDispatcher("/register.jsp").forward(request, response);

            return;
        }

        if (password == null || password.isEmpty()) {

            request.setAttribute("errorMessage","パスワードを入力してください");

            request.getRequestDispatcher("/register.jsp").forward(request, response);

            return;
        }

        if (userPhoneNumber == null || userPhoneNumber.isEmpty()) {

            request.setAttribute("errorMessage","電話番号を入力してください");

            request.getRequestDispatcher("/register.jsp").forward(request, response);

            return;
        }

        PatientDAO patientDAO =new PatientDAO();

        // ID重複チェック
        if (patientDAO.existsUserId(userId)) {

            request.setAttribute("errorMessage","このIDは既に使用されています");

            request.getRequestDispatcher("/register.jsp").forward(request, response);

            return;
        }

        // Patientオブジェクト作成
        Patient patient = new Patient();

        patient.setUserId(userId);
        patient.setPassword(password);
        patient.setUserPhoneNumber(userPhoneNumber);
        patient.setFamilyPhoneNumber(familyPhoneNumber);

        // 登録処理
        boolean result =patientDAO.insertPatient(patient);

        if (result) {

            HttpSession session =request.getSession();

            session.setAttribute("userId",userId);

            response.sendRedirect("health_standard.jsp");

        } else {

            request.setAttribute("errorMessage","エラーが発生しました。もう一度お試しください。");

            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
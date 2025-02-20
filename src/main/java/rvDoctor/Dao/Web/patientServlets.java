package rvDoctor.Dao.Web;

import rvDoctor.Dao.model.patient;
import rvDoctor.dao.patientDao;
import rvDoctor.model.patient;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class PatientServlets extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;
    private patientDao patientDao;

    public void init() {
        patientDao = new patientDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertPatient(request, response);
                    break;
                case "/update":
                    updatePatient(request, response);
                    break;
                case "/delete":
                    deletePatient(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                default:
                    listPatient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<patient> listPatient = patientDao.selectAllPatients();
        request.setAttribute("listPatient", listPatient);
        request.getRequestDispatcher("Patient-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patient existingPatient = patientDao.selectPatient(id);
        request.setAttribute("patient", existingPatient);
        request.getRequestDispatcher("Patient-form.jsp").forward(request, response);
    }

    private void insertPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        if (username == null || username.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                tel == null || tel.trim().isEmpty()) {
            response.getWriter().write("Invalid input data!");
            return;
        }

        patient newPatient = new patient(username, email, tel);
        patientDao.insertPatient(newPatient);
        response.sendRedirect("list");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        patient patient = new patient(id, username, email, tel);
        patientDao.updatePatient(patient);
        response.sendRedirect("list");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientDao.deletePatient(id);
        response.sendRedirect("list");
    }
}

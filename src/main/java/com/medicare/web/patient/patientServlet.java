package com.medicare.web.patient;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.medicare.model.patientModel;
import com.medicare.dao.patientDao;
@WebServlet("/medi-care/*")
public class patientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private patientDao patientDao;

    public void init() {
        patientDao = new patientDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        try {
            switch (action) {
                case "/medi-care/new":
                    showNewForm(request, response);
                    break;
                case "/medi-care/insert":
                    insertPatient(request, response);
                    break;
                case "/medi-care/update":
                    updatePatient(request, response);
                    break;
                case "/medi-care/delete":
                    deletePatient(request, response);
                    break;
                case "/medi-care/edit":
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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientModel existingPatient = patientDao.selectPatient(id);
        request.setAttribute("patient", existingPatient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/patient-form.jsp");
        dispatcher.forward(request, response);
    }



    private void listPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<patientModel> listPatient = patientDao.selectAllPatients();
        request.setAttribute("listPatient", listPatient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/patient-form.jsp");
        dispatcher.forward(request, response);
    }


    private void insertPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        patientModel newPatient = new patientModel(username, email, tel);
        patientDao.insertPatient(newPatient);

        response.sendRedirect("medi-care/home");
    }


    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        patientModel updatedPatient = new patientModel(id, username, email, tel);
        patientDao.updatePatient(updatedPatient);

        response.sendRedirect("medi-care/home");
    }


    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientDao.deletePatient(id);
        response.sendRedirect("medi-care/home");
    }
}

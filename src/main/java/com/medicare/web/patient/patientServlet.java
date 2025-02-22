package com.medicare.web.patient;

import com.medicare.dao.patientDao;
import com.medicare.model.patientModel;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class patientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private patientDao patientDao;

    public void init() {
        patientDao = new patientDao();
    }

    @WebServlet("/index")
    public class IndexServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<patientModel> patients = null; // On suppose que PatientDAO est la classe qui gère les opérations sur la base de données
            try {
                patients = patientDao.getAllPatients();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("patients", patients);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);


            try {
                String action = request.getParameter("action");
                if (action == null) {
                    listPatients(request, response);
                } else {
                    switch (action) {
                        case "/medi-care/new":
                            showNewForm(request, response);
                            break;
                        case "/medi-care/edit":
                            showEditForm(request, response);
                            break;
                        case "/medi-care/delete":
                            deletePatient(request, response);
                            break;
                        default:
                            listPatients(request, response);
                            break;
                    }
                }
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("insert".equals(action)) {
                insertPatient(request, response);
            } else if ("update".equals(action)) {
                updatePatient(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<patientModel> listPatients = patientDao.getAllPatients();
        request.setAttribute("listPatient", listPatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientModel existingPatient = patientDao.getPatientById(id);
        request.setAttribute("patient", existingPatient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        patientModel newPatient = new patientModel(username, email, tel);
        patientDao.insertPatient(newPatient);
        response.sendRedirect("index");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");

        patientModel updatedPatient = new patientModel(id, username, email, tel);
        patientDao.updatePatient(updatedPatient);
        response.sendRedirect("index");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientDao.deletePatient(id);
        response.sendRedirect("index");
    }
}

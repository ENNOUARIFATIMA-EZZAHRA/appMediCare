package com.medicare.web.patient;

import com.medicare.dao.rendezVousDao;
import com.medicare.model.rendezVousModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/rendezVous")
public class rendezVousServlets extends HttpServlet {
    private rendezVousDao rendezVousDao;

    public void init() {
        rendezVousDao = new rendezVousDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/insert":
                    insertRendezVous(request, response);
                    break;
                case "/update":
                    updateRendezVous(request, response);
                    break;
                case "/delete":
                    deleteRendezVous(request, response);
                    break;
                default:
                    listRendezVous(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            listRendezVous(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void listRendezVous(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<rendezVousModel> listRendezVous = rendezVousDao.getAllRendezVous();
        request.setAttribute("listRendezVous", listRendezVous);
        request.getRequestDispatcher("rendezvous-list.jsp").forward(request, response);
    }

    private void insertRendezVous(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String rendezVousNom = request.getParameter("rendezVousNom");
        LocalDateTime rendezVousDate = LocalDateTime.parse(request.getParameter("rendezVousDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String heure = request.getParameter("heure");
        String motif = request.getParameter("motif");

        rendezVousModel newRendezVous = new rendezVousModel(0, patientId, doctorId, rendezVousNom, rendezVousDate, heure, motif);
        rendezVousDao.insertRendezVous(newRendezVous);
        response.sendRedirect("rendezVous");
    }

    private void updateRendezVous(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String rendezVousNom = request.getParameter("rendezVousNom");
        LocalDateTime rendezVousDate = LocalDateTime.parse(request.getParameter("rendezVousDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        String heure = request.getParameter("heure");
        String motif = request.getParameter("motif");

        rendezVousModel updatedRendezVous = new rendezVousModel(id, patientId, doctorId, rendezVousNom, rendezVousDate, heure, motif);
        rendezVousDao.updateRendezVous(updatedRendezVous);
        response.sendRedirect("rendezVous");
    }

    private void deleteRendezVous(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        rendezVousDao.deleteRendezVous(id);
        response.sendRedirect("rendezVous");
    }
}

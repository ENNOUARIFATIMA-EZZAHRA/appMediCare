<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.medicare.model.docteur, com.medicare.dao.DocteurDAO, com.medicare.model.Appointment"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Rendez-vous - Médecin</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Mes Rendez-vous Médicaux</h2>

        <div class="my-4">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient</th>
                        <th>Date</th>
                        <th>Motif</th>
                        <th>Statut</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Afficher les rendez-vous du médecin -->
                    <c:forEach var="appointment" items="${appointments}">
                        <tr>
                            <td>${appointment.appointmentId}</td>
                            <td>${appointment.patientUsername}</td>
                            <td>${appointment.appointmentDate}</td>
                            <td>${appointment.status}</td>
                            <td>
                                <a href="rescheduleAppointment.jsp?id=${appointment.appointmentId}" class="btn btn-warning btn-sm">Reporter</a>
                                <a href="cancelAppointment.jsp?id=${appointment.appointmentId}" class="btn btn-danger btn-sm">Annuler</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- JavaScript Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>

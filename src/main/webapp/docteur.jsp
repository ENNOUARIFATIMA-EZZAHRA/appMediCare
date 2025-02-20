<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> List patient</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<header>
           <nav class="bg-red-200 px-4 text-white flex justify-between pt-8  pb-0">
                    <div>
                    <a href="rendez vous.jsp" class=" bg-blue-500 text-white px-2 py-2 rounded hover:bg-blue-600  ">Prendre un Rendez-vous</a><br><br><br><br>
                    </div>
                    </header>
    <div class="container mx-auto p-6">


            <h2 class="text-3xl font-bold mb-4">Patient List</h2>


            <table class="min-w-full bg-white shadow-md rounded-lg mt-6">
                <thead class="bg-gray-200">
                    <tr>
                        <th class="px-6 py-3 text-left">ID</th>
                        <th class="px-6 py-3 text-left">Nom</th>
                        <th class="px-6 py-3 text-left">prenom</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="patient" items="${listPatient}">
                        <tr class="border-t">
                            <td class="px-6 py-3">${patient.id}</td>
                            <td class="px-6 py-3">${patient.nom}</td>
                            <td class="px-6 py-3">${patient.prenom}</td>

                            <td class="px-6 py-3">
                                <form action="CancelAppointmentServlet" method="post">
                                    <input type="hidden" name="rendez_vous" value="${patient.id}" />
                                    <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Annuler</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Patient List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-6">



    <div class="container mx-auto p-6">
        <div class="header flex justify-start">
            <a href="#" class="text-blue-500 hover:underline">Home</a>

        </div>
        <div class="header flex justify-start">
            <a href="#" class="text-blue-500 hover:underline">Login</a>
        </div>
        <nav class="bg-red-500 p-4 text-white flex justify-between">

            <ul class="flex space-x-4">
                <li><a href="<%=request.getContextPath()%>/list" class="hover:underline">List Patient</a></li><br><br><br>
            </ul>
        </nav>
        <br>
        <div class="container mx-auto w-1/2">
            <div class="bg-white shadow-md rounded-lg p-6">
                <div class="mb-4">
                    <c:if test="${patient != null}">
                        <form action="update" method="post">
                    </c:if>
                    <c:if test="${patient == null}">
                        <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h2 class="text-2xl font-bold mb-4">
                            <c:if test="${patient != null}">
                                Edit Patient
                            </c:if>
                            <c:if test="${patient == null}">
                                Ajouter nouveaux rendez-vous
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${patient != null}">
                        <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                    </c:if>
                    <div class="mb-4">
                        <label class="block text-lg font-medium">Nom</label>
                        <input type="text" value="<c:out value='${patient.nom}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="nom" required>
                    </div>
                    <div class="mb-4">
                        <label class="block text-lg font-medium">Date</label>
                        <input type="date" value="<c:out value='${patient.date}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="date">
                    </div>
                    <div class="mb-4">
                        <label class="block text-lg font-medium">Heure</label>
                        <input type="time" value="<c:out value='${patient.heure}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="heure">
                    </div>
                    <div class="mb-4">
                        <label class="block text-lg font-medium">Motif</label>
                        <input type="text" value="<c:out value='${patient.motif}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="motif">
                    </div>
                    <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600">Save</button>
                </form>
            </div>
        </div>
    </div>
    <div id="popup" class="fixed inset-0 flex items-center justify-center bg-gray-500 bg-opacity-75 hidden">
        <div class="bg-white p-6 rounded shadow-md text-center">
            <img src="tick.png" alt="Success">
            <h2 class="text-2xl font-bold mb-4">Thank you</h2>
            <p>Your reservation has been confirmed.</p>
            <button onclick="closePopup()" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">Ok</button>
        </div>
    </div>
    <script>
        let popup = document.getElementById("popup");
        function openPopup() {
            popup.classList.remove("hidden");
        }
        function closePopup() {
            popup.classList.add("hidden");
        }
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Liste des Rendez-vous</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <header>
        <nav class="bg-red-200 px-4 text-white flex justify-between pt-8 pb-0">
            <div class="header flex justify-start justify-around items-center">
                <a href="#" class="text-blue-500 hover:underline">Home</a>
            </div>
        </nav>
    </header>
    <br><br><br><br>
    <div class="container mx-auto w-1/2">
        <div class="bg-white shadow-md rounded-lg p-6">
            <h2 class="text-2xl font-bold mb-4">Liste des Rendez-vous</h2>
            <table class="w-full table-auto">
                <thead>
                    <tr>
                        <th class="px-4 py-2">ID</th>
                        <th class="px-4 py-2">Nom</th>
                        <th class="px-4 py-2">Date</th>
                        <th class="px-4 py-2">Heure</th>
                        <th class="px-4 py-2">Motif</th>
                        <th class="px-4 py-2">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="rendezVous" items="${listRendezVous}">
                        <tr>
                            <td class="border px-4 py-2">${rendezVous.id}</td>
                            <td class="border px-4 py-2">${rendezVous.rendezVousNom}</td>
                            <td class="border px-4 py-2">
                                <fmt:formatDate value="${rendezVous.rendezVousDate}" pattern="yyyy-MM-dd HH:mm"/>
                            </td>
                            <td class="border px-4 py-2">${rendezVous.heure}</td>
                            <td class="border px-4 py-2">${rendezVous.motif}</td>
                            <td class="border px-4 py-2">
                                <a href="update?id=${rendezVous.id}" class="bg-yellow-500 text-white px-3 py-1 rounded">Edit</a>
                                <a href="delete?id=${rendezVous.id}" class="bg-red-500 text-white px-3 py-1 rounded">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>

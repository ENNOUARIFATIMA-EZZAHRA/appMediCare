<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Rendez-vous Form</title>
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
        <form action="<c:choose>
                    <c:when test="${rendezVous != null}">
                        update
                    </c:when>
                    <c:otherwise>
                        insert
                    </c:otherwise>
                </c:choose>" method="post">

            <caption>
                <h2 class="text-2xl font-bold mb-4">
                    <c:if test="${rendezVous != null}">Edit Rendez-vous</c:if>
                    <c:if test="${rendezVous == null}">Ajouter un Nouveau Rendez-vous</c:if>
                </h2>
            </caption>

            <c:if test="${rendezVous != null}">
                <input type="hidden" name="id" value="${rendezVous.id}" />
            </c:if>

            <div class="mb-4">
                <label class="block text-lg font-medium">Nom</label>
                <input type="text" value="<c:out value='${rendezVous.rendezVousNom}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="rendezVousNom" required />
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Date</label>
                <input type="datetime-local" value="<c:out value='${rendezVous.rendezVousDate}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="rendezVousDate" required />
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Heure</label>
                <input type="time" value="<c:out value='${rendezVous.heure}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="heure" required />
            </div>

            <div class="mb-4">
                <label class="block text-lg font-medium">Motif</label>
                <input type="text" value="<c:out value='${rendezVous.motif}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="motif" required />
            </div>

            <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600">Save</button>
        </form>
    </div>
</div>

</body>
</html>

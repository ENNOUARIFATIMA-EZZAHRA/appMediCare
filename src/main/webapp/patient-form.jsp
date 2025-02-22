<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<html>

<head>
    <title> Management Application de cabinet </title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100">

    <header>
        <nav class="bg-red-500 p-4 text-white flex justify-between">
            <div>
                <a href="<%=request.getContextPath()%>/list" class="hover:underline"> LIST PATIENT</a>
            </div>

            <ul class="flex space-x-4">
                <li><a href="<%=request.getContextPath()%>/home" class="hover:underline"> Home</a></li>
            </ul>
        </nav>
    </header>
    <br>
    <div class="container mx-auto w-1/2">
        <div class="bg-white shadow-md rounded-lg p-6">
            <div class="mb-4">
               <form action="<%= request.getContextPath() %>/patient?action=insert" method="post">

                    <caption>
                        <h2 class="text-2xl font-bold mb-4">
                            <c:if test="${patient != null}">Edit Patient</c:if>
                            <c:if test="${patient == null}">Ajouter Nouveau Patient</c:if>
                        </h2>
                    </caption>

                    <c:if test="${patient != null}">
                        <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                    </c:if>

                    <div class="mb-4">
                        <label class="block text-lg font-medium">Nom</label>
                        <input type="text" value="<c:out value='${patient.username}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="username" required>
                    </div>

                    <div class="mb-4">
                        <label class="block text-lg font-medium">Email</label>
                        <input type="email" value="<c:out value='${patient.email}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="email" required>
                    </div>

                    <div class="mb-4">
                        <label class="block text-lg font-medium">Téléphone</label>
                        <input type="text" value="<c:out value='${patient.tel}' />" class="mt-2 p-2 w-full border border-gray-300 rounded" name="tel" required>
                    </div>

                    <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded hover:bg-green-600">Ajouter</button>
                    </form>
            </div>
        </div>
    </div>

</body>

</html>

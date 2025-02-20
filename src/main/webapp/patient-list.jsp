<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title> List Rendez-vous</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-6">
        <div class="header flex justify-start" href="#">Home</div>
        <div class="header flex justify-start" href="#">Login</div>
    <div class="container mx-auto p-6">
        <h2 class="text-3xl font-bold mb-4">LIST PATIENT</h2>
        <a href="<%=request.getContextPath()%>/new" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Add New Product</a>

        <table class="min-w-full bg-white shadow-md rounded-lg mt-5">
            <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left">ID</th>
                    <th class="px-6 py-3 text-left">Name</th>
                    <th class="px-6 py-3 text-left">Email</th>
                    <th class="px-6 py-3 text-left">Tel</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="patient" items="${listPatient}">
                    <tr class="border-t">
                        <td class="px-6 py-3">${patient.id}</td>
                        <td class="px-6 py-3">${patient.username}</td>
                        <td class="px-6 py-3">${patient.email}</td>
                        <td class="px-6 py-3">${patient.tel}</td>

                        <td class="px-6 py-3">
                            <a href="edit?id=${patient.id}" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">Edit</a>
                            <a href="delete?id=${patient.id}" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 ml-2">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>

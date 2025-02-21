<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<html>
<head>
    <title>Confirmation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

    <div class="container mx-auto w-1/2 mt-10">
        <div class="bg-white shadow-md rounded-lg p-6 text-center">
            <h2 class="text-2xl font-bold text-green-600 mb-4">Succès</h2>
            <p class="text-lg">${message}</p>
            <a href="<%=request.getContextPath()%>/list" class="mt-4 inline-block bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">Retour à la liste</a>
        </div>
    </div>

</body>
</html>

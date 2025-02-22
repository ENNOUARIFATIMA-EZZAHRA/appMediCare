<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Liste des Patients</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

    <header>
        <nav class="bg-red-500 p-4 text-white flex justify-between">
            <ul class="flex space-x-4">
                <li><a href="<%=request.getContextPath()%>/home" class="hover:underline">Home</a></li>
            </ul>
        </nav>
    </header>

    <div class="container mx-auto p-6">
<a href="<%= request.getContextPath() %>/patient?action=new" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"> Ajouter Nouveau Patient
</a>

        <br> <br>
        <h2 class="text-3xl font-bold mb-4">Liste des Patients</h2>

        <table class="min-w-full bg-white shadow-md rounded-lg mt-5">
            <thead class="bg-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left">ID</th>
                    <th class="px-6 py-3 text-left">Nom</th>
                    <th class="px-6 py-3 text-left">Email</th>
                    <th class="px-6 py-3 text-left">Téléphone</th>
                    <th class="px-6 py-3 text-left">Actions</th>
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
                            <a href="edit?id=${patient.id}" class="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">Modifier</a>
                            <a href="delete?id=${patient.id}" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 ml-2">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>

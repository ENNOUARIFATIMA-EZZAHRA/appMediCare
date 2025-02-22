package com.medicare.dao;

import com.medicare.model.docteur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocteurDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/vrDoctor_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id = ?";

    // Méthode pour obtenir tous les docteurs
    public List<docteur> getAllDoctors() throws SQLException {
        List<docteur> doctors = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_DOCTORS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String prenom = resultSet.getString("prenom");

                doctors.add(new docteur(id, name, prenom));
            }
        }
        return doctors;
    }

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found!", e);
        }
    }
}

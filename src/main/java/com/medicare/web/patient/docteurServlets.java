package com.medicare.web.patient;


import com.medicare.model.docteur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class docteurServlets {

    private String jdbcURL = "jdbc:mysql://localhost:3306/vrDoctor_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctor (name, prenom) VALUES (?, ?)";
    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctor";
    private static final String SELECT_DOCTOR_BY_ID = "SELECT * FROM doctor WHERE id = ?";
    private static final String UPDATE_DOCTOR_SQL = "UPDATE doctor SET name = ?, prenom = ? WHERE id = ?";
    private static final String DELETE_DOCTOR_SQL = "DELETE FROM doctor WHERE id = ?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found!", e);
        }
    }

    public void insertDoctor(docteur doctor) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR_SQL)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getPrenom());
            preparedStatement.executeUpdate();
        }
    }

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

    public docteur getDoctorById(int id) throws SQLException {
        docteur doctor = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DOCTOR_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String prenom = resultSet.getString("prenom");
                doctor = new docteur(id, name, prenom);
            }
        }
        return doctor;
    }

    public void updateDoctor(docteur doctor) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DOCTOR_SQL)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getPrenom());
            statement.setInt(3, doctor.getId());
            statement.executeUpdate();
        }
    }

    public void deleteDoctor(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DOCTOR_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

package com.medicare.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.medicare.model.patientModel;

public class patientDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/vrDoctor_db?useSSL=false&serverTimezone=UTC";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_PATIENT_SQL = "INSERT INTO patient (username, email, tel) VALUES (?, ?, ?)";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM patient";
    private static final String DELETE_PATIENT_SQL = "DELETE FROM patient WHERE id = ?";
    private static final String UPDATE_PATIENT_SQL = "UPDATE patient SET username = ?, email = ?, tel = ? WHERE id = ?";

    // Méthode de connexion
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Méthode pour ajouter un patient
    public void insertPatient(patientModel patient) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_SQL)) {
            preparedStatement.setString(1, patient.getUsername());
            preparedStatement.setString(2, patient.getEmail());
            preparedStatement.setString(3, patient.getTel());
            preparedStatement.executeUpdate();
        }
    }


    public patientModel selectPatient(int id) {
        patientModel patient = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                patient = new patientModel(id, username, email, tel);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }


    public List<patientModel> selectAllPatients() {
        List<patientModel> patients = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PATIENTS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                patients.add(new patientModel(id, username, email, tel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


    public boolean deletePatient(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PATIENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


    public boolean updatePatient(patientModel patient) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PATIENT_SQL)) {
            statement.setString(1, patient.getUsername());
            statement.setString(2, patient.getEmail());
            statement.setString(3, patient.getTel());
            statement.setInt(4, patient.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}

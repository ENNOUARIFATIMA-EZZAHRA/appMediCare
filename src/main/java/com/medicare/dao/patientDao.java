package com.medicare.dao;

import com.medicare.model.patientModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class patientDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/medicare";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_PATIENT_SQL = "INSERT INTO patient (username, email, tel) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_PATIENTS = "SELECT * FROM patient";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patient WHERE id = ?";
    private static final String UPDATE_PATIENT_SQL = "UPDATE patient SET username = ?, email = ?, tel = ? WHERE id = ?";
    private static final String DELETE_PATIENT_SQL = "DELETE FROM patient WHERE id = ?";

    protected Connection getConnection() throws SQLException {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found !", e);
        }
    }


    public void insertPatient(patientModel patient) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_SQL)) {
            preparedStatement.setString(1, patient.getUsername());
            preparedStatement.setString(2, patient.getEmail());
            preparedStatement.setString(3, patient.getTel());
            preparedStatement.executeUpdate();
        }
    }

    public List<patientModel> getAllPatients() throws SQLException {
        List<patientModel> patients = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PATIENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String tel = resultSet.getString("tel");

                patients.add(new patientModel(id, username, email, tel));
            }
        }
        return patients;
    }

    public patientModel getPatientById(int id) throws SQLException {
        patientModel patient = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String tel = resultSet.getString("tel");
                patient = new patientModel(id, username, email, tel);
            }
        }
        return patient;
    }

    public void updatePatient(patientModel patient) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PATIENT_SQL)) {
            statement.setString(1, patient.getUsername());
            statement.setString(2, patient.getEmail());
            statement.setString(3, patient.getTel());
            statement.setInt(4, patient.getId());
            statement.executeUpdate();
        }
    }

    public void deletePatient(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PATIENT_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

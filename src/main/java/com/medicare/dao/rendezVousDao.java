package com.medicare.dao;

import com.medicare.model.rendezVousModel;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class rendezVousDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/vrDoctor_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234";

    private static final String INSERT_APPOINTMENT_SQL = "INSERT INTO rendezVous (patient_id, doctor_id, appointmentName, appointmentDate, heure, motif) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_APPOINTMENTS = "SELECT * FROM rendezVous";
    private static final String SELECT_APPOINTMENT_BY_ID = "SELECT * FROM rendezVous WHERE id = ?";
    private static final String UPDATE_APPOINTMENT_SQL = "UPDATE rendezVous SET patient_id = ?, doctor_id = ?, appointmentName = ?, appointmentDate = ?, heure = ?, motif = ? WHERE id = ?";
    private static final String DELETE_APPOINTMENT_SQL = "DELETE FROM rendezVous WHERE id = ?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver not found!", e);
        }
    }

    public void insertRendezVous(rendezVousModel rendezVous) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_APPOINTMENT_SQL)) {
            preparedStatement.setInt(1, rendezVous.getPatientId());
            preparedStatement.setInt(2, rendezVous.getDoctorId());
            preparedStatement.setString(3, rendezVous.getRendezVousNom());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(rendezVous.getRendezVousDate()));
            preparedStatement.setString(5, rendezVous.getHeure());
            preparedStatement.setString(6, rendezVous.getMotif());
            preparedStatement.executeUpdate();
        }
    }

    public List<rendezVousModel> getAllRendezVous() throws SQLException {
        List<rendezVousModel> rendezVousList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_APPOINTMENTS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                String appointmentName = resultSet.getString("appointmentName");
                LocalDateTime appointmentDate = resultSet.getTimestamp("appointmentDate").toLocalDateTime();
                String heure = resultSet.getString("heure");
                String motif = resultSet.getString("motif");

                rendezVousList.add(new rendezVousModel(id, patientId, doctorId, appointmentName, appointmentDate, heure, motif));
            }
        }
        return rendezVousList;
    }

    public rendezVousModel getRendezVousById(int id) throws SQLException {
        rendezVousModel rendezVous = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                String appointmentName = resultSet.getString("appointmentName");
                LocalDateTime appointmentDate = resultSet.getTimestamp("appointmentDate").toLocalDateTime();
                String heure = resultSet.getString("heure");
                String motif = resultSet.getString("motif");

                rendezVous = new rendezVousModel(id, patientId, doctorId, appointmentName, appointmentDate, heure, motif);
            }
        }
        return rendezVous;
    }

    public void updateRendezVous(rendezVousModel rendezVous) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_SQL)) {
            statement.setInt(1, rendezVous.getPatientId());
            statement.setInt(2, rendezVous.getDoctorId());
            statement.setString(3, rendezVous.getRendezVousNom());
            statement.setTimestamp(4, Timestamp.valueOf(rendezVous.getRendezVousDate()));
            statement.setString(5, rendezVous.getHeure());
            statement.setString(6, rendezVous.getMotif());
            statement.setInt(7, rendezVous.getId());
            statement.executeUpdate();
        }
    }

    public void deleteRendezVous(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT_SQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

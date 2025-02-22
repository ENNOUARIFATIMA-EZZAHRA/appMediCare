package com.medicare.model;

import java.time.LocalDateTime;

public class rendezVousModel {
    private int id;
    private int patientId;
    private int doctorId;
    private String rendezVousNom;
    private LocalDateTime rendezVousDate;
    private String heure;
    private String motif;

    public rendezVousModel(int id, int patientId, int doctorId, String rendezVousNom, LocalDateTime rendezVousDate, String heure, String motif) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.rendezVousNom = rendezVousNom;
        this.rendezVousDate = rendezVousDate;
        this.heure = heure;
        this.motif = motif;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getRendezVousNom() {
        return rendezVousNom;
    }

    public LocalDateTime getRendezVousDate() {
        return rendezVousDate;
    }

    public String getHeure() {
        return heure;
    }

    public String getMotif() {
        return motif;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setRendezVousNom(String rendezVousNom) {
        this.rendezVousNom = rendezVousNom;
    }

    public void setRendezVousDate(LocalDateTime rendezVousDate) {
        this.rendezVousDate = rendezVousDate;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}

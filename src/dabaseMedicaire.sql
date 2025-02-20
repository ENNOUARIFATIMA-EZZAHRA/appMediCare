create database mediCare;
use mediCare;
create table docteur (
docteur_id INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(50),
prenom VARCHAR (50)

);
create table patient (
patient_id INT AUTO_INCREMENT PRIMARY KEY,
username varchar(50),
email VARCHAR (50),
tel VARCHAR (50)
);
CREATE TABLE rendezVous (
    rendezVous_id INT AUTO_INCREMENT PRIMARY KEY,
    docteur_id INT,
    patient_id INT,
    date DATE,
    heure TIME,
    motif VARCHAR(255),
    FOREIGN KEY (docteur_id) REFERENCES docteur(docteur_id),
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id) 
);

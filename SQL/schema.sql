DROP SCHEMA HumanCapital;
CREATE SCHEMA HumanCapital;
USE HumanCapital;

CREATE TABLE Candidate (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  dateOfBirth DATE NOT NULL,
  expectation DOUBLE NOT NULL,
  PRIMARY KEY(id)
);

/* Base employee table. TODO: Refactor */
CREATE TABLE Employee (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  dateOfBirth DATE NOT NULL,
  PRIMARY KEY(id)
);

/* TODO: Make a 'Person' superclass to avoid id collisions */
CREATE TABLE Certificate (
  id INT NOT NULL AUTO_INCREMENT,
  person_id INT NOT NULL,
  type VARCHAR(100) NOT NULL,
  name VARCHAR(200) NOT NULL,
  organization VARCHAR(300) NOT NULL,
  dateAquired DATE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(person_id) REFERENCES Candidate(id)
);

/* TODO: Make a 'Person' superclass to avoid id collisions */
CREATE TABLE PreviousJob (
  id INT NOT NULL AUTO_INCREMENT,
  person_id INT NOT NULL,
  jobTitle VARCHAR(100) NOT NULL,
  jobDescription VARCHAR(3000) NOT NULL,
  salary DOUBLE NOT NULL,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(person_id) REFERENCES Candidate(id)
);
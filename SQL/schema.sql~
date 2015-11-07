DROP SCHEMA HumanCapital;
CREATE SCHEMA HumanCapital;
USE HumanCapital;

CREATE TABLE Person (
  id INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(200) NOT NULL,
  lastName VARCHAR(200) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  professionalTitle VARCHAR(100) NOT NULL,
  dateOfBirth DATE NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Employee (
  id INT NOT NULL,
  jobTitle VARCHAR(100) NOT NULL,
  salary DOUBLE NOT NULL,
  vacationDays INT NOT NULL DEFAULT 0,
  PRIMARY KEY(id),
  FOREIGN KEY(id) REFERENCES Person(id)
);

CREATE TABLE User (
  id INT NOT NULL,
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(id) REFERENCES Employee(id)
);

CREATE TABLE Candidate (
  id INT NOT NULL,
  expectation DOUBLE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(id) REFERENCES Person(id)
);

CREATE TABLE Certificate (
  id INT NOT NULL AUTO_INCREMENT,
  personId INT NOT NULL,
  type VARCHAR(100) NOT NULL,
  name VARCHAR(200) NOT NULL,
  organization VARCHAR(300) NOT NULL,
  dateAquired DATE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(personId) REFERENCES Person(id)
);

CREATE TABLE PreviousJob (
  id INT NOT NULL AUTO_INCREMENT,
  personId INT NOT NULL,
  jobTitle VARCHAR(100) NOT NULL,
  company VARCHAR(100) NOT NULL, 
  jobDescription VARCHAR(3000) NOT NULL,
  salary DOUBLE NOT NULL,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(personId) REFERENCES Person(id)
);

CREATE TABLE Interview (
  id INT NOT NULL AUTO_INCREMENT,
  employeeId INT NOT NULL,
  candidateId INT NOT NULL,
  interviewDate DATE NOT NULL,
  feedback VARCHAR(2000) NOT NULL,
  platform VARCHAR(100) NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(employeeId) REFERENCES Employee(id),
  FOREIGN KEY(candidateId) REFERENCES Candidate(id)
);
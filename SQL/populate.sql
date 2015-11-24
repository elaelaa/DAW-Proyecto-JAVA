-- CANDIDATE CREATION
INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Luis',
	'Lamadrid', 
	'luis@web.com', 
	'777 Ejemplo, NL, MX', 
	'+(52) 827777777',
	'Student',
	CURDATE()
);

-- EMPLOYEE CREATION
INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Andres',
	'Faguette', 
	'andrew@web.com', 
	'777 Ejemplo, NL, MX', 
	'+(52) 827787777',
	'Student',
	CURDATE()
);

INSERT INTO Candidate (id, expectation) 
VALUES ( 1, 14000.00 );
INSERT INTO Employee (id, jobTitle, startDate, salary, vacationDays) 
VALUES ( 2, 'Disque CEO', CURDATE(), 10.0, 7 );

-- CERTIFICATE CREATION
INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	1,
	'Bachelors',
	'Computer Science',
	'Tec de Monterrey',
	CURDATE()
);

INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	2,
	'Bachelors',
	'Computer Science',
	'Tec de Monterrey',
	CURDATE()
);

-- PREVIOUS JOB CREATION
INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	1,
	'Developer',
	'AlphaOne',
	'I got to modify stuff from the software...',
	3000.00,
	CURDATE(),
	CURDATE()
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	2,
	'Developer',
	'AlphaOne',
	'I got to modify other stuff from the software...',
	3100.00,
	CURDATE(),
	CURDATE()
);

-- USER CREATION
INSERT INTO User (id, password) VALUES (2, 'password');

-- INTERVIEW CREATION
INSERT INTO Interview (employeeId, candidateId, jobTitle, interviewDate, feedback, platform)
VALUES (2, 
	1, 
	'Boss', 
	CURDATE(), 
	'GREAT!!!',
	'skype');

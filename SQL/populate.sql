-- CANDIDATE CREATION
INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Luis',
	'Lamadrid', 
	'luis@web.com', 
	'777 Ejemplo, NL, MX', 
	'+528277777777',
	'Student',
	'1993-03-15'
);

INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Pete',
	'Parker', 
	'pete123@hotmail.com', 
	'Street 1, Austin, TX, US', 
	'+528212345678',
	'Graduate',
	'1988-10-12'
);

-- EMPLOYEE CREATION
INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Andres',
	'Perez', 
	'andrew@web.com', 
	'777 Ejemplo, NL, MX', 
	'+528277807777',
	'Student',
	'1991-07-20'
);


INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Senor',
	'Moustachio', 
	'senor@moustachio.com', 
	'Address 123, Monterrey, NL, MX', 
	'+528212348765',
	'PhD',
	'1977-10-30'
);

INSERT INTO Person (firstName, lastName, email, address, phone, 
										professionalTitle, dateOfBirth)
VALUES (
	'Tina',
	'FromHR', 
	'admin@admin.com', 
	'Address 123, Monterrey, NL, MX', 
	'+528233334444',
	'Bachelor of secretarism',
	'1982-11-02'
);

INSERT INTO Candidate (id, expectation) 
VALUES ( 1, 12000.00 );
INSERT INTO Candidate (id, expectation) 
VALUES ( 2, 25000.00 );
INSERT INTO Employee (id, jobTitle, startDate, salary, vacationDays) 
VALUES ( 3, 'Junior developer', '2014-06-01', 14000, 7 );
INSERT INTO Employee (id, jobTitle, startDate, salary, vacationDays) 
VALUES ( 4, 'CEO', '2012-12-01', 40000, 15 );
INSERT INTO Employee (id, jobTitle, startDate, salary, vacationDays) 
VALUES ( 5, 'Secretary', '2010-01-01', 29000, 20 );

-- CERTIFICATE CREATION
INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	2,
	'Bachelors',
	'Computer Science',
	'Tec de Monterrey',
	'2013-05-30'
);

INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	4,
	'Bachelors',
	'Economics',
	'Tec de Monterrey',
	'1995-03-18'
);

INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	4,
	'Masters',
	'Marketing',
	'University of California',
	'2000-05-30'
);

INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	4,
	'PhD',
	'Marketing',
	'University of California',
	'2007-12-15'
);
INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	5,
	'Bachelor',
	'Secretarism',
	'Secretary school',
	'2010-12-15'
);

-- PREVIOUS JOB CREATION
INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	2,
	'Junior Developer',
	'Samsung',
	'I got to modify stuff from the software...',
	10000.00,
	'2013-06-15',
	'2015-02-27'
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	2,
	'Developer',
	'AlphaOne',
	'I got to modify other stuff from the software...',
	12000.00,
	'2015-03-01',
	'2015-11-30'
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	3,
	'Junior Developer',
	'Rovio',
	'I got to modify stuff from the software...',
	10000.00,
	'2013-06-15',
	'2015-02-25'
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	4,
	'COO',
	'AlphaOne',
	'Almost ran the company',
	30000.00,
	'2005-03-01',
	'2012-11-30'
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, 
												 startDate, endDate)
VALUES (
	4,
	'Software Engineer',
	'AlphaOne',
	'Easy side job designing some software',
	18000.00,
	'2000-01-01',
	'2005-02-20'
);


-- USER CREATION
INSERT INTO User (id, password) VALUES (4, 'password');
INSERT INTO User (id, password) VALUES (5, 'admin');

-- INTERVIEW CREATION
INSERT INTO Interview (employeeId, candidateId, jobTitle, interviewDate, feedback, platform)
VALUES (5, 
	1, 
	'Junior Developer', 
	'2015-10-15', 
	'He was a little bit shy.',
	'Skype');
INSERT INTO Interview (employeeId, candidateId, jobTitle, interviewDate, feedback, platform)
VALUES (5, 
	1, 
	'Junior Test Engineer', 
	'2015-11-20', 
	'Great choice for this post!',
	'Telephono');
INSERT INTO Interview (employeeId, candidateId, jobTitle, interviewDate, feedback, platform)
VALUES (4, 
	2, 
	'COO', 
	'2015-11-10', 
	'Good man to follow my footsteps. #BFF :D',
	'Presencial');

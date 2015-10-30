INSERT INTO Candidate (firstName, lastName, email, address, phone, 
											 professionalTitle, dateOfBirth, expectation) 
VALUES (
	'Luis',
	'Lamadrid', 
	'luis@web.com', 
	'777 Ejemplo, NL, MX', 
	'+(52) 827777777',
	'Student',
	CURDATE(),
	14000.00
);

INSERT INTO Certificate (personId, type, name, organization, dateAquired)
VALUES (
	1,
	'Bachelors',
	'Computer Science',
	'Tec de Monterrey',
	CURDATE()
);

INSERT INTO PreviousJob (personId, jobTitle, company,  jobDescription, salary, startDate, 
												 endDate)
VALUES (
	1,
	'Developer',
	'AlphaOne',
	'I got to modify stuff from the software...',
	3000.00,
	CURDATE(),
	CURDATE()
);

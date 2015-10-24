INSERT INTO Candidate (name, email, address, phone, dateOfBirth, expectation) 
VALUES (
	'Luis Lamadrid', 
	'luis@web.com', 
	'777 Ejemplo, NL, MX', 
	'+(52) 827777777',
	CURDATE(),
	14000.00
);

INSERT INTO Title (person_id, type, name, organization, dateAquired)
VALUES (
	1,
	'Bachelors',
	'Computer Science',
	'Tec de Monterrey',
	CURDATE()
);
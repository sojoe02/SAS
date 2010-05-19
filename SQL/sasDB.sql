DROP DATABASE IF EXISTS sasDB;

CREATE DATABASE sasDB;
USE sasDB;

CREATE TABLE Harbour (
	name				VARCHAR(30)		NOT NULL,
	coordinate			VARCHAR(50)		NOT NULL,
	nationality			VARCHAR(30)		NOT NULL,
	PRIMARY KEY (Name)
);
CREATE TABLE Ship (
	shipID				INT(4)			NOT NULL,
	name				VARCHAR(30)		NOT NULL,
	captain				VARCHAR(30)		NOT NULL,
	maxContainers			INT(4)			NOT NULL,
	PRIMARY KEY (shipID)	
);

CREATE TABLE Container (
	containerID			INT(4)			NOT NULL,
	shipID				INT(4)			NOT NULL,
	orderID				INT(4)			NOT NULL,
	content				VARCHAR(30)		NOT NULL,
	PRIMARY KEY (containerID)	
);

CREATE TABLE Users (
	userID				INT(4)			NOT NULL,
	userType			VARCHAR(30)		NOT NULL,
	name				VARCHAR(30)		NOT NULL,
	company				VARCHAR(30)		NOT NULL,
	Adress				VARCHAR(30)		NOT NULL,
	PRIMARY KEY (userID)	
);

CREATE TABLE Orders (
	orderID				INT(4)			NOT NULL	AUTO_INCREMENT,
	userID				INT(4)			NOT NULL,
	containers			INT(4)			NOT NULL,
	startScheduling			INT(4)			NOT NULL,
	endScheduling			INT(4)			NOT NULL,
	PRIMARY KEY (orderID)	
);

CREATE TABLE Scheduling (
	schedulingID			INT(4)			NOT NULL	AUTO_INCREMENT,
	eventType			VARCHAR(10)		NOT NULL,
	eventDate			date			NOT NULL,
	harbour				VARCHAR(30)		NOT NULL,
	shipID				INT(4)			NOT NULL,
	currentContainers		INT(4)			NOT NULL,
	PRIMARY KEY (schedulingID)	
);


CREATE TABLE accessControl (
	userID				INT(4)			NOT NULL;
	adminAccess			BOOLEAN			NOT NULL;
	simulatorAccess			BOOLEAN			NOT NULL;
	shipView	  		CHAR(8)			NOT NULL;
	orderView	 		CHAR(9)			NOT NULL;
	containerView			CHAR(13)		NOT NULL;
	PRIMARY KEY(userID)
);






INSERT INTO Harbour	VALUES
	('Odense', '10.39306640625, 55.3915921070334', 'Denmark'),
	('Valencia', '-0.384521484375, 39.470125122358176', 'Spain'),
	('New York', '-74.00390625, 40.713955826286046', 'USA'),
	('New Orleans', '-90.06591796875, 29.954934549656144', 'USA'),
	('Los Angeles', '-90.06591796875, 29.954934549656144', 'USA'),
	('Shanghai Deepwater Port', '-90.06591796875, 29.954934549656144', 'China'),
	('Sydney', '-90.06591796875, 29.954934549656144', 'Australia'),
	('Cape Town', '-90.06591796875, 29.954934549656144', 'South Africa')	
;

INSERT INTO Ship VALUES
	(1, 'Neil Young', 'Dan Nguyen', 25),
	(2, 'Melua', 'Mats Larsen', 30)
;

INSERT INTO Users VALUES
	(1,'customer','Mærsk Mckinney Møller','Mærsk','Odensegade 1'),
	(2,'customer','Hans Peter Lauritsen','Dansk Rederi','Svendborggade 2')
;

INSERT INTO Users (userID,userType,name) VALUES
	(3,'admin','Dan'),
	(4,'admin','Mats')
;

INSERT INTO accessControl VALUES
	(1,false,false,'shipView1','ordersView1','containerView1'),
	(2,false,false,'shipView2','ordersView2','containerView2'),
	(3,true,true,'ship','orders','Container'),
	(4,true,true,'ship','orders','Container'),
;

INSERT INTO Scheduling (eventType,eventDate,harbour,shipID,currentContainers) VALUES
    ('Departure', '2010-05-02', 'Odense', 1, 0),
    ('Arrival', '2010-05-03', 'Kiel', 1, 0),
    ('Departure', '2010-05-04', 'Kiel', 1, 0), 
    ('Arrival', '2010-05-05', 'Hamburg', 1, 0),
    ('Departure', '2010-05-06', 'Hamburg', 1, 0),
    ('Arrival', '2010-05-07', 'Amsterdam', 1, 0),
    ('Departure', '2010-05-08', 'Amsterdam', 1, 0),
    ('Arrival', '2010-05-16', 'Poole', 1, 0),
    ('Departure', '2010-05-17', 'Poole', 1, 0),
    ('Arrival', '2010-05-23', 'Porto', 1, 0),
    ('Departure', '2010-05-24', 'Porto', 1, 0),
    ('Arrival', '2010-05-30', 'Valencia', 1, 0),
    ('Departure', '2010-05-31', 'Valencia', 1, 0),
    ('Arrival', '2010-06-07', 'Marseille', 1, 0)
;

INSERT INTO Scheduling (eventType,eventDate,harbour,shipID,currentContainers)
 VALUES
	('Departure', '2010-06-02', 'Odense', 2, 0), 
	('Arrival', '2010-06-03', 'Kiel', 2, 0),
    ('Departure', '2010-06-04', 'Kiel', 2, 0), 
    ('Arrival', '2010-06-05', 'Hamburg', 2, 0),
    ('Departure', '2010-06-06', 'Hamburg', 2, 0),
    ('Arrival', '2010-06-07', 'Amsterdam', 2, 0),
    ('Departure', '2010-06-08', 'Amsterdam', 2, 0), 
    ('Arrival', '2010-06-16', 'Poole', 2, 0),
    ('Departure', '2010-06-17', 'Poole', 2, 0), 
    ('Arrival', '2010-06-23', 'Porto', 2, 0),
    ('Departure', '2010-06-24', 'Porto', 2, 0), 
    ('Arrival', '2010-06-30', 'Valencia', 2, 0),
    ('Departure', '2010-06-31', 'Valencia', 2, 0), 
    ('Arrival', '2010-07-07', 'Marseille', 2, 0)
;

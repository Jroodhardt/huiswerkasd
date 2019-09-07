DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS UserSubscription;
DROP TABLE IF EXISTS Subscription;

CREATE TABLE User(
token varchar(14) PRIMARY KEY not null,
id int not null,
name varchar(255) not null,
email varchar(255) not null,
user varchar(255) not null,
password varchar(255) not null,
);

INSERT INTO User VALUES('5234-1234-1235',1,'Meron','meron@23.com','meron','23');
INSERT INTO User VALUES('1111-1111-1111',2,'naam','e@mail.com','user','wachtwoord');

CREATE TABLE UserSubscription(
token varchar(14) not null,
id int not null,
aanbieder varchar(20) not null,
dienst varchar(255) not null,
prijs decimal(5,2) not null,
startDatum datetime default current_date(),
verdubbeling varchar(12) not null,
deelbaar boolean not null,
status varchar(12),
primary key (id,token,startDatum)
);
INSERT INTO UserSubscription VALUES('1234-1234-1234',0,'vodafone','Mobiele telefonie 100',25.00,'2017-01-01','standaard',true,'actief');
INSERT INTO UserSubscription VALUES('1234-1234-1234',1,'vodafone','Mobiele telefonie 250',25.00,'2017-01-01','standaard',true,'actief');
CREATE TABLE Subscription(
id int not null,
aanbieder varchar(20) not null,
dienst varchar(255) not null,
prijs decimal(5,2) not null,
deelbaar boolean not null,
verdubbeling varchar(12) not null,
primary key (id)
);
INSERT INTO Subscription VALUES(0,'vodafone','Mobiele telefonie 100',5.00,false,'standaard');
INSERT INTO Subscription VALUES(1,'vodafone','Mobiele telefonie 250',10.00,false,'standaard');
INSERT INTO Subscription VALUES(2,'vodafone','Glasvezel-internet',40.00,false,'standaard');
INSERT INTO Subscription VALUES(3,'ziggo','Kabel-internet',30.00,false,'standaard');
INSERT INTO Subscription VALUES(4,'ziggo','Eredivisie-Live 1, 2, 3, 4 en 5',10.00,true,'standaard');
INSERT INTO Subscription VALUES(5,'ziggo','HBO Plus',15.00,true,'standaard');

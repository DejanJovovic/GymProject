DROP SCHEMA IF EXISTS mojaTeretana;
CREATE SCHEMA mojaTeretana;
USE mojaTeretana;

CREATE TABLE treninzi (
id BIGINT AUTO_INCREMENT NOT NULL,
naziv VARCHAR(50) NOT NULL,
kratakOpis VARCHAR(255) NOT NULL,
slika VARCHAR(255) NOT NULL,
cena FLOAT NOT NULL,
vrstaTreninga enum('POJEDINACNI', 'GRUPNI') not null,
nivoTreninga enum('AMATERSKI', 'SREDNJI', 'NAPREDNI') not null,
trajanjeTreninga INT(20) NOT NULL,
prosecnaOcena INT(20) NOT NULL,
tipTreninga VARCHAR(255) NOT NULL,
trener varchar(255) NOT NULL,
PRIMARY KEY(id)
);

alter table treninzi 
drop tipTreninga

Create table tipTreninga (
id BIGINT AUTO_INCREMENT NOT NULL,
ime VARCHAR(255) NOT NULL,
opis VARCHAR(255) NOT NULL,
treningId bigint,
PRIMARY KEY(id),
FOREIGN KEY(treningId) references treninzi(id)
);


CREATE TABLE korisnici (
id BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
korisnickoIme VARCHAR(20) UNIQUE NOT NULL,
lozinka VARCHAR(20) NOT NULL,
email VARCHAR(20) NOT NULL,
ime VARCHAR(20) NOT NULL,
prezime VARCHAR(20) NOT NULL,
datumRodjenja VARCHAR(20) NOT NULL,
adresa VARCHAR(20) NOT NULL,
brojTelefona VARCHAR(20) NOT NULL,
datumIVremeRegistracije VARCHAR(20) NOT NULL,
uloga VARCHAR(20) NOT NULL,
tipKorisnika enum('ADMINISTRATOR', 'POLAZNIK'),
aktivan bit	,		
PRIMARY KEY(id)
);


CREATE TABLE clanskeKarte (
id BIGINT AUTO_INCREMENT NOT NULL,
popust INT(50) NOT NULL,
brojPoena INT(100) NOT NULL,
status enum('CEKANJE', 'ODOBREN', 'ODBIJEN'),
korisnikId bigint,
PRIMARY KEY(id),
FOREIGN KEY(korisnikId) REFERENCES korisnici(id)
);


CREATE TABLE sale (
id BIGINT AUTO_INCREMENT NOT NULL,
kapacitet INT(75) NOT NULL,
PRIMARY KEY(id)
);

Create TABLE terminTreninga (
id BIGINT AUTO_INCREMENT NOT NULL,
sala VARCHAR(100) NOT NULL,
trening VARCHAR(100) NOT NULL,
datumTermina date not null,
vreme time not null,
treningId bigint,
salaId bigint,
PRIMARY KEY(id),
foreign key(salaId) references sale(id),
foreign keY(treningId) references treninzi(id)
);


CREATE TABLE komentari (
id BIGINT AUTO_INCREMENT NOT NULL,
tekstKomentara VARCHAR(300) NOT NULL,
 ocena int(20) not null,
datum DATE NOT NULL,
anoniman BIT NOT NULL,
autor varchar(50) not null,
trening varchar(50) not null,
statusKomentara enum('CEKANJE', 'ODOBREN', 'ODBIJEN') NOT NULL,
korisnikId bigint,
treningId bigint,
PRIMARY KEY(id),
FOREIGN KEY(korisnikId) REFERENCES korisnici(id),
FOREIGN KEY(treningId) REFERENCES treninzi(id)
);

CREATE TABLE korpe(
id BIGINT AUTO_INCREMENT NOT NULL,
korisnikId bigint,
terminId bigint not null,
PRIMARY KEY(id),
FOREIGN KEY(korisnikId) REFERENCES korisnici(id),
FOREIGN KEY(terminId) REFERENCES terminTreninga(id)
);


	
	
INSERT INTO korisnici (tipKorisnika,korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona, datumIVremeRegistracije, uloga)
VALUES ('ADMINISTRATOR', 'admin', 'admin','admin@admin.rs','Marko','Markovic','27.05.2000.','neka tamo adresa','063857281','11.07.2022. 15:37', 'admin');
INSERT INTO korisnici (tipKorisnika, korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona, datumIVremeRegistracije, uloga)
VALUES ('POLAZNIK','korisnik', 'korisnik','korisnik@korisnik.rs','Petar','Petrovic','11.08.2001.','neka tamo adresa2','06732131245','11.07.2022. 15:38', 'clanTeretane');

INSERT INTO treninzi (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Snaga sa Srdjanom','Srdjan','Trening snage opis treninga','https://www.fitness.com.hr/images/articles/531.jpg','snaga',1000,'POJEDINACNI','NAPREDNI',45,4.5);
INSERT INTO treninzi (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Kondicija sa Mirkom','Mirko','Trening kondicije opis treninga','https://velikirecnik.com/wp-content/uploads/2016/03/Kondicija.jpg','kondicija',500,'GRUPNI','SREDNJI',60,4.1);
INSERT INTO treninzi (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Joga sa Marinom','Marina','Trening joga opis treninga','https://i.insider.com/61b11b1679acc60019cd44f9?width=1136&format=jpeg','yoga',700,'GRUPNI','AMATERSKI',30,3.9);
INSERT INTO treninzi (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Fitness sa Ivom','Iva','Trening fitness opis treninga','https://i.insider.com/61b11b1679acc60019cd44f9?width=1136&format=jpeg','fitness',500,'GRUPNI','AMATERSKI',40,3.6);
INSERT INTO treninzi (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Fitness sa Milicom','Milica','Trening fitness opis treninga','https://i.imgur.com/iehRYNF.jpg','fitness',600,'POJEDINACNI','AMATERSKI',30,4.1);

INSERT INTO clanskeKarte(popust, brojPoena, status) VALUES(10,3, "ODBIJEN");
INSERT INTO clanskeKarte(popust, brojPoena, status) VALUES(15,1, "ODBIJEN");
INSERT INTO clanskeKarte(popust, brojPoena, status) VALUES(20,5, "ODOBREN");
INSERT INTO clanskeKarte(popust, brojPoena, status) VALUES(15,2, "ODOBREN");
INSERT INTO clanskeKarte(popust, brojPoena,status) VALUES(10,7, "ODOBREN");

INSERT INTO sale( kapacitet) VALUES(30);
INSERT INTO sale( kapacitet) VALUES(40);
INSERT INTO sale( kapacitet) VALUES(25);
INSERT INTO sale(kapacitet) VALUES(50);
INSERT INTO sale(kapacitet) VALUES(10);

INSERT INTO terminTreninga(sala, trening, datumTermina) VALUES(2,2 ,'15.02.2022.');
INSERT INTO terminTreninga(sala, trening, datumTermina) VALUES(1,3,'27.02.2022.');
INSERT INTO terminTreninga(sala, trening, datumTermina) VALUES(2,3,'22.02.2022.');
INSERT INTO terminTreninga(sala, trening, datumTermina) VALUES(5,2,'11.03.2022.');
INSERT INTO terminTreninga(sala, trening, datumTermina) VALUES(4,1,'22.03.2022.');


INSERT INTO komentari(tekstKomentara, ocena, datum, anoniman, trening, statusKomentara, autor)
VALUES('test', 2, '15.02.2022.' , true, 'test', 'ODBIJEN', 1)
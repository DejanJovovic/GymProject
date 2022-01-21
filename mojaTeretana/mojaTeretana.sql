DROP SCHEMA IF EXISTS mojaTeretana;
CREATE SCHEMA mojaTeretana;
USE mojaTeretana;

CREATE TABLE korisnik (
	idKorisnik BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    korisnickoIme VARCHAR(20) UNIQUE NOT NULL,
    lozinka VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
	ime VARCHAR(20) NOT NULL,
    prezime VARCHAR(20) NOT NULL,
	datumRodjenja VARCHAR(20) NOT NULL,
	adresa VARCHAR(20) NOT NULL,
	brojTelefona VARCHAR(20) NOT NULL,
    datumIVremeRegistracije VARCHAR(30),
    uloga VARCHAR(20) NOT NULL,
	PRIMARY KEY(idKorisnik)
);

CREATE TABLE trening (
	idTrening BIGINT AUTO_INCREMENT NOT NULL,
	naziv VARCHAR(20) NOT NULL,
	kratakOpis VARCHAR(255) NOT NULL,
	slika VARCHAR(75) NOT NULL,
	tipTreninga VARCHAR(20) NOT NULL,
	cena INT(20) NOT NULL,
	vrstaTreninga VARCHAR(20) NOT NULL,
    nivoTreninga VARCHAR(20) NOT NULL,
    trajanjeTreninga INT(20) NOT NULL,
    prosecnaOcena INT(20),
	PRIMARY KEY(idTrening)
);
        
ALTER TABLE trening
ADD trener varchar(255);


CREATE TABLE tipTreninga (
	idTipTreninga BIGINT AUTO_INCREMENT NOT NULL,
	ime VARCHAR(20),
	opis VARCHAR(100),
	PRIMARY KEY(idTipTreninga)
);

CREATE TABLE clanskeKarte (
	idClanskeKarte BIGINT AUTO_INCREMENT NOT NULL,
	popust INT(50) NOT NULL,
	brojPoena INT(100) NOT NULL,
	PRIMARY KEY(idClanskeKarte),
	FOREIGN KEY(idClanskeKarte) REFERENCES korisnik(idKorisnik)
		ON DELETE CASCADE
);

CREATE TABLE sala (
    idSala BIGINT NOT NULL,
    oznakaSale VARCHAR(20) NOT NULL,
    kapacitet INT(75) NOT NULL,
    PRIMARY KEY(idSala)
);

CREATE TABLE termin (
    idTermin BIGINT NOT NULL,
    sala BIGINT NOT NULL,
    trening BIGINT NOT NULL,
    datum VARCHAR(100) NOT NULL,
    PRIMARY KEY(idTermin),
    FOREIGN KEY(sala) REFERENCES sala(idSala)
		ON DELETE CASCADE,
    FOREIGN KEY(trening) REFERENCES trening(idTrening)
		ON DELETE CASCADE
);

CREATE TABLE komentar (
	idKomentar BIGINT NOT NULL,
    ocena INT(20),
    datum VARCHAR(100) NOT NULL,
    autor BIGINT,
    trening BIGINT NOT NULL,
    statusKomentara VARCHAR(20) NOT NULL,
    anoniman boolean,
    PRIMARY KEY(idKomentar),
    FOREIGN KEY(autor) REFERENCES korisnik(idKorisnik)
		ON DELETE CASCADE,
	FOREIGN KEY(trening) REFERENCES trening(idTrening)
		ON DELETE CASCADE
);

INSERT INTO korisnik (korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona, datumIVremeRegistracije, uloga)
VALUES ('admin', 'admin','admin@admin.rs','Marko','Markovic','27.05.2000.','neka tamo adresa','063857281','11.01.2022. 15:37','administrator');
INSERT INTO korisnik (korisnickoIme, lozinka, email, ime, prezime, datumRodjenja, adresa, brojTelefona, datumIVremeRegistracije, uloga)
VALUES ('korisnik', 'korisnik','korisnik@korisnik.rs','Petar','Petrovic','11.08.2001.','neka tamo adresa2','06732131245','11.01.2022. 15:38','clanTeretane');

INSERT INTO trening (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Snaga sa Srdjanom','Srdjan','Trening snage opis treninga','https://www.fitness.com.hr/images/articles/531.jpg','snaga',1000,'pojedinacni','napredni',45,4.5);
INSERT INTO trening (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Kondicija sa Mirkom','Mirko','Trening kondicije opis treninga','https://velikirecnik.com/wp-content/uploads/2016/03/Kondicija.jpg','kondicija',500,'grupni','srednji',60,4.1);
INSERT INTO trening (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Joga sa Marinom','Marina','Trening joga opis treninga','https://i.insider.com/61b11b1679acc60019cd44f9?width=1136&format=jpeg','yoga',700,'grupni','amaterski',30,3.9);
INSERT INTO trening (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Fitness sa Ivom','Iva','Trening fitness opis treninga','https://i.insider.com/61b11b1679acc60019cd44f9?width=1136&format=jpeg','fitness',500,'grupni','amaterski',40,3.6);
INSERT INTO trening (naziv, trener, kratakOpis, slika, tipTreninga, cena, vrstaTreninga, nivoTreninga, trajanjeTreninga, prosecnaOcena)
VALUES('Fitness sa Milicom','Milica','Trening fitness opis treninga','https://i.imgur.com/iehRYNF.jpg','fitness',600,'pojedinacni','amaterski',30,4.1);

INSERT INTO tipTreninga(ime, opis) VALUES('yoga', 'istezanje misica i opustanje');
INSERT INTO tipTreninga(ime, opis) VALUES('cardio', 'dobijanje kondicije i mrsavnjenje');
INSERT INTO tipTreninga(ime, opis) VALUES('snaga', 'dobijanje snage');
INSERT INTO tipTreninga(ime, opis) VALUES('borilacke vestine', 'ucenje samoodbrane');

INSERT INTO clanskeKarte(popust, brojPoena) VALUES(10,3);
INSERT INTO clanskeKarte(popust, brojPoena) VALUES(15,1);
INSERT INTO clanskeKarte(popust, brojPoena) VALUES(20,5);
INSERT INTO clanskeKarte(popust, brojPoena) VALUES(15,2);
INSERT INTO clanskeKarte(popust, brojPoena) VALUES(10,7);

INSERT INTO sala(oznaka, kapacitet) VALUES('prva sala',30);
INSERT INTO sala(oznaka, kapacitet) VALUES('druga sala',40);
INSERT INTO sala(oznaka, kapacitet) VALUES('treca sala',25);
INSERT INTO sala(oznaka, kapacitet) VALUES('cetvrta sala',50);
INSERT INTO sala(oznaka, kapacitet) VALUES('peta sala',10);

INSERT INTO termin(sala, trening, datum) VALUES(2,2,'15.02.2022.');
INSERT INTO termin(sala, trening, datum) VALUES(1,3,'27.02.2022.');
INSERT INTO termin(sala, trening, datum) VALUES(2,3,'22.02.2022.');
INSERT INTO termin(sala, trening, datum) VALUES(5,2,'11.03.2022.');
INSERT INTO termin(sala, trening, datum) VALUES(4,1,'22.03.2022.');

INSERT INTO komentar(tekst, ocena, datum, autor, trening, statusKomentara, anoniman) VALUES('Dobar trening svaka cast',5,'14.01.2022.',2,3,'odobren',false);

select * from korisnik;
select * from trening;

SELECT naziv, slika, cena, prosecnaOcena FROM trening;
SELECT * FROM trening;
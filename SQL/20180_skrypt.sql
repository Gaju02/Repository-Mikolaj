CREATE TABLE adres_zameldowania (
id_adres_zameldowania INTEGER IDENTITY(1,1) PRIMARY KEY,
nazwa_ulicy VARCHAR(255),
numer_lokalu CHAR(6),
kod_pocztowy VARCHAR(10),
miejscowosc VARCHAR(255) 
);
CREATE TABLE rodzaje_sprzedazy(
id_rodzaje_sprzedazy INTEGER IDENTITY(1,1) PRIMARY KEY,
forma_sprzedazy VARCHAR(50)
);
CREATE TABLE oddzial(
id_oddzial INTEGER IDENTITY(1,1) PRIMARY KEY,
nazwa_ulicy VARCHAR(255) NOT NULL,
numer_lokalu CHAR(6) NOT NULL,
kod_pocztowy VARCHAR(10) NOT NULL,
miejscowosc VARCHAR(255) NOT NULL
);


CREATE TABLE typ_konta(
id_typ_konta INTEGER IDENTITY(1,1) PRIMARY KEY,
nazwa_konta VARCHAR(20) NOT NULL,
has³o_do_konta VARCHAR(20) NOT NULL
);
CREATE TABLE dane_pracownik (
id_dane_pracownik INTEGER IDENTITY(1,1) PRIMARY KEY,
id_adres_zameldowania INTEGER NOT NULL REFERENCES adres_zameldowania(id_adres_zameldowania),
id_oddzial INTEGER NOT NULL REFERENCES oddzial(id_oddzial),
imie VARCHAR(255) NOT NULL,
nazwisko VARCHAR(255) NOT NULL
);
CREATE TABLE poprzedni_operatorzy(
id_poprzedni_operatorzy INTEGER IDENTITY(1,1) PRIMARY KEY,
nazwa_operatora VARCHAR(20) NOT NULL,
powod_zrezygnowania_z_umowy VARCHAR(255) NOT NULL,
);

CREATE TABLE karta_sim(
id_karta_sim INTEGER IDENTITY(1,1) PRIMARY KEY,
numer_karty VARCHAR(20) NOT NULL,
PIN VARCHAR(4) NOT NULL,
PUK VARCHAR(8) NOT NULL,
stan_karty VARCHAR(20) NOT NULL
);
CREATE TABLE dane_klient(
id_dane_klient INTEGER IDENTITY(1,1) PRIMARY KEY,
id_typ_konta INTEGER NOT NULL REFERENCES typ_konta(id_typ_konta),
id_adres_zamledowania INTEGER NOT NULL REFERENCES adres_zameldowania(id_adres_zameldowania),
imie VARCHAR(255) NOT NULL,
nazwisko VARCHAR(255) NOT NULL,
liczba_numerow INTEGER NOT NULL,
pesel VARCHAR(255) NOT NULL,
e_mail_korespondencyjny VARCHAR(255) NOT NULL,
numer_telefonu VARCHAR(11) NOT NULL,
od_kiedy_u_obecnego_operatora DATETIME NOT NULL,
liczba_kont_klienta INTEGER NOT NULL
);
CREATE TABLE sprzedaz(
id_sprzedaz INTEGER IDENTITY(1,1) PRIMARY KEY,
id_dane_pracownik INTEGER NOT NULL REFERENCES dane_pracownik(id_dane_pracownik),
id_rodzaje_sprzedazy INTEGER NOT NULL REFERENCES rodzaje_sprzedazy(id_rodzaje_sprzedazy),
id_dane_klient INTEGER NOT NULL REFERENCES dane_klient(id_dane_klient),
)
CREATE TABLE faktura(
id_faktura INTEGER IDENTITY(1,1) PRIMARY KEY,
id_sprzedaz INTEGER NOT NULL REFERENCES sprzedaz(id_sprzedaz),
data_wystawienia DATETIME NOT NULL,
id_oddzial INTEGER NOT NULL REFERENCES oddzial(id_oddzial),
sposob_platnosci VARCHAR(20) NOT NULL,
data_platnosci DATETIME NOT NULL,
do_zaplaty INTEGER NOT NULL,
do_zaplaty_slownie VARCHAR(255) NOT NULL,
ilosc INTEGER NOT NULL,
wartosc_netto INTEGER NOT NULL,
stawka_vat INTEGER NOT NULL,
kwota_vat INTEGER NOT NULL,
wartosc_brutto INTEGER NOT NULL,
czy_oplacona VARCHAR(3) NOT NULL,
);
CREATE TABLE telefon(
id_telefon INTEGER IDENTITY(1,1) PRIMARY KEY,
marka_telefonu VARCHAR(20) NOT NULL,
model_telefonu VARCHAR(20) NOT NULL,
cena_hurtowa INTEGER NOT NULL
);

CREATE TABLE obecna_oferta(
id_obecna_oferta INTEGER IDENTITY(1,1) PRIMARY KEY,
id_telefon INTEGER NOT NULL REFERENCES telefon(id_telefon),
typ_oferty VARCHAR(20) NOT NULL,
czy_rozmowy_p³atne VARCHAR(10) NOT NULL,
czy_smsy_p³atne VARCHAR(10) NOT NULL,
ilosc_internetu INTEGER NOT NULL
);

CREATE TABLE szanse_sprzeda¿owe(
id_szanse_sprzeda¿owe INTEGER IDENTITY(1,1) PRIMARY KEY,
id_dane_klient INTEGER NOT NULL REFERENCES dane_klient(id_dane_klient),
nazwa_szansy_sprzeda¿owej VARCHAR(255) NOT NULL
);

CREATE TABLE inne(
id_inne INTEGER IDENTITY(1,1) PRIMARY KEY,
id_sprzedaz INTEGER NOT NULL REFERENCES sprzedaz(id_sprzedaz),
id_karta_sim INTEGER NOT NULL REFERENCES karta_sim(id_karta_sim),
id_poprzedni_operatorzy INTEGER NOT NULL REFERENCES poprzedni_operatorzy(id_poprzedni_operatorzy)
);
CREATE TABLE zuzycie_danych(
id_zuzycie_danych INTEGER IDENTITY(1,1) PRIMARY KEY,
zuzycie_ostatni_miesiac INTEGER NOT NULL,
zuzycie_srednia_6miesiecy INTEGER NOT NULL,
);
CREATE TABLE zgody_klienta(
id_zgody_klienta INTEGER IDENTITY(1,1) PRIMARY KEY,
typ_zgody VARCHAR(20) NOT NULL,
opis_zgody VARCHAR(255) NOT NULL,
);
CREATE TABLE zgloszenia(
id_zgloszenia INTEGER IDENTITY(1,1) PRIMARY KEY,
typ_zgloszenia VARCHAR(20) NOT NULL,
opis_zgloszenia_ VARCHAR(255) NOT NULL,
zgloszenie_odp VARCHAR(255) NOT NULL,
data_otrzymania_zgloszenia DATETIME NOT NULL
);
CREATE TABLE platnosci_okazjonalne(
id_platnosci_okazjonalne INTEGER IDENTITY(1,1) PRIMARY KEY,
forma_platnosci VARCHAR(20) NOT NULL,
data_platnosci DATETIME NOT NULL,
kwota INTEGER NOT NULL,
);
CREATE TABLE faktury_platnosci(
id_faktury_platnosci INTEGER IDENTITY(1,1) PRIMARY KEY,
id_platnosci_okazjonalne INTEGER NOT NULL REFERENCES platnosci_okazjonalne(id_platnosci_okazjonalne),
id_faktura INTEGER NOT NULL REFERENCES faktura(id_faktura)
);
CREATE TABLE konto_klient(
id_konto_klient INTEGER IDENTITY(1,1) PRIMARY KEY,
id_inne INTEGER NOT NULL REFERENCES inne(id_inne),
id_zuzycie_danych INTEGER NOT NULL REFERENCES zuzycie_danych(id_zuzycie_danych ),
id_obecna_oferta INTEGER NOT NULL REFERENCES obecna_oferta(id_obecna_oferta),
id_zgloszenia INTEGER NOT NULL REFERENCES zgloszenia(id_zgloszenia),
id_zgody_klienta INTEGER NOT NULL REFERENCES zgody_klienta(id_zgody_klienta),
id_faktury_platnosci INTEGER NOT NULL REFERENCES faktury_platnosci(id_faktury_platnosci),
id_dane_klient INTEGER NOT NULL REFERENCES dane_klient(id_dane_klient),
);


INSERT INTO oddzial(nazwa_ulicy,numer_lokalu,kod_pocztowy,miejscowosc)
VALUES ('Mickiewicza','3A','82-100','Nowy Dwor Gdanski'),
	   ('Konopnicka','9','82-200','Malbork'),
	   ('Sienkiewicza','2B','80-000','Gdansk'),
	   ('Morska','3','83-200','Starogard'),
	   ('Zagonowa','56,','82-310','Elblag'),
	   ('Dêbowa','4A','83-004','Pruszcz Gdanski');
INSERT INTO adres_zameldowania(nazwa_ulicy,numer_lokalu,kod_pocztowy,miejscowosc)
VALUES ('Akacjowa','3','82-100','Nowy Dwor Gdanski'),
	   ('Zajazdowa','3C/25','82-200','Malbork'),
	   ('Kosciuszki','56A','80-000','Gdansk'),
	   ('Targowa','6','83-200','Starogard'),
	   ('Cieszynska','43,','82-310','Elblag'),
	   ('Lipowa','3','83-004','Pruszcz Gdanski'),
	   ('Wyszyñskiego','3C/25','83-004','Pruszcz Gdanski'),
	   ('Sklepowa','4','83-004','Pruszcz Gdanski'),
	   ('Kosciuszki','2A','82-310','Elblag'),
	   ('Ferajny','7B','83-004','Pruszcz Gdanski'),
	   ('Dêbowa','3/C','82-100','Nowy Dwor Gdanski'),
	   ('Jana Paw³a II','15','82-100','Nowy Dwor Gdanski');

INSERT INTO typ_konta(nazwa_konta,has³o_do_konta)
VALUES ('konto_50','dfsnovs**'),
	   ('konto_51','modfsv=24'),
	   ('konto_52','fsdnor-023'),
	   ('konto_53','0842fsvv'),
	   ('konto_54','noaoij3'),
	   ('konto_55','bniuhf82734');


INSERT INTO dane_klient(
id_typ_konta,
id_adres_zamledowania,
imie,
nazwisko,
liczba_numerow,
pesel,
e_mail_korespondencyjny,
numer_telefonu,
od_kiedy_u_obecnego_operatora,
liczba_kont_klienta)
VALUES (1,6,'Adam','Nowak',3,'04532123456','nowak@gmail.com','555-444-333','2022-02-13',2),
(2,7,'Tadeusz','Koscianek',1,'06543234567','Koscianek@gmail.com','111-432-343','2022-05-22',2),
(3,8,'Andrzej','Borsuk',5,'06543234567','Borsuk@gmail.com','532-534-231','2022-06-10',6),
(4,9,'Agata','Kowalska',8,'03432345676','Kowalska@wp.pl.com','532-142-643','2022-04-01',4),
(5,10,'Jadwiga','B¹k',1,'06543456785','B¹k@onet.pl','972-231-432','2022-03-09',1),
(6,11,'Paulina','Wiœnewska',3,'09823612344','Wiœnewska@gmail.com','983-242-323','2022-06-13',5);


INSERT INTO szanse_sprzeda¿owe(id_dane_klient,nazwa_szansy_sprzeda¿owej)
VALUES(1,'przedluzenie umowy'),
(2,'zaoferowanie routera'),
(3,'zaoferowanie nowego modelu telefonu'),
(4,'przedluzenie umowy'),
(5,'zapropowanie nowej oferty'),
(6,'zaproponowanie nowej oferty');

INSERT INTO telefon(marka_telefonu,model_telefonu,cena_hurtowa)
VALUES('Xiaomi','Redmi note 10',1200),
('Iphone','13 Pro',3200),
('Samsung','S10 ',2500),
('Xiaomi','Redmi note 8',800),
('Huawei','S7',900),
('Xiaomi','Redmi note 11',1300);

INSERT INTO obecna_oferta(
id_telefon,
typ_oferty,
czy_rozmowy_p³atne,
czy_smsy_p³atne,
ilosc_internetu)
VALUES(1,'postpaid','tak','tak',500),
(2,'internet stacjonarny', 'nie','nie',1000),
(3,'telewizja','nie','nie',0),
(4,'mix','tak','nie',100),
(5,'postpaid','tak','nie',500),
(6,'telewizja','nie','nie',0);

INSERT INTO rodzaje_sprzedazy(forma_sprzedazy)
VALUES('migracja'),
('przeniesienie numeru'),
('nowy numer'),
('nowa umowa');

INSERT INTO dane_pracownik(id_adres_zameldowania,id_oddzial, imie, nazwisko)
VALUES(1,1,'Magdalena','Hajduk'),
(2,2,'Arkadiusz','Kowalski'),
(3,3,'Mariusz','Knobek'),
(4,4,'Maciej','Zenkowski'),
(5,5,'Zygmunt','Jaroszewski'),
(6,6,'Adam','Nowak');


INSERT INTO sprzedaz(id_dane_pracownik,id_rodzaje_sprzedazy,id_dane_klient)
VALUES(1,1,1),
(2,2,2),
(3,3,3),
(4,3,4),
(5,2,5),
(6,4,6);

INSERT INTO karta_sim(numer_karty, PIN,PUK,stan_karty)
VALUES('412421412','2312','4231','aktywna'),
('54124124','9872','4231','aktywna'),
('10432720','9123','4111','aktywna'),
('51923847','4421','4442','nieaktywna'),
('12039841','2643','9894','aktywna'),
('01935234','9081','1566','nieaktywna');

INSERT INTO poprzedni_operatorzy(nazwa_operatora,powod_zrezygnowania_z_umowy)
VALUES('Play','zbyt duze oplaty'),
('Orange','zbyt duze oplaty'),
('Plus','brak mozliwosci migracji numeru'),
('T-Mobile','brak satysfakcjonuj¹cej oferty'),
('Orange','brak oferty z zalaczeniem telewizji'),
('nju mobile','zbyt duze oplaty');


INSERT INTO inne(id_sprzedaz,id_karta_sim,id_poprzedni_operatorzy)
VALUES(1,1,1),
(2,2,2),
(3,3,3),
(4,4,4),
(5,5,5),
(6,6,6);

INSERT INTO zgloszenia(typ_zgloszenia,opis_zgloszenia_,zgloszenie_odp,data_otrzymania_zgloszenia)
VALUES ('infolinia','brak internetu','wyslanie serwisanta','2022-01-04'),
('salon','brak ³adowarki w zestawie','wizyta w salonie','2022-03-05 '),
('infolinia','brak telewizji','wyslanie serwisanta','2022-04-20 '),
('salon','brak internetu','wyslanie serwisanta','2022-03-04'),
('strona internetowa','brak internetu','wyslanie serwisanta','2022-03-04'),
('infolinia','otrzymanie uszkodzonego telefonu','wizyta w salonie','2022-03-04');

INSERT INTO zgody_klienta(typ_zgody,opis_zgody)
VALUES ('umowa','podpisanie umowy'),
('umowa','podpisanie umowy'),
('umowa','podpisanie umowy'),
('umowa','podpisanie umowy'),
('umowa','podpisanie umowy'),
('umowa','podpisanie umowy');

INSERT INTO platnosci_okazjonalne(forma_platnosci,data_platnosci,kwota)
VALUES('paragon','2022-03-04',50),
('paragon','2022-03-02 ',50),
('paragon','2022-07-10',20),
('paragon','2022-03-07',70),
('paragon','2022-09-05',100),
('paragon','2022-10-04',5);


INSERT INTO faktura(
id_sprzedaz,
data_wystawienia,
id_oddzial,
sposob_platnosci,
data_platnosci,
do_zaplaty,
do_zaplaty_slownie,
ilosc,
wartosc_netto,
stawka_vat,
kwota_vat,
wartosc_brutto,
czy_oplacona)
VALUES 
(1,'2022-07-04 12:45',1,'gotowka','2022-08-04 18:34','100','sto zlotych',1,77,23,23,100,'tak'),
(2,'2022-03-04 12:45',2,'gotowka','2022-04-04 13:12','20','dwadziescia zlotych',1,15.4,23,4.6,20,'nie'),
(3,'2022-10-01 10:45',3,'karta','2022-11-04 10:30','1000','tysciac zlotych',1,770,23,230,1000,'tak'),
(4,'2022-11-02 11:35',4,'gotowka','2022-12-04 11:00','250','dwiesciepiecdziesiat zlotych',1,192.5,23,57.5,250,'tak'),
(5,'2022-12-03 09:35',5,'inne','2023-01-04 12:45','100','sto zlotych',1,77,23,23,100,'nie'),
(6,'2022-05-04 16:50',6,'gotowka','2022-06-04 13:40','50','piecdziesiat zlotych',1,48.5,23,11.5,50,'tak');

INSERT INTO faktury_platnosci(id_platnosci_okazjonalne,id_faktura)
VALUES (1,1),
(2,2),
(3,3),
(4,4),
(5,5),
(6,6);

INSERT INTO zuzycie_danych(zuzycie_ostatni_miesiac,zuzycie_srednia_6miesiecy)
VALUES (20,50),
(50,150),
(50,120),
(10,100),
(60,230),
(41,105);

INSERT INTO konto_klient(
id_inne,
id_zuzycie_danych,
id_obecna_oferta,
id_zgloszenia,
id_zgody_klienta,
id_faktury_platnosci,
id_dane_klient)
VALUES(1,1,1,1,1,1,1),
(2,2,2,2,2,2,2),
(3,3,3,3,3,3,3),
(4,4,4,4,4,4,4),
(5,5,5,5,5,5,5),
(6,6,6,6,6,6,6);



SELECT * from dane_pracownik --1
SELECT * from adres_zameldowania --2
SELECT * from oddzial --3
SELECT * from typ_konta --4
SELECT * from dane_klient --5
SELECT * from obecna_oferta --6
SELECT * from sprzedaz --7
SELECT * from szanse_sprzeda¿owe --8
SELECT * from konto_klient--9
SELECT * from zgody_klienta--10
SELECT * from zgloszenia--11
SELECT * from zuzycie_danych--12
SELECT * from inne--13
SELECT * from poprzedni_operatorzy--14
SELECT * from karta_sim--15
SELECT * from faktura--16
SELECT * from faktury_platnosci--17
SELECT * from telefon--18
SELECT * from platnosci_okazjonalne--19
SELECT * from rodzaje_sprzedazy--20
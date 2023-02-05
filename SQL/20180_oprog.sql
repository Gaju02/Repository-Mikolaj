CREATE TRIGGER prac_del_after ON dane_pracownik
AFTER DELETE
AS
IF @@ROWCOUNT>1           		
BEGIN
PRINT 'Pracowników mo¿na usuwaæ tylko pojedynczo!'
ROLLBACK
END
GO  --Usuwanie pojedynczo pracowników

CREATE TRIGGER pesel_klient ON dane_klient  
AFTER INSERT AS
BEGIN
	DECLARE @pesel INTEGER
	SET @pesel=-1
	SELECT @pesel=pesel FROM INSERTED WHERE pesel=0
	IF	@pesel=0
	BEGIN
		RAISERROR('Pesel nie mo¿e byæ równy zero!',1,2);
		ROLLBACK
	END
END
GO  

GO
CREATE FUNCTION dbo.aktywnosc_klienta(
@id_dane_klient INT
) RETURNS INT
BEGIN
RETURN (SELECT SUM(id_sprzedaz) FROM sprzedaz 	ok	
	WHERE id_dane_klient =@id_dane_klient)
END;
GO
SELECT dbo.aktywnosc_klienta(1) AS ilosc;  


--DROP FUNCTION dbo.ilosc_faktur
CREATE FUNCTION dbo.ilosc_faktur(
@dana_data DATETIME
) RETURNS INT
BEGIN
RETURN (SELECT COUNT(id_faktura) FROM faktura ok
WHERE data_wystawienia=@dana_data)
END
GO
SELECT dbo.ilosc_faktur('2022-11-04 10:30') AS spis;  

--DROP PROCEDURE dodaj_pracownika
CREATE PROCEDURE dodaj_pracownika
@id_adres_zameldowania INTEGER,
@id_oddzial INTEGER,
@imie VARCHAR(20),
@nazwisko VARCHAR(20)
AS
INSERT INTO dane_pracownik(id_adres_zameldowania,id_oddzial, imie,nazwisko)
VALUES(@id_adres_zameldowania,@id_oddzial,@imie, @nazwisko)  
GO
EXECUTE dodaj_pracownika 1,1,'Adam','Kowalski'; 

--drop procedure zwieksz_cene_telefonu
CREATE PROCEDURE zwieksz_cene_telefonu @id INTEGER, @kwota INTEGER
AS
UPDATE telefon
SET cena_hurtowa=@kwota
WHERE @id = id_telefon
GO
EXECUTE zwieksz_cene_telefonu 4,5000 

--DROP VIEW klient_raport
GO
CREATE VIEW klient_raport  
AS
SELECT k.imie, k.nazwisko , COUNT(s.id_sprzedaz) AS "ilosc"
FROM dane_klient k JOIN sprzedaz s ON k.id_dane_klient = s.id_dane_klient
GROUP BY k.imie,k.nazwisko
HAVING COUNT(s.id_sprzedaz) <= (SELECT MAX(liczba_kont_klienta) FROM dane_klient)
GO
SELECT * FROM klient_raport  



--drop view zgloszenia_raport

GO
CREATE VIEW zgloszenia_raport 	

AS
SELECT SUM(z.id_zgloszenia) AS "ilosc zgloszenia infolinia"
FROM zgloszenia z JOIN konto_klient k ON z.id_zgloszenia = k.id_zgloszenia
GROUP BY  k.id_dane_klient, k.id_konto_klient
HAVING SUM(z.id_zgloszenia) IN (SELECT COUNT(id_zgloszenia) FROM zgloszenia
WHERE typ_zgloszenia='infolinia')

GO
SELECT * FROM zgloszenia_raport
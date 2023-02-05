CREATE PROCEDURE dodaj_pracownika(zameldowanie int, oddzial int,imie_prac varchar(20),nazwisko_prac varchar(20))
LANGUAGE SQL
AS $$
INSERT INTO dane_pracownik(id_adres_zameldowania,id_oddzial, imie, nazwisko) VALUES(zameldowanie,   oddzial,imie_prac,nazwisko_prac);     
$$;

CALL dodaj_pracownika(1,1,'Adam','Kowalski');

create procedure zwieksz_cene_telefonu(iden INTEGER, kwota INTEGER)     
language sql
as $$
update telefon set cena_hurtowa=kwota
where iden=id_telefon;
$$;

CALL zwieksz_cene_telefonu(50,5000);

create function ilosc_faktur( 
	dana_data DAT
)returns int
language plpgsql
as
$$
declare
	ilosc int;
begin 
	SELECT COUNT(id_faktura) 
	into ilosc
	from faktura
	where data_wystawienia=dana_data;
		   
	return ilosc;
END;
$$;
SELECT ilosc_faktur('2022-07-04')

create function aktywnosc_klienta( 
P_id_dane_klient int
) returns int
language plpgsql
as
$$
declare
	ilosc int;
begin
	    select SUM(id_dane_klient) 
		into ilosc
		from sprzedaz
		WHERE id_dane_klient =P_id_dane_klient;
		
		return ilosc;
		
END;
$$;
SELECT aktywnosc_klienta(1)




CREATE VIEW zgloszenia_raport
AS
SELECT k.id_dane_klient,k.id_konto_klient, COUNT(z.id_zgloszenia) AS "ilosc"
FROM zgloszenia z INNER JOIN konto_klient k ON k.id_dane_klient, k.id_konto_klient  
GROUP BY k.id_dane_klient,k.id_konto_klient  
HAVING SUM(z.id_zgloszenia) IN (SELECT typ_zgloszenia FROM zgloszenia  
WHERE typ_zgloszenia ='infolinia');


SELECT * FROM zgloszenia_raport




CREATE VIEW klient_raport
AS
SELECT k.imie, k.nazwisko , COUNT(s.id_sprzedaz) AS "ilosc"
FROM dane_klient k JOIN sprzedaz s ON k.id_dane_klient = s.id_dane_klient
GROUP BY k.imie,k.nazwisko
HAVING COUNT(s.id_sprzedaz) <= (SELECT MAX(liczba_kont_klienta) FROM dane_klient)  




CREATE OR REPLACE FUNCTION prevent_deletes() RETURNS TRIGGER AS $$
BEGIN
    RAISE EXCEPTION 'Deleting records is not allowed.';
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;
 
CREATE TRIGGER prevent_deletes_trigger         
BEFORE DELETE ON dane_pracownik
FOR EACH ROW
EXECUTE FUNCTION prevent_deletes();





CREATE  FUNCTION check_pesel() RETURNS TRIGGER AS $$
BEGIN
  IF NEW.pesel = 0 THEN
    RAISE EXCEPTION 'Niedozwolona wartość dla PESEL';     
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER prevent_zero_pesel
BEFORE INSERT OR UPDATE ON dane_pracownik
FOR EACH ROW
EXECUTE FUNCTION check_pesel();


CREATE FUNCTION domyslny()
RETURNS TRIGGER AS
'BEGIN
IF OLD.id_oddzial<>NEW.id_oddzial THEN
UPDATE dane_pracownik SET nazwa_ulicy=NULL; 
END IF;
RETURN NEW;
END;'
LANGUAGE 'plpgsql';

CREATE TRIGGER domyslny
AFTER UPDATE ON dane_pracownik
FOR EACH ROW EXECUTE PROCEDURE domyslny();
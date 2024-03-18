package com.example.demo.student;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
public class Faktura {

    @Id
    @SequenceGenerator(
            name = "faktura_sequence",
            sequenceName = "faktura_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "faktura_sequence"
    )
    private Long id;
    private String kupujący;
    private String sprzedający;
    private String numer_faktury;
    private LocalDate data_wystawienia;
    private String miejscowosc_wystawienia;
    private LocalDate data_zakonczenia_dostawy_uslugi;
    private LocalDate termin_Zaplaty;
    private String Nazwa_uslugi;
    private int ilosc;
    private String jednostka;
    private double cena_netto;
    private byte stawka_VAT;

    private double kwota_VAT;
    private double cena_brutto;

    private boolean czy_oplacone;
    private String sposob_zaplaty;

    Faktura(){

    }

    public Faktura(
                   String kupujący,
                   String sprzedający,
                   String numer_faktury,
                   LocalDate data_wystawienia,
                   String miejscowosc_wystawienia,
                   LocalDate data_zakonczenia_dostawy_uslugi,
                   LocalDate termin_Zaplaty,
                   String nazwa_uslugi,
                   int ilosc,
                   String jednostka,
                   double cena_netto,
                   byte stawka_VAT,
                   double kwota_VAT,
                   double cena_brutto,
                   boolean czy_oplacone,
                   String sposob_zaplaty) {
        this.kupujący=kupujący;
        this.sprzedający=sprzedający;
        this.numer_faktury = numer_faktury;
        this.data_wystawienia = data_wystawienia;
        this.miejscowosc_wystawienia = miejscowosc_wystawienia;
        this.data_zakonczenia_dostawy_uslugi = data_zakonczenia_dostawy_uslugi;
        this.termin_Zaplaty = termin_Zaplaty;
        this.Nazwa_uslugi = nazwa_uslugi;
        this.ilosc = ilosc;
        this.jednostka = jednostka;
        this.cena_netto = cena_netto;
        this.stawka_VAT = stawka_VAT;
        this.kwota_VAT = kwota_VAT;
        this.cena_brutto = cena_brutto;
        this.czy_oplacone = czy_oplacone;
        this.sposob_zaplaty = sposob_zaplaty;
    }

    public Faktura(Long id,
                   String kupujący,
                   String sprzedający,
                   String numer_faktury,
                   LocalDate data_wystawienia,
                   String miejscowosc_wystawienia,
                   LocalDate data_zakonczenia_dostawy_uslugi,
                   LocalDate termin_Zaplaty,
                   String nazwa_uslugi,
                   int ilosc,
                   String jednostka,
                   double cena_netto,
                   byte stawka_VAT,
                   double kwota_VAT,
                   double cena_brutto,
                   boolean czy_oplacone,
                   String sposob_zaplaty) {
        this.id = id;
        this.kupujący=kupujący;
        this.sprzedający=sprzedający;
        this.numer_faktury = numer_faktury;
        this.data_wystawienia = data_wystawienia;
        this.miejscowosc_wystawienia = miejscowosc_wystawienia;
        this.data_zakonczenia_dostawy_uslugi = data_zakonczenia_dostawy_uslugi;
        this.termin_Zaplaty = termin_Zaplaty;
        this.Nazwa_uslugi = nazwa_uslugi;
        this.ilosc = ilosc;
        this.jednostka = jednostka;
        this.cena_netto = cena_netto;
        this.stawka_VAT = stawka_VAT;
        this.kwota_VAT = kwota_VAT;
        this.cena_brutto = cena_brutto;
        this.czy_oplacone = czy_oplacone;
        this.sposob_zaplaty = sposob_zaplaty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumer_faktury() {
        return numer_faktury;
    }

    public void setNumer_faktury(String numer_faktury) {
        this.numer_faktury = numer_faktury;
    }

    public LocalDate getData_wystawienia() {
        return data_wystawienia;
    }

    public void setData_wystawienia(LocalDate data_wystawienia) {
        this.data_wystawienia = data_wystawienia;
    }

    public String getMiejscowosc_wystawienia() {
        return miejscowosc_wystawienia;
    }

    public void setMiejscowosc_wystawienia(String miejscowosc_wystawienia) {
        this.miejscowosc_wystawienia = miejscowosc_wystawienia;
    }
    public LocalDate getData_zakonczenia_dostawy_uslugi() {
        return data_zakonczenia_dostawy_uslugi;
    }

    public void setData_zakonczenia_dostawy_uslugi(LocalDate data_zakonczenia_dostawy_uslugi) {
        this.data_zakonczenia_dostawy_uslugi = data_zakonczenia_dostawy_uslugi;
    }
    public LocalDate getTermin_Zaplaty() {
        return termin_Zaplaty;
    }

    public void setTermin_Zaplaty(LocalDate termin_Zaplaty) {
        this.termin_Zaplaty = termin_Zaplaty;
    }

    public String getNazwa_uslugi() {
        return Nazwa_uslugi;
    }

    public void setNazwa_uslugi(String nazwa_uslugi) {
        Nazwa_uslugi = nazwa_uslugi;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getJednostka() {
        return jednostka;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }

    public double getCena_netto() {
        return cena_netto;
    }

    public void setCena_netto(double cena_netto) {
        this.cena_netto = cena_netto;
    }

    public byte getStawka_VAT() {
        return stawka_VAT;
    }

    public void setStawka_VAT(byte stawka_VAT) {
        this.stawka_VAT = stawka_VAT;
    }

    public double getKwota_VAT() {
        return kwota_VAT;
    }

    public void setKwota_VAT(double kwota_VAT) {
        this.kwota_VAT = kwota_VAT;
    }

    public double getCena_brutto() {
        return cena_brutto;
    }

    public void setCena_brutto(double cena_brutto) {
        this.cena_brutto = cena_brutto;
    }

    public boolean isCzy_oplacone() {
        return czy_oplacone;
    }

    public void setCzy_oplacone(boolean czy_oplacone) {
        this.czy_oplacone = czy_oplacone;
    }

    public String getSposob_zaplaty() {
        return sposob_zaplaty;
    }

    public void setSposob_zaplaty(String sposob_zaplaty) {
        this.sposob_zaplaty = sposob_zaplaty;
    }

    public String getKupujący() {
        return kupujący;
    }

    public String getSprzedający() {
        return sprzedający;
    }

    @Override
    public String toString() {
        return "Faktura{" +
                "id=" + id +
                ", kupujący='" + kupujący + '\'' +
                ", sprzedający='" + sprzedający + '\'' +
                ", numer_faktury='" + numer_faktury + '\'' +
                ", data_wystawienia=" + data_wystawienia +
                ", miejscowosc_wystawienia='" + miejscowosc_wystawienia + '\'' +
                ", data_zakonczenia_dostawy_uslugi=" + data_zakonczenia_dostawy_uslugi +
                ", termin_Zaplaty=" + termin_Zaplaty +
                ", Nazwa_uslugi='" + Nazwa_uslugi + '\'' +
                ", ilosc=" + ilosc +
                ", jednostka='" + jednostka + '\'' +
                ", cena_netto=" + cena_netto +
                ", stawka_VAT=" + stawka_VAT +
                ", kwota_VAT=" + kwota_VAT +
                ", cena_brutto=" + cena_brutto +
                ", czy_oplacone=" + czy_oplacone +
                ", sposob_zaplaty='" + sposob_zaplaty + '\'' +
                '}';
    }
}


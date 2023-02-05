import java.io.Serializable;


public class Obiekt  implements Serializable {
    private String nazwa ;
    private int id;
    private String kaliber;
    private String firma;
    private int rokProdukcji;
    private String krajPochodzenia;

    private int numerRekordu;

    public Obiekt(String nazwa,
                int id,
                String kaliber,
                String firma,
                int rokProdukcji,
                String krajPochodzenia,
                  int numerRekordu ){
        this.nazwa=nazwa;
        this.id=id;
        this.kaliber=kaliber;
        this.firma=firma;
        this.rokProdukcji=rokProdukcji;
        this.krajPochodzenia=krajPochodzenia;
        this.numerRekordu=numerRekordu;
    }

    public Obiekt() {

    }
//gettery
    public int getId() {
        return id;
    }

    public int getNumerRekordu() {
        return numerRekordu;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public String getFirma() {
        return firma;
    }

    public String getKaliber() {
        return kaliber;
    }

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public String getNazwa() {
        return nazwa;
    }
//settery
    public void setFirma(String firma) {
        this.firma = firma;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKaliber(String kaliber) {
        this.kaliber = kaliber;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        this.krajPochodzenia = krajPochodzenia;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setNumerRekordu(int numerRekordu) {
        this.numerRekordu = numerRekordu;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }
}

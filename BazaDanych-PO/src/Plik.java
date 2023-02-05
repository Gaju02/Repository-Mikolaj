import java.io.*;

public class  Plik implements Serializable{

    public static void  zapiszPlik(Obiekt []obiekt, String name) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream outputStream = new ObjectOutputStream(fos);
        System.out.println("Wczytuje elementy kolejno do tablicy.");
        outputStream.writeObject(obiekt);
        outputStream.close();
        System.out.println("Testowe odczytanie z pliku");
        Obiekt[] tablicaObiektowZpliku = new Obiekt[6];
        FileInputStream fileIn = new FileInputStream(name);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        tablicaObiektowZpliku = (Obiekt[]) objectIn.readObject();
        objectIn.close();
        System.out.println("\n Odczytanie z pliku :" + name);
        for (int i = 0; i < tablicaObiektowZpliku.length; i++) {
            System.out.println(tablicaObiektowZpliku[i].getNazwa());
            System.out.println(tablicaObiektowZpliku[i].getFirma());
            System.out.println(tablicaObiektowZpliku[i].getId());
            System.out.println(tablicaObiektowZpliku[i].getNumerRekordu());
            System.out.println(tablicaObiektowZpliku[i].getRokProdukcji());
            System.out.println(tablicaObiektowZpliku[i].getKrajPochodzenia());

        }

    }
    public static void usunPlik(String fileName) {
        try {
            File f = new File(fileName);
            if (f.delete()) {
                System.out.println(f.getName()+ " zostal skasowany");
            }else{
                System.out.println("Operacja skasowania nie powiodla się.");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


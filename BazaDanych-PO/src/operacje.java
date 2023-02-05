import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

import static java.awt.SystemColor.menu;
import static java.lang.System.exit;

/*
3. zrobić ładne menu (Zapytać się czy to wgle możliwe)
 */
public class operacje {
        public static void otworzBaze() throws IOException, ClassNotFoundException {
                clss();
                Scanner scanner = new Scanner(System.in);
                System.out.println("OTWIERAM BAZE DANYCH");
                String znak;
                char ch;
                String ciag = "";
                char tab[] = new char[8];
                boolean popr = false;
                do {
                    System.out.flush();
                    for (int i = 0; i < 25; i++)
                        System.out.println();
                    System.out.print(" Podaj nazwe bazy danych xxxx.dat:");
                    ciag = scanner.next();
                    ciag.trim();
                    popr = sprawdzPoprawnosc(ciag);
                    System.out.println(" wynik=" + popr);
                    System.out.println(" S -stop, other continue");
                    String ws = scanner.next();

                    if (ws.charAt(0) == 's' || ws.charAt(0) == 'S')
                        System.exit(0);
                    if (popr) break;

                    if (popr == false) clss();
                }
                while (!popr);
                Obiekt[] tablicaObiektowZpliku = new Obiekt[6];
                FileInputStream fileIn = null;
                try {
                    fileIn = new FileInputStream(ciag);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ObjectInputStream objectIn = null;
                try {
                    objectIn = new ObjectInputStream(fileIn);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    tablicaObiektowZpliku = (Obiekt[]) objectIn.readObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("-----------------------------------------");
                for (int i = 0; i < tablicaObiektowZpliku.length; i++) {
                    System.out.println("Id: "+tablicaObiektowZpliku[i].getId());
                    System.out.println("Firma: "+tablicaObiektowZpliku[i].getFirma());
                    System.out.println("Kaliber: "+tablicaObiektowZpliku[i].getKaliber());
                    System.out.println("Nazwa: "+tablicaObiektowZpliku[i].getNazwa());
                    System.out.println("KrajPochodzenia: "+tablicaObiektowZpliku[i].getKrajPochodzenia());
                    System.out.println("NumerRekordu: "+tablicaObiektowZpliku[i].getNumerRekordu());
                    System.out.println("RokProdukcji: "+tablicaObiektowZpliku[i].getRokProdukcji());
                }
                objectIn.close();
                System.out.println("Pomyślnie wczytnano bazę danych. Naciśnij przycisk 'y' aby aby kontynuuować: ");
                boolean wcisniety = false;
                String c;
                while(!wcisniety){
                    c = scanner.next();
                    if(c.equals("y")||c.equals("Y")){
                        wcisniety=true;
                        break;
                    }
                    else {
                        System.out.println("Podano złą literkę,podaj ją jeszcze raz :");
                    }

                }
                clss();
            }

        public static void utworzNowaBaze() throws IOException, ClassNotFoundException {
            clss();
            Scanner scanner = new Scanner(System.in);
            System.out.println("TWORZE NOWA  BAZE DANYCH");
            String znak;
            char ch;
            String ciag ="";
            char tab[]=new char[8];
            boolean popr=false;
            do
            {

                System.out.flush();
                for (int i=0;i<25;i++)
                    System.out.println();
                System.out.print(" Podaj nazwe bazy danych xxxx.dat:");
                ciag=scanner.next();
                ciag.trim();
                popr=sprawdzPoprawnosc(ciag);
                System.out.println(" wynik="+popr);
                System.out.println(" S -stop, other continue");
                String ws=scanner.next();

                if (ws.charAt(0)=='s'||ws.charAt(0)=='S')
                    System.exit(0);
                if (popr) break;

                if (popr==false) clss();
            }
            while(!popr);
            clss();
            System.out.println("Nowa baza to : "+ciag);
            System.out.println("\n Podaj ilośc rekordow: ");
            while(!scanner.hasNextInt()){
                System.out.println("Podano bledna liczbe, podaj ja jeszcze raz :");
                scanner.next();
            }
            int ilosc = scanner.nextInt();
            Obiekt[] tablica = new Obiekt[ilosc];
            for(int i = 0 ; i < ilosc; i++){
                System.out.print("\n Rekord nr: "+i);
                System.out.println("nazwa: ");
                String nazwa = scanner.next();
                System.out.println("identyfikator: ");
                int id = scanner.nextInt();
                System.out.println("kaliber: ");
                String kaliber = scanner.next();
                System.out.println("firma: ");
                String firma = scanner.next();
                System.out.println("rokProdukcji: ");
                int rokProdukcji = scanner.nextInt();
                System.out.println("krajPochodzenia: ");
                String krajPochodzenia = scanner.next();
                Obiekt obiekt = new Obiekt(nazwa,id,kaliber,firma,rokProdukcji,krajPochodzenia,i);
                tablica[i]=obiekt;
                System.out.print("\n");
            }

            System.out.println("\n zapisane obiekty do tablicy");
            System.out.println("----------------------------------");
            for(int i = 0 ; i < ilosc; i++){
                System.out.println("Id: "+tablica[i].getId());
                System.out.println("Firma: "+tablica[i].getFirma());
                System.out.println("Kaliber: "+tablica[i].getKaliber());
                System.out.println("Nazwa: "+tablica[i].getNazwa());
                System.out.println("KrajPochodzenia: "+tablica[i].getKrajPochodzenia());
                System.out.println("NumerRekordu: "+tablica[i].getNumerRekordu());
                System.out.println("RokProdukcji: "+tablica[i].getRokProdukcji());
            };
            try {
                Plik.zapiszPlik(tablica,ciag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pomyślnie wczytnano bazę danych. Naciśnij przycisk 'y' aby aby kontynuuować: ");
            boolean wcisniety = false;
            String c;
            while(!wcisniety){
                c = scanner.next();
                if(c.equals("y")||c.equals("Y")){
                    wcisniety=true;
                    break;
                }
                else {
                    System.out.println("Podano złą literkę,podaj ją jeszcze raz :");
                }
                clss();
            }

        }
        public static void przegladBazy() throws IOException, ClassNotFoundException {
            clss();
            Scanner scanner = new Scanner(System.in);
            System.out.println("OTWIERAM BAZE DANYCH");
            String znak;
            char ch;
            String ciag ="";
            char tab[]=new char[8];
            boolean popr=false;
            do
            {

                System.out.flush();
                for (int i=0;i<25;i++)
                    System.out.println();
                System.out.print(" Podaj nazwe bazy danych xxxx.dat:");
                ciag=scanner.next();
                ciag.trim();
                popr=sprawdzPoprawnosc(ciag);
                System.out.println(" wynik="+popr);
                System.out.println(" S -stop, other continue");
                String ws=scanner.next();

                if (ws.charAt(0)=='s'||ws.charAt(0)=='S')
                    System.exit(0);
                if (popr) break;

                if (popr==false) clss();
            }
            while(!popr);
            clss();
            Obiekt [] tablicaObiektowZpliku = new Obiekt[6];
            FileInputStream fileIn = null;
            try {
                fileIn = new FileInputStream(ciag);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObjectInputStream objectIn = null;
            try {
                objectIn = new ObjectInputStream(fileIn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                tablicaObiektowZpliku=(Obiekt[]) objectIn.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            clss();
            for (int i = 0 ; i < tablicaObiektowZpliku.length; i++){
                System.out.println("Id: "+tablicaObiektowZpliku[i].getId());
                System.out.println("Firma: "+tablicaObiektowZpliku[i].getFirma());
                System.out.println("Kaliber: "+tablicaObiektowZpliku[i].getKaliber());
                System.out.println("Nazwa: "+tablicaObiektowZpliku[i].getNazwa());
                System.out.println("KrajPochodzenia: "+tablicaObiektowZpliku[i].getKrajPochodzenia());
                System.out.println("NumerRekordu: "+tablicaObiektowZpliku[i].getNumerRekordu());
                System.out.println("RokProdukcji: "+tablicaObiektowZpliku[i].getRokProdukcji());
            }
            try {
                objectIn.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Co chcesz zrobic? M.Zmodyfikować rekord w bazie" +
                    ", S.skasować rekord w bazie , D.dopisac rekord do bazy, P.posortować tablicę?");

            do {
                ch = (char) System.in.read();
                if (ch != 'M' && ch != 'D' && ch != 'S' && ch != 'P' ){
                    ch = '0';
                    break;
                }
            } while (ch != 'M' && ch != 'D' && ch != 'S' && ch != 'P');
            switch(ch) {
                case 'M':
                    System.out.println("Ktory rekord chcesz zmodyfikowac?");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Podano bledna liczbe, podaj ja jeszcze raz :");
                        scanner.next();
                    }
                    int choiceRecord = scanner.nextInt();
                    System.out.println("Co dokladnie chcesz zmodyfikowac?");
                    System.out.println("1.nazwa?");
                    System.out.println("2.kaliber?");
                    System.out.println("3. id");
                    System.out.println("4. firma?");
                    System.out.println("5. rokProdukcji?");
                    System.out.println("6. krajPochodzenia?");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Podano błędną liczbe, podaj ja jeszcze raz :");
                        scanner.next();
                    }
                    int choiceWhat = scanner.nextInt();
                    switch (choiceWhat) {
                        case 1:
                            System.out.println("Wybrano nazwa. Podaj wartosc:");
                            String nazwaNowa = scanner.next();
                            tablicaObiektowZpliku[choiceRecord - 1].setNazwa(nazwaNowa);
                            break;
                        case 2:
                            System.out.println("Wybrano kaliber. Podaj wartosc:");
                            String kaliber = scanner.next();
                            tablicaObiektowZpliku[choiceRecord - 1].setKaliber(kaliber);
                            break;
                        case 3:
                            System.out.println("Wybrano id. Podaj wartosc:");
                            int id = scanner.nextInt();
                            tablicaObiektowZpliku[choiceRecord - 1].setId(id);
                            break;
                        case 4:
                            System.out.println("Wybrano firma. Podaj wartosc:");
                            String firma = scanner.next();
                            tablicaObiektowZpliku[choiceRecord - 1].setFirma(firma);
                            break;
                        case 5:
                            System.out.println("Wybrano rokProdukcji. Podaj wartosc:");
                            int rokProduckji = scanner.nextInt();
                            tablicaObiektowZpliku[choiceRecord - 1].setRokProdukcji(rokProduckji);
                            break;
                        case 6:
                            System.out.println("Wybrano krajPochodzenia. Podaj wartosc:");
                            String krajPochodzenia = scanner.next();
                            tablicaObiektowZpliku[choiceRecord - 1].setKrajPochodzenia(krajPochodzenia);
                            break;
                    }
                    break;
                case 'D':
                    System.out.println("Gdzie chcesz dodać rekord ?");
                    System.out.println("1. Na początek bazy danych");
                    System.out.println("2. Na koniec bazy danych");
                    char c;
                    do {
                        c = scanner.next().charAt(0);
                        if (c != '1' && c != '2'){
                            c = '0';
                            break;
                        }
                    } while (c != '1' && c != '2' && c != 27);
                    switch (c){
                        case '1':
                            System.out.println("Wybrano dopisanie obiektu na początek  bazy danych.Podaj kolejno wartosci:");
                            tablicaObiektowZpliku = Arrays.copyOf(tablicaObiektowZpliku, 1 + tablicaObiektowZpliku.length);
                            System.out.println("Podaj kolejno wartosci dla nowego obiektu:");
                            System.out.println("nazwa: ");
                            String nazwa = scanner.next();
                            System.out.println("identyfikator: ");
                            int id = scanner.nextInt();
                            System.out.println("kaliber: ");
                            String kaliber = scanner.next();
                            System.out.println("firma: ");
                            String firma = scanner.next();
                            System.out.println("rokProdukcji: ");
                            int rokProdukcji = scanner.nextInt();
                            System.out.println("krajPochodzenia: ");
                            String krajPochodzenia = scanner.next();
                            Obiekt obiekt = new Obiekt(nazwa, id, kaliber, firma, rokProdukcji, krajPochodzenia, tablicaObiektowZpliku.length - 1);
                            tablicaObiektowZpliku[tablicaObiektowZpliku.length - 1] = tablicaObiektowZpliku[0];
                            tablicaObiektowZpliku[0]=obiekt;
                            System.out.print("\n");
                            break;

                        case '2':
                            System.out.println("Wybrano dopisanie obiektu na koniec bazy danych.Podaj kolejno wartosci:");
                            tablicaObiektowZpliku = Arrays.copyOf(tablicaObiektowZpliku, 1 + tablicaObiektowZpliku.length);
                            System.out.println("Podaj kolejno wartosci dla nowego obiektu:");
                            System.out.println("nazwa: ");
                            nazwa = scanner.next();
                            System.out.println("identyfikator: ");
                            id = scanner.nextInt();
                            System.out.println("kaliber: ");
                            kaliber = scanner.next();
                            System.out.println("firma: ");
                            firma = scanner.next();
                            System.out.println("rokProdukcji: ");
                            rokProdukcji = scanner.nextInt();
                            System.out.println("krajPochodzenia: ");
                            krajPochodzenia = scanner.next();
                            obiekt = new Obiekt(nazwa, id, kaliber, firma, rokProdukcji, krajPochodzenia, tablicaObiektowZpliku.length - 1);
                            tablicaObiektowZpliku[tablicaObiektowZpliku.length - 1] = obiekt;
                            System.out.print("\n");
                            break;
                    }
                    break;
                case 'S':
                    clss();
                    System.out.println("Skasowanie rekordu w bazie");
                    System.out.println("Ktory rekord chcesz usunac?");
                    Obiekt[]tempTab= new Obiekt[tablicaObiektowZpliku.length-1];
                    while(!scanner.hasNextInt() ){
                        System.out.println("Podano bledna liczbe, podaj ja jeszcze raz :");
                        scanner.next();
                    }
                    int choiceToDelete = scanner.nextInt();
                    for(int i = 0 ; i<tablicaObiektowZpliku.length; i++){
                        if (i == choiceToDelete-1)
                            continue;
                        tempTab[i]=tablicaObiektowZpliku[i];

                    }
                    tablicaObiektowZpliku=Arrays.copyOf(tempTab,tempTab.length);
                    break;
                case 'P':
                    clss();
                    System.out.println("Wybrano operację sortowania. Według czego chcesz posortwać tablice?");
                    System.out.println("1.nazwa?");
                    System.out.println("2.kaliber?");
                    System.out.println("3. id");
                    System.out.println("4. firma?");
                    System.out.println("5. rokProdukcji?");
                    System.out.println("6. krajPochodzenia?");
                    while(!scanner.hasNextInt() ){
                        System.out.println("Podano błedna liczbe, podaj ja jeszcze raz :");
                        scanner.next();
                    }
                    int choiceToSort = scanner.nextInt();
                    Obiekt temp = new Obiekt();
                    switch(choiceToSort){
                        case 1:
                            System.out.println("Wybrano nazwę");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getNazwa().compareTo(tablicaObiektowZpliku[j].getNazwa()) != 0 ) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Wybrano kaliber");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getKaliber().compareTo(tablicaObiektowZpliku[j].getKaliber()) != 0 ) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;

                        case 3:
                            System.out.println("wybrano id");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getId() > tablicaObiektowZpliku[j].getId()) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;

                        case 4:
                            System.out.println("wybrano firma");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getFirma().compareTo(tablicaObiektowZpliku[j].getFirma()) != 0 ) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;

                        case 5:
                            System.out.println("wybrano rokProdukcji");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getRokProdukcji()>tablicaObiektowZpliku[j].getRokProdukcji() ) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;

                        case 6:
                            System.out.println("wybrano krajPochodzenia");
                            for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                                for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                                    if (tablicaObiektowZpliku[j+1].getKrajPochodzenia().compareTo(tablicaObiektowZpliku[j].getKrajPochodzenia()) != 0 ) {
                                        temp = tablicaObiektowZpliku[j];
                                        tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                        tablicaObiektowZpliku[j+1]=temp;
                                    }
                                }
                            }
                            break;


                    }
                    break;
                default: {
                    System.out.println(ch);
                    System.out.println("Pobrano zła wartosc, podaj ją jeszcze raz ");
                }
            }
            try {
                Plik.zapiszPlik(tablicaObiektowZpliku,ciag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pomyślnie wczytnano bazę danych. Naciśnij przycisk 'y' aby aby kontynuuować: ");
            boolean wcisniety = false;
            String c;
            while(!wcisniety){
                c = scanner.next();
                if(c.equals("y")||c.equals("Y")){
                    wcisniety=true;
                    break;
                }
                else {
                    System.out.println("Podano złą literkę,podaj ją jeszcze raz :");
                }

            }
            clss();

        }

        public static void sortowanieBazy(){
            clss();
            Scanner scanner = new Scanner(System.in);
            System.out.println("OTWIERAM BAZE DANYCH");
            String znak;
            char ch;
            String ciag ="";
            char tab[]=new char[8];
            boolean popr=false;
            do
            {

                System.out.flush();
                for (int i=0;i<25;i++)
                    System.out.println();
                System.out.print(" Podaj nazwe bazy danych xxxx.dat:");
                ciag=scanner.next();
                ciag.trim();
                popr=sprawdzPoprawnosc(ciag);
                System.out.println(" wynik="+popr);
                System.out.println(" S -stop, other continue");
                String ws=scanner.next();

                if (ws.charAt(0)=='s'||ws.charAt(0)=='S')
                    System.exit(0);
                if (popr) break;

                if (popr==false) clss();
            }
            while(!popr);
            clss();
            Obiekt [] tablicaObiektowZpliku = new Obiekt[6];
            FileInputStream fileIn = null;
            try {
                fileIn = new FileInputStream(ciag);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            ObjectInputStream objectIn = null;
            try {
                objectIn = new ObjectInputStream(fileIn);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                tablicaObiektowZpliku=(Obiekt[]) objectIn.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0 ; i < tablicaObiektowZpliku.length; i++){
                System.out.println("Id: "+tablicaObiektowZpliku[i].getId());
                System.out.println("Firma: "+tablicaObiektowZpliku[i].getFirma());
                System.out.println("Kaliber: "+tablicaObiektowZpliku[i].getKaliber());
                System.out.println("Nazwa: "+tablicaObiektowZpliku[i].getNazwa());
                System.out.println("KrajPochodzenia: "+tablicaObiektowZpliku[i].getKrajPochodzenia());
                System.out.println("NumerRekordu: "+tablicaObiektowZpliku[i].getNumerRekordu());
                System.out.println("RokProdukcji: "+tablicaObiektowZpliku[i].getRokProdukcji());
            }
            try {
                objectIn.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clss();
            System.out.println("Wybrano operację sortowania. Według czego chcesz posortwac tablicę?");
            System.out.println("1.nazwa?");
            System.out.println("2.kaliber?");
            System.out.println("3. id");
            System.out.println("4. firma?");
            System.out.println("5. rokProdukcji?");
            System.out.println("6. krajPochodzenia?");
            while(!scanner.hasNextInt() ){
                System.out.println("Podano bledna liczbe, podaj ja jeszcze raz :");
                scanner.next();
            }
            int choiceToSort = scanner.nextInt();
            Obiekt temp ;
            switch(choiceToSort){
                case 1:
                    System.out.println("Wybrano nazwę");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getNazwa().compareTo(tablicaObiektowZpliku[j].getNazwa()) != 0 ) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;

                case 2:
                    System.out.println("Wybrano kaliber");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getKaliber().compareTo(tablicaObiektowZpliku[j].getKaliber()) != 0 ) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("wybrano id");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getId() > tablicaObiektowZpliku[j].getId()) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.println("wybrano firma");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getFirma().compareTo(tablicaObiektowZpliku[j].getFirma()) != 0 ) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.println("wybrano rokProdukcji");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getRokProdukcji()>tablicaObiektowZpliku[j].getRokProdukcji() ) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("wybrano krajPochodzenia");
                    for ( int i = 1 ; i< tablicaObiektowZpliku.length; i++){
                        for (int j = 0 ; j< tablicaObiektowZpliku.length-i ; j++){
                            if (tablicaObiektowZpliku[j+1].getKrajPochodzenia().compareTo(tablicaObiektowZpliku[j].getKrajPochodzenia()) != 0 ) {
                                temp = tablicaObiektowZpliku[j];
                                tablicaObiektowZpliku[j]=tablicaObiektowZpliku[j+1];
                                tablicaObiektowZpliku[j+1]=temp;
                            }
                        }
                    }
                    break;
            }
            try {
                Plik.zapiszPlik(tablicaObiektowZpliku,ciag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pomyślnie wczytnano bazę danych. Naciśnij przycisk 'y' aby aby kontynuuować: ");
            boolean wcisniety = false;
            String c;
            while(!wcisniety){
                c = scanner.next();
                if(c.equals("y")||c.equals("Y")){
                    wcisniety=true;
                    break;
                }
                else {
                    System.out.println("Podano złą literkę,podaj ją jeszcze raz :");
                }

            }
            clss();

        }
        public static void usunBaze(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("USUN BAZE DANYCH");
            String znak;
            char ch;
            String ciag ="";
            char tab[]=new char[8];
            boolean popr=false;
            do
            {

                System.out.flush();
                for (int i=0;i<25;i++)
                    System.out.println();
                System.out.print(" Podaj nazwe bazy danych xxxx.dat:");
                ciag=scanner.next();
                ciag.trim();
                popr=sprawdzPoprawnosc(ciag);
                System.out.println(" wynik="+popr);
                System.out.println(" S -stop, other continue");
                String ws=scanner.next();

                if (ws.charAt(0)=='s'||ws.charAt(0)=='S')
                    System.exit(0);
                if (popr) break;

                if (popr==false) clss();
            }
            while(!popr);
            System.out.println("Nowa baza to : "+ciag);
            Plik.usunPlik(ciag);
            System.out.println("Pomyślnie usunieto bazę danych. Naciśnij przycisk 'y' aby kontynuuować: ");
            boolean wcisniety = false;
            String c;
            while(!wcisniety){
                c = scanner.next();
                if(c.equals("y")||c.equals("Y")){
                    wcisniety=true;
                    break;
                }
                else {
                    System.out.println("Podano złą literkę,podaj ją jeszcze raz :");
                }

            }
            clss();
        }

    static boolean sprawdzPoprawnosc(String nazwaBazy)
    {
        // xxxx.dat
        boolean popr=true;
        int powt=0;
        int dl=nazwaBazy.length();
        System.out.println("Dlugosc lancucha="+dl);

        if(dl<5) {
            System.out.println(" Nazwa bazy zbyt krotka");
            return false;
        }

        String typ=".dat";
        String stDat=nazwaBazy.substring(dl-4);
        if (stDat.equals(typ)){
            System.out.println("poprawne rozszerzenie nazwy bazy");

        }
        else
        {
            System.out.println(" niepoprawne rozszerzenie nazwy bazy");
            popr=false;
        }
        String stNazwa=nazwaBazy.substring(0,dl-4);
        System.out.println(" Nazwa bazy:"+stNazwa);
        Character chC='A';
        char tab[]=new char[dl];
        tab=stNazwa.toCharArray();
        if (Character.isLetter(tab[0]))
            System.out.println(" Pierwszy znak nazwy jest litera");
        else
        {
            System.out.println(" Pierwszy znak nazwy nie jest litera");
                    popr=false;

        }
        for(int i=1;i<dl-4;i++)
            if (!Character.isLetterOrDigit(tab[i])){
                System.out.println(" Niepoprawny znak nazwy");
                popr=false;

            }
        return popr;
    }
    static boolean clss()
    {
        System.out.println(new String(new
                char[25]).replace("\0","\r\n"));
        return true;
    }

}



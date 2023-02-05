import java.lang.*;
import java.io.*;
import java.awt.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Menu extends operacje{
    public void start() throws IOException, ClassNotFoundException {
        while (true) {
            char ch;
            location("\n===========================");
            location("\n 1.Otworz baze danych");
            location("\n 2.Utworz nowa baze");
            location("\n 3.Przeglad bazy");
            location("\n 4.Sortowanie bazy");
            location("\n 5 Usun baze");
            location("\n 6.Zakoncz program");
            location("\n ===========================");
            location("\n Wybierz opcje : ");
            do {
                ch = (char) System.in.read();
                if (ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != 27){
                    ch = '0';
                    break;
                }
            } while (ch != '1' && ch != '2' && ch != '3' && ch != '4' && ch != '5' && ch != '6' && ch != 27);
            switch(ch) {
                case '1':
                    clss();
                    operacje.otworzBaze();
                    break;
                case '2':
                    clss();
                    operacje.utworzNowaBaze();
                    break;
                case '3':
                    clss();
                    operacje.przegladBazy();
                    break;
                case '4':
                    clss();
                    operacje.sortowanieBazy();
                    break;
                case '5':
                    clss();
                    operacje.usunBaze();
                    break;
                case '6':
                    clss();
                    exit(0);
                    break;
                default:{
                    System.out.println(ch);
                    System.out.println("Pobrano zła wartosc, podaj ją jeszcze raz ");
                    clss();
                    }
                }
            }
        }
    static boolean clss()
    {
        System.out.println(new String(new
                char[25]).replace("\0","\r\n"));
        return true;
    }
    public static void location( String text){
        int consoleWidth = 80; // szerokość konsoli
        int textWidth = text.length();
        int padding = (consoleWidth - textWidth) / 2;
        System.out.printf("%" + padding + "s%s%" + padding + "s", "", text, "");
    }
}


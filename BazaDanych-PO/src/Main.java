import java.io.IOException;

public class Main{
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        clss();
        System.out.println("\n Menu Loading");
        Thread.sleep(2000);
        Menu menu = new Menu();
        menu.start();
    }
    static boolean clss()
    {
        System.out.println(new String(new
                char[25]).replace("\0","\r\n"));
        return true;
    }

}
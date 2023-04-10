import java.util.Scanner;

public class Utility {
    public static String scanSTR(){
        Scanner scanner = new Scanner(System.in);
        String nama = scanner.nextLine();
        return nama;
    }
    public static int scanINT(){
        Scanner scanner = new Scanner(System.in);
        int nilai = scanner.nextInt();
        return nilai;
    }
}

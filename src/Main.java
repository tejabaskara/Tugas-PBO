import java.util.Scanner;

public class Main {
    String username, password;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("LOGIN");
        System.out.println("Username: ");
        String username = scanner.nextLine();

        System.out.println("Password: ");
        String password = scanner.nextLine();

//        System.out.println(username);
//        System.out.println(password);


        if ((username.equals("Admin") || username.equals("admin")) && password.equals("admin")){
            System.out.println("kamu admin");
        }else{
            System.out.println("User "+ username);
            Customer MyCustomer = new Customer();
            MyCustomer.myHello();
        }

//            Admin MyAdmin = new Admin();
//            MyAdmin.myHello();
    }
}
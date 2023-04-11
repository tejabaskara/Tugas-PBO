

public class Main {

    public static void main(String[] args) {
        Login();
    }
    public static void Login(){
        System.out.println("LOGIN");
        System.out.print("Username: ");
        String username = Utility.scanSTR();

        System.out.print("Password: ");
        String password = Utility.scanSTR();

//        System.out.println(username);
//        System.out.println(password);


        if (username.equals("admin") && password.equals("admin")) {

            Admin Admin = new Admin();
            Admin.MenuAdmin();

        } else {
            System.out.println("User " + username);
            Customer.menuCustomer();
        }
    }

}
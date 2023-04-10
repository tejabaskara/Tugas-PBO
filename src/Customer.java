

public class Customer {

    public static void menuCustomer(){

        System.out.println("\t\tMENU CUSTOMER");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Kembali Login");
        System.out.print("Masukkan pilihan: ");
        String pilihan = Utility.scanSTR();
        System.out.println(pilihan);
        switch (pilihan){
            case "1":
                System.out.println("Buat pesanan");
                break;
            case "2":
                System.out.println("Lihat pesanan");
                break;
            case "3":
                System.out.println("Kembali login");
                Main.Login();
                break;
            default:
                System.out.println("Anda salah memasukkan nilai");
                break;
        }


    }
}

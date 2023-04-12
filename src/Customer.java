import java.util.ArrayList;

public class Customer {
    public static ArrayList<ArrayList<String>> restaurant = Admin.restaurant;
    public static ArrayList<ArrayList<Integer>> hargaMenu = Admin.hargaMenu;
    public static ArrayList<String> alamat = Admin.alamat;

    public static void menuCustomer(){

        System.out.println("\t\tMENU CUSTOMER");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Kembali Login");
        System.out.print("Masukkan pilihan: ");
        String pilihan = Utility.scanSTR();
//        System.out.println(pilihan);
        switch (pilihan){
            case "1":
                pesanan();
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

    //BUAT PESANAN
//    private static void TampilanRestauran(){
//
//    }
//
    private static void pesanan(){
//        TampilanRestauran();
        int menuPesan = 0;
        int totalBayar = 0;


        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i).get(0));
        }
        System.out.println("0. Keluar");
        System.out.print("Pilih restauran untuk melihat lebih detail: ");
        int pilihan = Utility.scanINT();
        if (pilihan == 0){
            menuCustomer();
        } else {
            pilihan -= 1;
            System.out.println("Nama Restaurant: " + restaurant.get(pilihan).get(0));
            System.out.println("Alamat: " + alamat.get(pilihan));
//            System.out.println("a");
//            System.out.println(pilihan);
            do {
                System.out.printf("MENU:\t\t\tHARGA:\n");
                for (int i=1; i < restaurant.get(pilihan).size(); i++){
                    System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
                }
                System.out.println("0. Keluar");
                System.out.print("Masukkan pilihan menu yang akan dipesan: ");
                menuPesan = Utility.scanINT();
                if (menuPesan == 0){
                    System.out.println("Total harga: "+ totalBayar);
                    break;
                }else {
                    System.out.println("Menu: " + restaurant.get(pilihan).get(menuPesan));
                    int menu = menuPesan - 1 ;
                    System.out.println("Harga: " + hargaMenu.get(pilihan).get(menu)); //untuk index dalam mencari harga dari menu
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    int subtotal = banyak * hargaMenu.get(pilihan).get(menu);
                    System.out.println("Harga: " + subtotal);
                    totalBayar += subtotal;
                    System.out.println(menuPesan);
                }
            }while (true);
        }

    }

    //LIHAT PESANAN YANG UDAH DILAKUKAN






}

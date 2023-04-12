import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Admin {
    public static ArrayList<String> alamat = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> restaurant = new ArrayList< ArrayList <String>>();
    public static ArrayList<ArrayList<Integer>> hargaMenu = new ArrayList< ArrayList <Integer>>();
    private static int banyakRestauran=0;
    public static void MenuAdmin(){ // MENU UTAMA DALAM ADMIN SETELAH LOGIN
        System.out.println("MENU ADMIN");
        System.out.println("1. Lihat restaurant");
        System.out.println("2. Tambah restaurant");
        System.out.println("3. Hapus restaurant");
        System.out.println("4. Kembali ke login");
        System.out.print("Masukkan pilihan: ");
        String pilihan = Utility.scanSTR();
//        System.out.println(pilihan);
        switch (pilihan){
            case "1":
                System.out.println("LIHAT RESTAURANT");
                System.out.println("Daftar Restaurant");
                lihatRestauran();
                menuRestaurant();
//                MenuAdmin();
                break;
            case "2":
                TambahRestaurant();
                MenuAdmin();
                break;
            case "3":
                HapusRestauran();
                MenuAdmin();
                break;
            case "4":
                Main.Login();
                break;
            default:
                System.out.println("Anda salah memasukkan nilai");
                break;
        }
    }

    private static void TambahRestaurant(){ // TEMPAT MENAMBAHKAN MENU DENGAN INPUT STRING

        restaurant.add(new ArrayList<>());
        hargaMenu.add(new ArrayList<>());

        System.out.println("TAMBAH RESTAURANT");
        System.out.print("Masukkan nama restaurant: ");
        String tempNama = Utility.scanSTR();
        restaurant.get(banyakRestauran).add(0, tempNama);


        System.out.print("Masukkan alamat restaurant: ");
        String tempAlamat = Utility.scanSTR();
        alamat.add(tempAlamat);

//        System.out.print("Masukkan banyak menu di restaurant: ");
//        int banyakMenu = Utility.scanINT();
        int pilihan = 0;
        do {
            int i = 1;
//            System.out.print("masukkan menu: ");
//            String menu = Utility.scanSTR();
            String menu = "a";
            restaurant.get(banyakRestauran).add(i, menu);
//            System.out.print("masukkan harga: ");
//            int harga = Utility.scanINT();
            int harga = 2000;
            hargaMenu.get(banyakRestauran).add(i-1, harga);
            System.out.println("1. Tambah menu");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = Utility.scanINT();
            i+=1;
        }while (pilihan == 1);

//        for (int i = 1; i <= banyakMenu; i++){
//        }

//        System.out.println(restaurant);
//        System.out.println(alamat);
//        System.out.println(hargaMenu);
        System.out.println(restaurant.size());
        System.out.println(restaurant.get(banyakRestauran).size());
        banyakRestauran += 1;
    }

    public static void menuRestaurant(){ // UNTUK MELIHAT MENU DI DALAM RESTAURANT DALAM BAGIAN LIHAT RESTAURAN
        System.out.println("0. Keluar");
        System.out.print("Pilih restauran untuk melihat lebih detail: ");
        int pilihan = Utility.scanINT();
        if (pilihan == 0){
            MenuAdmin();
        } else {
            pilihan -= 1;
            System.out.println("Nama Restaurant: " + restaurant.get(pilihan).get(0));
            System.out.println("Alamat: " + alamat.get(pilihan));
//            System.out.println("a");
//            System.out.println(pilihan);
            System.out.printf("MENU:\t\t\tHARGA:\n");
            for (int i=1; i < restaurant.get(pilihan).size(); i++){
                System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
            }
        }
    }

    private static void lihatRestauran(){ // UNTUK MELIHAT RESTAURAN YANG ADA
        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i).get(0));
        }
    }

    public static void HapusRestauran(){ // UNTUK MENGHAPUS RESTURAN BESERTA MENU MENU YANG ADA
        System.out.println("HAPUS RESTAURANT");
        System.out.println("Daftar Restaurant");
        lihatRestauran();
        System.out.print("Pilih yang ingin dihapus");
        int pilihan = Utility.scanINT();
        restaurant.remove(pilihan-1);
    }


}

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Admin {
    public static ArrayList<String> alamat = new ArrayList<String>();
    public static ArrayList<String> restaurant = new ArrayList<>();
    public static ArrayList<ArrayList<String>> menuMakanan = new ArrayList<>();
    public static ArrayList<ArrayList<String>> menuMinuman = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> hargaMakanan = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> hargaMinuman = new ArrayList<>();

    private static int banyakRestauran=0;
    public static void MenuAdmin(){ // MENU UTAMA DALAM ADMIN SETELAH LOGIN
        System.out.println("MENU ADMIN");
        System.out.println("1. Lihat restaurant");
        System.out.println("2. Tambah restaurant");
        System.out.println("3. Hapus restaurant");
        System.out.println("4. Kembali ke login");
        System.out.print("Masukkan pilihan: ");
        String pilihan = Utility.scanSTR();
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

        hargaMakanan.add(new ArrayList<>());
        hargaMinuman.add(new ArrayList<>());
        menuMakanan.add(new ArrayList<>());
        menuMinuman.add(new ArrayList<>());


        System.out.println("TAMBAH RESTAURANT");
        System.out.print("Masukkan nama restaurant: ");
        String tempNama = Utility.scanSTR();
        restaurant.add(tempNama);


        System.out.print("Masukkan alamat restaurant: ");
        String tempAlamat = Utility.scanSTR();
        alamat.add(tempAlamat);
        System.out.print("\033[H\033[2J");
        System.out.flush();

//        System.out.print("Masukkan banyak menu di restaurant: ");
//        int banyakMenu = Utility.scanINT();
        int pilihan = 0;
        int ma = 0;
        int mi = 0;
        do {
            System.out.println("1. Tambah menu makanan");
            System.out.println("2. Tambah menu minuman");
            System.out.print("Masukkan pilihan: ");
            int menu = Utility.scanINT();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (menu == 1){
//                System.out.println(ma);
                System.out.print("Masukkan nama makanan: ");
                String makanan = Utility.scanSTR();
//            String menu = "a";
                menuMakanan.get(banyakRestauran).add(ma, makanan);
                System.out.print("Masukkan harga: ");
                int harga = Utility.scanINT();
//            int harga = 2000;
                hargaMakanan.get(banyakRestauran).add(ma, harga);
                ma += 1;
            }else if (menu == 2){
//                System.out.println(mi);
                System.out.print("Masukkan nama minuman: ");
                String minuman = Utility.scanSTR();
//            String menu = "a";
                menuMinuman.get(banyakRestauran).add(mi, minuman);
                System.out.print("Masukkan harga: ");
                int harga = Utility.scanINT();
//            int harga = 2000;
                hargaMinuman.get(banyakRestauran).add(mi, harga);
                mi += 1;
            }else {
                System.out.println("Anda memasukkan angka yang salah");
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();
            lihatMenu(banyakRestauran);

            System.out.println();
            System.out.println("1. Tambah menu");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan: ");
            pilihan = Utility.scanINT();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }while (pilihan != 0);

//        for (int i = 1; i <= banyakMenu; i++){
//        }

//        System.out.println(restaurant);
//        System.out.println(alamat);
//        System.out.println(hargaMenu);
//        System.out.println(restaurant.size());
//        System.out.println(restaurant.get(banyakRestauran).size());
        banyakRestauran += 1;
    }

    private static void menuRestaurant(){ // UNTUK MELIHAT MENU DI DALAM RESTAURANT DALAM BAGIAN LIHAT RESTAURAN
        System.out.println("0. Keluar");
        System.out.print("Pilih restauran untuk melihat lebih detail: ");
        int pilihan = Utility.scanINT();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (pilihan == 0){
            MenuAdmin();
        } else {
            int nilaiKirim = pilihan - 1;
            System.out.println(nilaiKirim);
            System.out.println("Nama Restaurant: " + restaurant.get(nilaiKirim));
            System.out.println("Alamat: " + alamat.get(nilaiKirim));
//            System.out.println("a");
//            System.out.println(pilihan);
            lihatMenu(nilaiKirim);
            System.out.println("MASUKKAN 0 UNTUK KELUAR");
            int keluar = Utility.scanINT();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (keluar == 0){
                MenuAdmin();
            }
        }
    }

    private static void lihatRestauran(){ // UNTUK MELIHAT RESTAURAN YANG ADA
        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i));
        }
    }

    public static void  lihatMenu(int restauran){
        System.out.println("MENU");
        System.out.println();
        if (menuMakanan.get(restauran).size() != 0 && menuMinuman.get(restauran).size() != 0){
            System.out.println("MAKANAN");
            System.out.printf("MENU:\t\t\tHARGA:\n");
            for (int nomerMakanan = 0; nomerMakanan < menuMakanan.get(restauran).size(); nomerMakanan++){
                System.out.printf("%d. %s\t\t\t%s\n",nomerMakanan+1, menuMakanan.get(restauran).get(nomerMakanan), hargaMakanan.get(restauran).get(nomerMakanan));
            }
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("MENU:\t\t\tHARGA:\n");
            for (int nomerMinuman = 0; nomerMinuman < menuMinuman.get(restauran).size(); nomerMinuman++){
                System.out.printf("%d. %s\t\t\t%s\n",nomerMinuman+1, menuMinuman.get(restauran).get(nomerMinuman), hargaMinuman.get(restauran).get(nomerMinuman));
            }
        } else if (menuMakanan.get(restauran).size() != 0 && menuMinuman.get(restauran).size() == 0) {
            System.out.println("MAKANAN");
            System.out.printf("MENU:\t\t\tHARGA:\n");
            for (int nomerMakanan = 0; nomerMakanan < menuMakanan.get(restauran).size(); nomerMakanan++){
                System.out.printf("%d. %s\t\t\t%s\n",nomerMakanan+1, menuMakanan.get(restauran).get(nomerMakanan), hargaMakanan.get(restauran).get(nomerMakanan));
            }
        } else if (menuMakanan.get(restauran).size() == 0 && menuMinuman.get(restauran).size() != 0) {
            System.out.println("MINUMAN");
            System.out.printf("MENU:\t\t\tHARGA:\n");
            for (int nomerMinuman = 0; nomerMinuman < menuMinuman.get(restauran).size(); nomerMinuman++){
                System.out.printf("%d. %s\t\t\t%s\n",nomerMinuman+1, menuMinuman.get(restauran).get(nomerMinuman), hargaMinuman.get(restauran).get(nomerMinuman));
            }
        }
    }

    public static void HapusRestauran(){ // UNTUK MENGHAPUS RESTURAN BESERTA MENU MENU YANG ADA
        System.out.println("HAPUS RESTAURANT");
        System.out.println("Daftar Restaurant");
        lihatRestauran();
        System.out.print("Pilih yang ingin dihapus");
        int pilihan = Utility.scanINT();
        restaurant.remove(pilihan-1);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}

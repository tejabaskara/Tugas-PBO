import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
public class Admin {
    private static ArrayList<String> alamat = new ArrayList<String>();
    private static ArrayList<ArrayList<String>> restaurant = new ArrayList< ArrayList <String>>();
    public static void MenuAdmin(){
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
                MenuAdmin();
                break;
            case "2":
                TambahMenu();
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

    private static void TambahMenu(){
        System.out.println("TAMBAH MENU");
        System.out.print("Masukkan nama restaurant: ");
        String tempNama = Utility.scanSTR();
        restaurant.add(new ArrayList<>());
        restaurant.get(0).add(0, tempNama);

        System.out.print("Masukkan alamat restaurant: ");
        String tempAlamat = Utility.scanSTR();
        alamat.add(tempAlamat);

        System.out.print("Masukkan banyak menu di restaurant: ");
        int banyakMenu = Utility.scanINT();
        for (int i = 1; i <= banyakMenu; i++){
//            String menu = Utility.scanSTR();
            String menu = "a";
            restaurant.get(0).add(i, menu);
        }

        System.out.println(restaurant);
        System.out.println(alamat);
        System.out.println(restaurant.size());

    }

    private static void lihatRestauran(){
        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i).get(0));
        }
    }

    public static void HapusRestauran(){
        System.out.println("HAPUS RESTAURANT");
        System.out.println("Daftar Restaurant");
        lihatRestauran();
        System.out.print("Pilih yang ingin dihapus");
        int pilihan = Utility.scanINT();
        restaurant.remove(pilihan-1);
    }


}

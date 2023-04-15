import javax.net.ssl.SSLContext;
import javax.sound.midi.MidiFileFormat;
import java.util.ArrayList;

public class Customer {
    public static ArrayList<String> alamat = Admin.alamat;
    public static ArrayList<Integer> jarakRestauran = new ArrayList<>();
    public static ArrayList<String> restaurant = Admin.restaurant;
    public static ArrayList<ArrayList<String>> menuMakanan = Admin.menuMakanan;
    public static ArrayList<ArrayList<String>> menuMinuman = Admin.menuMinuman;
    public static ArrayList<ArrayList<Integer>> hargaMakanan = Admin.hargaMakanan;
    public static ArrayList<ArrayList<Integer>> hargaMinuman = Admin.hargaMinuman;
    public static ArrayList<ArrayList<Integer>> pilihanMenuMakanan = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> pilihanMenuMinuman = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> banyakPesananMakanan = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> banyakPesananMinuman = new ArrayList<>();
    public static ArrayList<Integer> pilihanRestauran = new ArrayList<>();
    public static int pesanan= 0;
    public static int ma = 0;
    public static int mi = 0;

    public static void menuCustomer(){

        System.out.println("\t\tMENU CUSTOMER");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Kembali Login");
        System.out.print("Masukkan pilihan: ");
        String pilihan = Utility.scanSTR();
        System.out.print("\033[H\033[2J");
        System.out.flush();
//        System.out.println(pilihan);
        switch (pilihan){
            case "1":
                pesanan();
                menuCustomer();
                break;
            case "2":
                lihatPesanan();
                menuCustomer();
                break;
            case "3":
                Main.Login();
                break;
            default:
                System.out.println("Anda salah memasukkan nilai");
                menuCustomer();
                break;
        }


    }

    //BUAT PESANAN

    private static void pesanan(){

        int menuPesan = 0; // untuk memilih menu yang akan dipesan user
        int totalBayar = 0; // menghitung total yang harus dibayar user
        int urutanMakanan =0; //untuk menghitung banyaknya pilihan makanan yang sudah dilakukan
        int urutanMinuman =0;

        pilihanMenuMakanan.add(new ArrayList<>());
        pilihanMenuMinuman.add(new ArrayList<>());
        banyakPesananMakanan.add(new ArrayList<>());
        banyakPesananMinuman.add(new ArrayList<>());

//        System.out.println(pesanan);

        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i));
        }

        System.out.println("0. Keluar");
        System.out.print("Pilih restauran yang akan dipesan: ");
        int pilihan = Utility.scanINT();
        pilihanRestauran.add(pilihan); // memasukkan nilai index restaurant yang ingin dipesan

        System.out.print("Masukkan jarak rumah anda dalam KM: ");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int jarak = Utility.scanINT();
        jarakRestauran.add(jarak);
        int ongkir = jarak * 2000;
        if (pilihan == 0){
            menuCustomer();
        } else {
            pilihan -= 1;
//            System.out.println("a");
//            System.out.println(pilihan);
            do {
                System.out.println("MENU RESTAURANT");
                System.out.println("Nama Restaurant: " + restaurant.get(pilihan));
                System.out.println("Alamat: " + alamat.get(pilihan));
//                System.out.printf("\nMENU:\t\t\tHARGA:\n");
//                for (int i=1; i < restaurant.get(pilihan).size(); i++){
//                    System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
//                }
                Admin.lihatMenu(pilihan);
                System.out.printf("\n\n");
                if (menuMakanan.get(pilihan).size() != 0 && menuMinuman.get(pilihan).size() != 0){
                    System.out.println("1. Tambah pesanan makanan");
                    System.out.println("2. Tambah pesanan minuman");
                    System.out.println("0. Lihat pesanan");
                    System.out.print("Masukkan pilihan anda: ");
                } else if (menuMakanan.get(pilihan).size() != 0 && menuMinuman.get(pilihan).size() == 0) {
                    System.out.println("1. Tambah pesanan makanan");
                    System.out.println("0. Lihat pesanan");
                    System.out.print("Masukkan pilihan anda: ");
                } else if (menuMakanan.get(pilihan).size() == 0 && menuMinuman.get(pilihan).size() != 0) {
                    System.out.println("1. Tambah pesanan minuman");
                    System.out.println("0. Lihat pesanan");
                    System.out.print("Masukkan pilihan anda: ");
                }
                menuPesan = Utility.scanINT();
                System.out.print("\033[H\033[2J");
                System.out.flush();



                if (menuPesan == 0){
                    int totalPlusOngkir = totalBayar + ongkir;
//                    System.out.println("Total harga: "+ totalPlusOngkir);
                    int riwayat = pesanan;
//                    System.out.println(riwayat);
//                    System.out.println(restaurant);
//                    System.out.println(banyakPesanan);
//                    System.out.println(pilihanMenu);
                    pesanan+=1;
                    int makanan = ma;
                    int minuman = mi;
                    pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat, jarak, makanan, minuman);
                    if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
                        ma += 1;
                        mi += 1;
                    } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
                        mi += 1;
                    } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
                        ma +=1;
                    }
                    break;
                } else if (menuPesan == 1) {
                    Admin.lihatMenu(pilihan);
                    System.out.print("Masukkan nomor pilihan makanan anda: ");
                    int pilihanMakanan = Utility.scanINT();
                    pilihanMakanan -= 1;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    if (urutanMakanan == 0){
                        pilihanMenuMakanan.get(ma).add(0, pesanan);
                    }

                    pilihanMenuMakanan.get(ma).add(urutanMakanan, pilihanMakanan);
                    urutanMakanan += 1;
                    System.out.println("Nama : " + menuMakanan.get(pilihan).get(pilihanMakanan));
                    System.out.println("Harga: " + hargaMakanan.get(pilihan).get(pilihanMakanan));
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    int urutanBanyak = urutanMakanan - 1;
                    banyakPesananMakanan.get(ma).add(urutanBanyak, banyak);
                    int subtotal = banyak * hargaMakanan.get(pilihan).get(pilihanMakanan);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalBayar += subtotal;
                } else if (menuPesan == 2) {
                    Admin.lihatMenu(pilihan);
                    System.out.print("Masukkan nomor pilihan minuman anda: ");
                    int pilihanMinuman = Utility.scanINT();
                    pilihanMinuman -= 1;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    if (urutanMakanan == 0){
                        pilihanMenuMinuman.get(mi).add(0, pesanan);
                    }

                    pilihanMenuMinuman.get(mi).add(urutanMinuman, pilihanMinuman);
                    urutanMinuman += 1;
                    System.out.println("Nama : " + menuMinuman.get(pilihan).get(pilihanMinuman));
                    System.out.println("Harga: " + hargaMinuman.get(pilihan).get(pilihanMinuman));
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    int urutanBanyak = urutanMinuman - 1;
                    banyakPesananMakanan.get(mi).add(urutanBanyak, banyak);
                    int subtotal = banyak * hargaMinuman.get(pilihan).get(pilihanMinuman);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalBayar += subtotal;
                } else {
                    System.out.println("INPUT ANDA SALAH");
                }
            }while (true);
        }

    }

    private static void tampilanPesanan(int ongkir, int riwayat, int jarak, int makanan, int minuman){//riwayat == pesanan yang akan ditampilkan
        int pesananMakanan = 0;
        int pesananMinuman = 0;
        if (pilihanMenuMakanan.get(makanan).size() != 0 && pilihanMenuMinuman.get(minuman).size() != 0){
            pesananMakanan = pilihanMenuMakanan.get(makanan).get(0);
            pesananMinuman = pilihanMenuMinuman.get(minuman).get(0);

        } else if (pilihanMenuMakanan.get(makanan).size() == 0 && pilihanMenuMinuman.get(minuman).size() != 0){
            pesananMinuman = pilihanMenuMinuman.get(minuman).get(0);
        } else if (pilihanMenuMinuman.get(minuman).size() == 0 && pilihanMenuMakanan.get(makanan).size() != 0) {
            pesananMakanan = pilihanMenuMakanan.get(makanan).get(0);
        }
//        System.out.println("lewat if-else pertama");

        if (pilihanMenuMakanan.get(makanan).size() != 0 && pilihanMenuMinuman.get(minuman).size() != 0 && riwayat == pesananMakanan && riwayat == pesananMinuman){
            // user memesan makanan dan minuman

            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(makanan).size() - 1; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(makanan).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(makanan).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(makanan).get(i), subtotal);
            }
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMinuman.get(minuman).size() -1 ; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(minuman).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(minuman).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(minuman).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.get(makanan).size() != 0 && pilihanMenuMinuman.get(minuman).size() != 0 && riwayat != pesananMakanan && riwayat == pesananMinuman) {
            // user tidak memesan makanan
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMinuman.get(minuman).size() -1 ; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(minuman).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(minuman).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(minuman).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.get(makanan).size() != 0 && pilihanMenuMinuman.get(minuman).size() != 0 && riwayat == pesananMakanan && riwayat != pesananMinuman) {
            // user tidak memesan minuman
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(makanan).size() -1; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(makanan).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(makanan).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(makanan).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.get(makanan).size() == 0 && pilihanMenuMinuman.get(minuman).size() != 0 && riwayat == pesananMinuman){
            // hanya memesan minuman saat pesanan pertamanya
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMinuman.get(minuman).size() -1; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(minuman).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(minuman).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(minuman).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMinuman.get(minuman).size() == 0 && pilihanMenuMakanan.get(makanan).size() != 0 && riwayat == pesananMakanan) {
            // hanya memesan makanan saat pesanan pertamanya
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(makanan).size() -1; i++ ){
                int nomerData = i+1;
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(makanan).get(nomerData); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(makanan).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", nomerData ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(makanan).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        }

    }



    private static void pengecekanSebelumBayar(int total, int ongkir, int riwayat, int jarak, int makanan, int minuman){
        System.out.println(riwayat);
        tampilanPesanan(ongkir, riwayat, jarak, makanan, minuman);
        System.out.println("TOTAL: " + total);
        System.out.println();

        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Bayar");
        System.out.println("0. Batalkan Pesanan");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int pilihan = Utility.scanINT();
        switch (pilihan){
            case 1:
                int restauranId = pilihanRestauran.get(riwayat) -1;
//                lihatMenu(restauranId);
                tambahPesanan(restauranId, riwayat, total, ongkir, jarak, makanan, minuman);
                break;
            case 2:
                bayar(total, ongkir, riwayat, jarak, makanan, minuman);
                break;
            case 0:
                menuCustomer();
                break;
            default:
                pengecekanSebelumBayar(total, ongkir, riwayat, jarak, makanan, minuman);
                break;
        }
    }

    private static void bayar(int total, int ongkir, int riwayat, int jarak, int makanan, int minuman){
        tampilanPesanan(ongkir, riwayat, jarak, makanan, minuman);
        System.out.printf("TOTAL: %d\n", total);
        System.out.println();
        System.out.println("Metode pembayaran");
        System.out.println("1. TUNAI");
        System.out.println("2. Online");
        System.out.println();
        System.out.print("Masukkan pilihan: ");
        int pilihan = Utility.scanINT();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch (pilihan){
            case 1:
                System.out.println("SILAHKAN BAYAR PADA PENGIRIM");
                System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN JASA KAMI");
                System.out.println();
                break;
            case 2:
                System.out.println("SILAHKAN BAYAR DENGAN e-WALLET ANDA");
                System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN JASA KAMI");
                System.out.println();
                break;
            default:
                System.out.println("SILAHKAN MENGULANGI MEMASUKKAN PILIHAN ANDA!");
                System.out.println();
                bayar(total, ongkir, riwayat, jarak, makanan, minuman);
                break;
        }
        menuCustomer();
    }
//
//
//
    private static void tambahPesanan(int pilihan, int riwayat, int totalBayar, int ongkir, int jarak, int makanan, int minuman){

        int menuPesan = 0;
        int urutanMakanan = pilihanMenuMakanan.get(makanan).size() ; //untuk menghitung banyaknya pilihan makanan yang sudah dilakukan
        int urutanMinuman = pilihanMenuMakanan.get(makanan).size() ;

        do {
            System.out.println("MENU RESTAURANT");
            System.out.println("Nama Restaurant: " + restaurant.get(pilihan));
            System.out.println("Alamat: " + alamat.get(pilihan));
//                System.out.printf("\nMENU:\t\t\tHARGA:\n");
//                for (int i=1; i < restaurant.get(pilihan).size(); i++){
//                    System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
//                }
            Admin.lihatMenu(pilihan);
            if (menuMakanan.get(pilihan).size() != 0 && menuMinuman.get(pilihan).size() != 0){
                System.out.println("1. Tambah pesanan makanan");
                System.out.println("2. Tambah pesanan minuman");
                System.out.println("0. Lihat pesanan");
                System.out.print("Masukkan pilihan anda: ");
            } else if (menuMakanan.get(pilihan).size() != 0 && menuMinuman.get(pilihan).size() == 0) {
                System.out.println("1. Tambah pesanan makanan");
                System.out.println("0. Lihat pesanan");
                System.out.print("Masukkan pilihan anda: ");
            } else if (menuMakanan.get(pilihan).size() == 0 && menuMinuman.get(pilihan).size() != 0) {
                System.out.println("1. Tambah pesanan minuman");
                System.out.println("0. Lihat pesanan");
                System.out.print("Masukkan pilihan anda: ");
            }
            menuPesan = Utility.scanINT();
            System.out.print("\033[H\033[2J");
            System.out.flush();



            if (menuPesan == 0){
                int totalPlusOngkir = totalBayar;
//                    System.out.println("Total harga: "+ totalPlusOngkir);
                pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat, jarak, makanan, minuman);
                break;
            } else if (menuPesan == 1) {
                Admin.lihatMenu(pilihan);
                System.out.println("Masukkan nomor pilihan makanan anda: ");
                int pilihanMakanan = Utility.scanINT();
                pilihanMakanan -= 1;

                if (urutanMakanan == 0){
                    pilihanMenuMakanan.get(makanan).add(0, riwayat);
                }

                pilihanMenuMakanan.get(makanan).add(urutanMakanan, pilihanMakanan);
                System.out.println("Nama : " + menuMakanan.get(pilihan).get(pilihanMakanan));
                System.out.println("Harga: " + hargaMakanan.get(pilihan).get(pilihanMakanan));
                System.out.print("Masukkan banyak menu yang akan dipesan: ");
                int banyak = Utility.scanINT();
                int urutanBanyak = urutanMakanan - 1;
                banyakPesananMakanan.get(makanan).add(urutanBanyak, banyak);
                int subtotal = banyak * hargaMakanan.get(pilihan).get(pilihanMakanan);
                System.out.println("Harga: " + subtotal);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                urutanMakanan += 1;
                totalBayar += subtotal;
            } else if (menuPesan == 2) {
                Admin.lihatMenu(pilihan);
                System.out.println("Masukkan nomor pilihan makanan anda: ");
                int pilihanMinuman = Utility.scanINT();
                pilihanMinuman -= 1;

                if (urutanMinuman == 0){
                    pilihanMenuMinuman.get(minuman).add(0, riwayat);
                }

                pilihanMenuMinuman.get(minuman).add(urutanMinuman, pilihanMinuman);
                System.out.println("Nama : " + menuMinuman.get(pilihan).get(pilihanMinuman));
                System.out.println("Harga: " + hargaMinuman.get(pilihan).get(pilihanMinuman));
                System.out.print("Masukkan banyak menu yang akan dipesan: ");
                int banyak = Utility.scanINT();
                int urutanBanyak = urutanMinuman - 1;
                banyakPesananMakanan.get(minuman).add(urutanBanyak, banyak);
                int subtotal = banyak * hargaMinuman.get(pilihan).get(pilihanMinuman);
                System.out.println("Harga: " + subtotal);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                urutanMinuman += 1;
                totalBayar += subtotal;
            } else {
                System.out.println("INPUT ANDA SALAH");
            }
        }while (true);

    }
//
//
    //LIHAT PESANAN YANG UDAH DILAKUKAN
    private static void lihatPesanan(){
        int pesananDilakukan = pilihanRestauran.size();

        for (int i = 0;i<pesananDilakukan;i++){
            int pesananMakanan = -1;
            int pesananMinuman = -1;
            int namaRestauran = pilihanRestauran.get(i) - 1;

            System.out.println("PESANAN KE-"+ (i+1));
            System.out.println();
            System.out.println("Restauran: "+ restaurant.get(namaRestauran));
            System.out.println("Alamat: "+ alamat.get(namaRestauran));


//            System.out.println(pilihanMenuMakanan.size());
//            System.out.println(pilihanMenuMinuman.size());


            if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
                for(int j = 0; j<pilihanMenuMakanan.size(); j++){
                    if (pilihanMenuMakanan.get(j).size() != 0 && pilihanMenuMakanan.get(j).get(0) == i){
                        pesananMakanan = pilihanMenuMakanan.get(j).get(0);
                    }
                }
                for (int k = 0; k < pilihanMenuMinuman.size(); k++){
                    if (pilihanMenuMinuman.get(k).size() != 0 && pilihanMenuMinuman.get(k).get(0) == i){
                        pesananMinuman = pilihanMenuMinuman.get(i).get(0);
                    }
                }

            } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
                for (int k = 0; k < pilihanMenuMinuman.size(); k++){
                    if (pilihanMenuMinuman.get(k).size() != 0 && pilihanMenuMinuman.get(k).get(0) == i){
                        pesananMinuman = pilihanMenuMinuman.get(i).get(0);
                    }
                }

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
                for(int j = 0; j<pilihanMenuMakanan.size(); j++){
                    if (pilihanMenuMakanan.get(j).size() != 0 && pilihanMenuMakanan.get(j).get(0) == i){
                        pesananMakanan = pilihanMenuMakanan.get(j).get(0);
                    }
                }
            }

            if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i == pesananMakanan && i == pesananMinuman){
                // user memesan makanan dan minuman

                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 1; l < pilihanMenuMakanan.get(i).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  l - 1;
                    int subtotal = banyakPesananMakanan.get(i).get(urutanBanyak) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMinuman.get(i).size(); m++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  m - 1;
                    int subtotal = banyakPesananMinuman.get(i).get(urutanBanyak) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i != pesananMakanan && i == pesananMinuman) {
                // user tidak memesan makanan
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMinuman.get(i).size(); m++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  m - 1;
                    int subtotal = banyakPesananMinuman.get(i).get(urutanBanyak) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i == pesananMakanan && i != pesananMinuman) {
                // user tidak memesan minuman

                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 1; l < pilihanMenuMakanan.get(i).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  l - 1;
                    int subtotal = banyakPesananMakanan.get(i).get(urutanBanyak) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0 && i == pesananMinuman){
                // hanya memesan minuman saat pesanan pertamanya

                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMinuman.get(i).size(); m++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  m - 1;
                    int subtotal = banyakPesananMinuman.get(i).get(urutanBanyak) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0 && i == pesananMakanan) {
                // hanya memesan makanan saat pesanan pertamanya

                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 1; l < pilihanMenuMakanan.get(i).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int urutanBanyak =  l - 1;
                    int subtotal = banyakPesananMakanan.get(i).get(urutanBanyak) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(urutanBanyak), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            }
            System.out.println();
        }

        System.out.println("MASUKKAN ANGKA 0 UNTUK KEMBALI");
        int kembali = Utility.scanINT();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if (kembali == 0){
            menuCustomer();
        }
    }






}

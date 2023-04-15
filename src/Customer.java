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

//        for (int i= 0; i < restaurant.size(); i++ ){
//            System.out.printf("%d. %s\n",i+1 , restaurant.get(i).get(0));
//        }
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
                    if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
                        ma += 1;
                        mi += 1;
                    } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
                        mi += 1;
                    } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
                        ma +=1;
                    }
                    pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat, jarak);
                    break;
                } else if (menuPesan == 1) {
                    Admin.lihatMenu(pilihan);
                    System.out.println("Masukkan nomor pilihan makanan anda: ");
                    int pilihanMakanan = Utility.scanINT();
                    pilihanMakanan -= 1;

                    if (urutanMakanan == 0){
                        pilihanMenuMakanan.get(ma).add(0, pesanan);
                    }

                    pilihanMenuMakanan.get(ma).add(urutanMakanan, pilihanMakanan);
                    urutanMakanan += 1;
                    System.out.println("Nama : " + menuMakanan.get(pilihan).get(pilihanMakanan));
                    System.out.println("Harga: " + hargaMakanan.get(pilihan).get(pilihanMakanan));
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    banyakPesananMakanan.get(ma).add(urutanMakanan, banyak);
                    int subtotal = banyak * hargaMakanan.get(pilihan).get(pilihanMakanan);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalBayar += subtotal;
                } else if (menuPesan == 2) {
                    Admin.lihatMenu(pilihan);
                    System.out.println("Masukkan nomor pilihan makanan anda: ");
                    int pilihanMinuman = Utility.scanINT();
                    pilihanMinuman -= 1;

                    if (urutanMakanan == 0){
                        pilihanMenuMinuman.get(mi).add(0, pesanan);
                    }

                    pilihanMenuMinuman.get(mi).add(urutanMinuman, pilihanMinuman);
                    urutanMinuman += 1;
                    System.out.println("Nama : " + menuMinuman.get(pilihan).get(pilihanMinuman));
                    System.out.println("Harga: " + hargaMinuman.get(pilihan).get(pilihanMinuman));
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    banyakPesananMakanan.get(mi).add(urutanMinuman, banyak);
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

    private static void tampilanPesanan(int ongkir, int riwayat, int jarak){//riwayat == pesanan yang akan ditampilkan
        int pesananMakanan = 0;
        int pesananMinuman = 0;
        if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
            pesananMakanan = pilihanMenuMakanan.get(ma).get(0);
            pesananMinuman = pilihanMenuMinuman.get(mi).get(0);
        } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
            pesananMinuman = pilihanMenuMinuman.get(mi).get(0);
        } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
            pesananMakanan = pilihanMenuMakanan.get(ma).get(0);
        }

        if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && riwayat == pesananMakanan && riwayat == pesananMinuman){
            // user memesan makanan dan minuman

            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(ma).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(ma).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(ma).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(ma).get(i), subtotal);
            }
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 1; i < pilihanMenuMakanan.get(riwayat).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(mi).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(mi).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(mi).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && riwayat != pesananMakanan && riwayat == pesananMinuman) {
            // user tidak memesan makanan
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 1; i < pilihanMenuMakanan.get(riwayat).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(mi).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(mi).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(mi).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && riwayat == pesananMakanan && riwayat != pesananMinuman) {
            // user tidak memesan minuman
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(ma).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(ma).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(ma).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(ma).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0 && riwayat == pesananMinuman){
            // hanya memesan minuman saat pesanan pertamanya
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MINUMAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 1; i < pilihanMenuMakanan.get(riwayat).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMinuman.get(mi).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMinuman.get(mi).get(i) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(mi).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0 && riwayat == pesananMakanan) {
            // hanya memesan makanan saat pesanan pertamanya
            System.out.println("PESANAN");
            System.out.println();
            System.out.println("MAKANAN");
            System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
            for (int i = 0; i < pilihanMenuMakanan.get(ma).size(); i++ ){
                int namaRestaurant = pilihanRestauran.get(riwayat) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenuMakanan.get(ma).get(i); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesananMakanan.get(ma).get(i) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(ma).get(i), subtotal);
            }
            System.out.println("JARAK: " + jarak);
            System.out.println("ONGKIR: "+ ongkir);

        }

    }



    private static void pengecekanSebelumBayar(int total, int ongkir, int riwayat, int jarak){
        System.out.println(riwayat);
        tampilanPesanan(ongkir, riwayat, jarak);
        System.out.println("TOTAL: " + total);
        System.out.println("1. Tambah Pesanan");
        System.out.println("2. Bayar");
        System.out.println("0. Batalkan Pesanan");
        System.out.print("Masukkan pilihan: ");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int pilihan = Utility.scanINT();
        switch (pilihan){
            case 1:
                int restauranId = pilihanRestauran.get(riwayat);
//                lihatMenu(restauranId);
                tambahPesanan(restauranId, riwayat, total, ongkir, jarak);
                break;
            case 2:
                bayar(total, ongkir, riwayat, jarak);
                break;
            default:
                pengecekanSebelumBayar(total, ongkir, riwayat, jarak);
                break;
        }
    }

    private static void bayar(int total, int ongkir, int riwayat, int jarak){
        tampilanPesanan(ongkir, riwayat, jarak);
        System.out.printf("TOTAL: %d\n", total);
        System.out.println("Metode pembayaran");
        System.out.println("1. TUNAI");
        System.out.println("2. Online");
        System.out.print("Masukkan pilihan: ");
        int pilihan = Utility.scanINT();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        switch (pilihan){
            case 1:
                System.out.println("SILAHKAN BAYAR PADA PENGIRIM");
                System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN JASA KAMI");
                break;
            case 2:
                System.out.println("SILAHKAN BAYAR DENGAN e-WALLET ANDA");
                System.out.println("TERIMAKASIH SUDAH MENGGUNAKAN JASA KAMI");
                break;
            default:
                System.out.println("SILAHKAN MENGULANGI MEMASUKKAN PILIHAN ANDA!");
                bayar(total, ongkir, riwayat, jarak);
                break;
        }
        menuCustomer();
    }
//
//
//
    private static void tambahPesanan(int pilihan, int riwayat, int totalBayar, int ongkir, int jarak){

        int menuPesan = 0;
        int urutanMakanan = pilihanMenuMakanan.get(ma).size() ; //untuk menghitung banyaknya pilihan makanan yang sudah dilakukan
        int urutanMinuman = pilihanMenuMakanan.get(ma).size();

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
                int totalPlusOngkir = totalBayar + ongkir;
//                    System.out.println("Total harga: "+ totalPlusOngkir);
                if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
                    ma += 1;
                    mi += 1;
                } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
                    mi += 1;
                } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
                    ma +=1;
                }
                pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat, jarak);
                break;
            } else if (menuPesan == 1) {
                Admin.lihatMenu(pilihan);
                System.out.println("Masukkan nomor pilihan makanan anda: ");
                int pilihanMakanan = Utility.scanINT();
                pilihanMakanan -= 1;

                if (urutanMakanan == 0){
                    pilihanMenuMakanan.get(ma).add(0, riwayat);
                }

                pilihanMenuMakanan.get(ma).add(urutanMakanan, pilihanMakanan);
                urutanMakanan += 1;
                System.out.println("Nama : " + menuMakanan.get(pilihan).get(pilihanMakanan));
                System.out.println("Harga: " + hargaMakanan.get(pilihan).get(pilihanMakanan));
                System.out.print("Masukkan banyak menu yang akan dipesan: ");
                int banyak = Utility.scanINT();
                banyakPesananMakanan.get(ma).add(urutanMakanan, banyak);
                int subtotal = banyak * hargaMakanan.get(pilihan).get(pilihanMakanan);
                System.out.println("Harga: " + subtotal);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                totalBayar += subtotal;
            } else if (menuPesan == 2) {
                Admin.lihatMenu(pilihan);
                System.out.println("Masukkan nomor pilihan makanan anda: ");
                int pilihanMinuman = Utility.scanINT();
                pilihanMinuman -= 1;

                if (urutanMakanan == 0){
                    pilihanMenuMinuman.get(mi).add(0, riwayat);
                }

                pilihanMenuMinuman.get(mi).add(urutanMinuman, pilihanMinuman);
                urutanMinuman += 1;
                System.out.println("Nama : " + menuMinuman.get(pilihan).get(pilihanMinuman));
                System.out.println("Harga: " + hargaMinuman.get(pilihan).get(pilihanMinuman));
                System.out.print("Masukkan banyak menu yang akan dipesan: ");
                int banyak = Utility.scanINT();
                banyakPesananMakanan.get(mi).add(urutanMinuman, banyak);
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
//
//
    //LIHAT PESANAN YANG UDAH DILAKUKAN
    private static void lihatPesanan(){
        int pesananDilakukan = pilihanRestauran.size();

        for (int i = 0;i<pesananDilakukan;i++){
            int pesananMakanan = 0;
            int pesananMinuman = 0;
            int namaRestauran = pilihanRestauran.get(i) - 1;

            System.out.println("Restauran: "+ restaurant.get(namaRestauran));
            System.out.println("Alamat: "+ alamat.get(namaRestauran));

            /*
            buat perulangang di dalama perulangan untuk dapatin angka pada semua x pada index 0 di pilihan menu makanan dan minuman
            dan cek jika itu sama dengan i ambil nilainya print kalo engga jangan di print
             */


            if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0){
                for(int j = 0; j<pilihanMenuMakanan.size(); j++){
                    if (pilihanMenuMakanan.get(j).get(0) == i){
                        pesananMakanan = pilihanMenuMakanan.get(j).get(0);
                    }else {
                        pesananMakanan = -1;
                    }
                }
                for (int k = 0; k < pilihanMenuMinuman.size(); k++){
                    if (pilihanMenuMinuman.get(k).get(0) == i){
                        pesananMinuman = pilihanMenuMinuman.get(i).get(0);
                    }else {
                        pesananMinuman = -1;
                    }
                }

            } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0){
                for (int k = 0; k < pilihanMenuMinuman.size(); k++){
                    if (pilihanMenuMinuman.get(k).get(0) == i){
                        pesananMinuman = pilihanMenuMinuman.get(i).get(0);
                    }else {
                        pesananMinuman = -1;
                    }
                }

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0) {
                for(int j = 0; j<pilihanMenuMakanan.size(); j++){
                    if (pilihanMenuMakanan.get(j).get(0) == i){
                        pesananMakanan = pilihanMenuMakanan.get(j).get(0);
                    }else {
                        pesananMakanan = -1;
                    }
                }
            }

            if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i == pesananMakanan && i == pesananMinuman){
                // user memesan makanan dan minuman

                System.out.println("PESANAN KE-"+ (i+1));
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 0; l < pilihanMenuMakanan.get(ma).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMakanan.get(i).get(l) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(l), subtotal);
                }
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMakanan.get(m).size(); i++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMinuman.get(i).get(m) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(m), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i != pesananMakanan && i == pesananMinuman) {
                // user tidak memesan makanan
                System.out.println("PESANAN KE-"+ (i+1));
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMakanan.get(m).size(); i++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMinuman.get(i).get(m) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(m), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() != 0 && i == pesananMakanan && i != pesananMinuman) {
                // user tidak memesan minuman
                System.out.println("PESANAN KE-"+ (i+1));
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 0; l < pilihanMenuMakanan.get(ma).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMakanan.get(i).get(l) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(l), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() == 0 && pilihanMenuMinuman.size() != 0 && i == pesananMinuman){
                // hanya memesan minuman saat pesanan pertamanya
                System.out.println("PESANAN KE-"+ (i+1));
                System.out.println();
                System.out.println("MINUMAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int m = 1; m < pilihanMenuMakanan.get(m).size(); i++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMinuman.get(i).get(m); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMinuman.get(i).get(m) * hargaMinuman.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", m ,restaurant.get(namaRestaurant), banyakPesananMinuman.get(i).get(m), subtotal);
                }
                System.out.println("JARAK: " + jarakRestauran.get(i));

            } else if (pilihanMenuMakanan.size() != 0 && pilihanMenuMinuman.size() == 0 && i == pesananMakanan) {
                // hanya memesan makanan saat pesanan pertamanya
                System.out.println("PESANAN KE-"+ (i+1));
                System.out.println();
                System.out.println("MAKANAN");
                System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
                for (int l = 0; l < pilihanMenuMakanan.get(ma).size(); l++ ){
                    int namaRestaurant = pilihanRestauran.get(i) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                    int menu = pilihanMenuMakanan.get(i).get(l); // ngambil data menu yang diinginkan
//            System.out.println("Nomer menu: "+ menu);
                    int subtotal = banyakPesananMakanan.get(i).get(l) * hargaMakanan.get(namaRestaurant).get(menu); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                    System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", l ,restaurant.get(namaRestaurant), banyakPesananMakanan.get(i).get(l), subtotal);
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

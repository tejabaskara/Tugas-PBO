import javax.net.ssl.SSLContext;
import java.util.ArrayList;

public class Customer {
    public static ArrayList<ArrayList<String>> restaurant = Admin.restaurant;
    public static ArrayList<ArrayList<Integer>> hargaMenu = Admin.hargaMenu;
    public static ArrayList<ArrayList<Integer>> pilihanMenu = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> banyakPesanan = new ArrayList<>();
    public static ArrayList<String> alamat = Admin.alamat;
    public static int pesanan= 0;

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
//    private static void TampilanRestauran(){
//
//    }
//
    private static void pesanan(){
        int menuPesan = 0; // untuk memilih menu yang akan dipesan user
        int totalBayar = 0; // menghitung total yang harus dibayar user
        int pilihanKe =0; //untuk menghitung banyaknya pilihan yang sudah dilakukan

        pilihanMenu.add(new ArrayList<>());
        banyakPesanan.add(new ArrayList<>());

//        System.out.println(pesanan);


        for (int i= 0; i < restaurant.size(); i++ ){
            System.out.printf("%d. %s\n",i+1 , restaurant.get(i).get(0));
        }
        System.out.println("0. Keluar");
        System.out.print("Pilih restauran yang akan dipesan: ");
        int pilihan = Utility.scanINT();
        pilihanMenu.get(pesanan).add(0, pilihan); // menambahkan element nomer atau ID resetaurant pada array
        System.out.print("Masukkan jarak rumah anda dalam KM: ");
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int jarak = Utility.scanINT();
        int ongkir = jarak * 2000;
        if (pilihan == 0){
            menuCustomer();
        } else {
            pilihan -= 1;
//            System.out.println("a");
//            System.out.println(pilihan);
            do {
                pilihanKe+=1;
                System.out.println("MENU RESTAURANT");
                System.out.println("Nama Restaurant: " + restaurant.get(pilihan).get(0));
                System.out.println("Alamat: " + alamat.get(pilihan));
//                System.out.printf("\nMENU:\t\t\tHARGA:\n");
//                for (int i=1; i < restaurant.get(pilihan).size(); i++){
//                    System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
//                }
                lihatMenu(pilihan);
                System.out.println("0. Lihat pesanan");
                System.out.print("Masukkan pilihan menu yang akan dipesan: ");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                menuPesan = Utility.scanINT();

                if (menuPesan == 0){
                    int totalPlusOngkir = totalBayar + ongkir;
//                    System.out.println("Total harga: "+ totalPlusOngkir);
                    int riwayat = pesanan;
//                    System.out.println(riwayat);
//                    System.out.println(restaurant);
//                    System.out.println(banyakPesanan);
//                    System.out.println(pilihanMenu);
                    pesanan+=1;
                    pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat);
                    break;
                }else {
//                    System.out.println(pilihanKe);
//                    System.out.println(menuPesan);
                    pilihanMenu.get(pesanan).add(pilihanKe, menuPesan); // menambahkan element nomer atau ID menu pada array
//                    System.out.println(pilihanMenu);
                    System.out.println("Menu: " + restaurant.get(pilihan).get(menuPesan));
                    int menu = menuPesan - 1 ;
                    System.out.println("Harga: " + hargaMenu.get(pilihan).get(menu));
                    System.out.print("Masukkan banyak menu yang akan dipesan: ");
                    int banyak = Utility.scanINT();
                    int indexBanyak = pilihanKe - 1;
                    banyakPesanan.get(pesanan).add(indexBanyak, banyak); //memasukkan data banyak pesanan per menu dalam array
                    int subtotal = banyak * hargaMenu.get(pilihan).get(menu);
                    System.out.println("Harga: " + subtotal);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    totalBayar += subtotal;
//                    System.out.println(menuPesan);
                }
            }while (true);
        }

    }

    private static void tampilanPesanan(int ongkir, int riwayat){//riwayat == pesanan yang akan ditampilkan
        System.out.println("PESANAN");
        System.out.printf("Menu\t\t\tBanyak pesanan\t\t\tSubtotal\n");
        for (int i = 1; i < pilihanMenu.get(riwayat).size(); i++ ){
            int NomerData = i-1;
//            System.out.println("Nomer data: "+ NomerData);
            int namaRestaurant = pilihanMenu.get(riwayat).get(0) - 1; // ngambil data nama restaurant dari array pesanan
//            System.out.println("Nomer restauran: "+ namaRestaurant);
            int menu = pilihanMenu.get(riwayat).get(i); // ngambil data menu yang diinginkan
            int harga = pilihanMenu.get(riwayat).get(i) - 1; //untuk index dalam mencari harga dari menu
//            System.out.println("Nomer menu: "+ menu);
            int subtotal = banyakPesanan.get(riwayat).get(NomerData) * hargaMenu.get(namaRestaurant).get(harga); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
            System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", i ,restaurant.get(namaRestaurant).get(menu), banyakPesanan.get(riwayat).get(NomerData), subtotal);
        }
        System.out.println("ONGKIR: "+ ongkir);
    }

    private static void pengecekanSebelumBayar(int total, int ongkir, int riwayat){
        System.out.println(riwayat);
        tampilanPesanan(ongkir, riwayat);
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
                int restauranId = pilihanMenu.get(riwayat).get(0) - 1;
//                lihatMenu(restauranId);
                tambahPesanan(restauranId, riwayat, total, ongkir);
                break;
            case 2:
                bayar(total, ongkir, riwayat);
                break;
            default:
                pengecekanSebelumBayar(total, ongkir, riwayat);
                break;
        }
    }

    private static void bayar(int total, int ongkir, int riwayat){
        tampilanPesanan(ongkir, riwayat);
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
                bayar(total, ongkir, riwayat);
                break;
        }
        menuCustomer();
    }

    private static void lihatMenu(int pilihan){
        System.out.printf("\nMENU:\t\t\tHARGA:\n");
        for (int i=1; i < restaurant.get(pilihan).size(); i++){
            System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
        }
    }


    private static void tambahPesanan(int pilihan, int riwayat, int totalBayar, int ongkir){
        int pilihanKe = pilihanMenu.get(riwayat).size() - 1;
        System.out.println(pilihanKe);

        lihatMenu(pilihan);

        do {
            pilihanKe+=1;
            System.out.println("MENU RESTAURANT");
            System.out.println("Nama Restaurant: " + restaurant.get(pilihan).get(0));
            System.out.println("Alamat: " + alamat.get(pilihan));
//                System.out.printf("\nMENU:\t\t\tHARGA:\n");
//                for (int i=1; i < restaurant.get(pilihan).size(); i++){
//                    System.out.printf("%d. %s\t\t\t%s\n",i, restaurant.get(pilihan).get(i), hargaMenu.get(pilihan).get(i-1));
//                }
            lihatMenu(pilihan);
            System.out.println("0. Lihat pesanan");
            System.out.print("Masukkan pilihan menu yang akan dipesan: ");
            int menuPesan = Utility.scanINT();
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (menuPesan == 0){
                int totalPlusOngkir = totalBayar + ongkir;
//                    System.out.println("Total harga: "+ totalPlusOngkir);
//                    System.out.println(riwayat);
//                    System.out.println(restaurant);
//                    System.out.println(banyakPesanan);
//                    System.out.println(pilihanMenu);
                pengecekanSebelumBayar(totalPlusOngkir, ongkir, riwayat);
                break;
            }else {
//                    System.out.println(pilihanKe);
//                    System.out.println(menuPesan);
                pilihanMenu.get(riwayat).add(pilihanKe, menuPesan); // menambahkan element nomer atau ID menu pada array
//                    System.out.println(pilihanMenu);
                System.out.println("Menu: " + restaurant.get(pilihan).get(menuPesan));
                int menu = menuPesan - 1 ;
                System.out.println("Harga: " + hargaMenu.get(pilihan).get(menu));
                System.out.print("Masukkan banyak menu yang akan dipesan: ");
                int banyak = Utility.scanINT();
                int indexBanyak = pilihanKe - 1;
                banyakPesanan.get(riwayat).add(indexBanyak, banyak); //memasukkan data banyak pesanan per menu dalam array
                int subtotal = banyak * hargaMenu.get(pilihan).get(menu);
                System.out.println("Harga: " + subtotal);
                totalBayar += subtotal;
                System.out.print("\033[H\033[2J");
                System.out.flush();
//                    System.out.println(menuPesan);
            }
        }while (true);

    }


    //LIHAT PESANAN YANG UDAH DILAKUKAN
    private static void lihatPesanan(){
        int pesananDilakukan = pilihanMenu.size();
        for (int i = 0;i<pesananDilakukan;i++){
            System.out.println("PESANAN KE-"+ (i+1));
            int namaRestaurant = pilihanMenu.get(i).get(0) - 1; // ngambil data nama restaurant dari array pesanan
            System.out.println();
            System.out.println("RESTAURANT: " + restaurant.get(namaRestaurant).get(0));
            System.out.println();
            for (int j = 1; j < pilihanMenu.get(i).size(); j++ ){
                int NomerData = i;
//            System.out.println("Nomer data: "+ NomerData);
//            System.out.println("Nomer restauran: "+ namaRestaurant);
                int menu = pilihanMenu.get(i).get(j); // ngambil data menu yang diinginkan
                int harga = pilihanMenu.get(i).get(j) - 1; //untuk index dalam mencari harga dari menu
//            System.out.println("Nomer menu: "+ menu);
                int subtotal = banyakPesanan.get(i).get(NomerData) * hargaMenu.get(namaRestaurant).get(harga); // menghitung harga tiap menu yang dipesan
//            System.out.println("subtotal: "+ subtotal);
                System.out.printf("%d. %s\t\t\t%d\t\t\t%d\n", j ,restaurant.get(namaRestaurant).get(menu), banyakPesanan.get(i).get(NomerData), subtotal);
            }
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

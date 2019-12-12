package com.genadidharma;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String[] provinces = {
            "Bali", "Bangka Belitung", "Gorontalo"
    };
    private static String[][] citiesOrRegencies = {
            {"Kab Jembrana", "Kab Tabanan", "Kab Badung", "Kab Gianyar", "Kab Klungkung", "Kab Bangli", "Kab Karangasem", "Kab Buleleng", "Kota Denpasar"},
            {"Kab Bangka", "Kab Belitung", "Kab Bangka Selatan", "Kab Bangka Tengah", "Kab Bangka Barat", "Kab Belitung Timur", "Kota Pangkal Pinang"},
            {"Kab Gorontalo", "Kab Boalemo", "Kab Bone Bolango", "Kab Pahuwato", "Kab Gorontalo Utara", "Kota Gorontalo"}
    };
    private static String[][][] districts = {
            {
                    {"Negara", "Mendoyo", "Pekutan", "Melaya", "Jembrana"},
                    {"Selemadeg", "Selemadeg Timur", "Selemadeg Barat", "Kerambitan", "Tabanan", "Kediri", "Marga", "Penebel", "Baturiti", "Pupuan"},
                    {"Kuta", "Mengwi", "Abiansemal", "Petang", "Kuta Utara", "Kuta Selatan"},
                    {"Sukawati", "Blahbatuh", "Gianyar", "Tampak Siring", "Ubud", "Tegalalang", "Payangan"},
                    {"Nusa Penida", "Banjarangkan", "Klungkung", "Dawan"},
                    {"Susut", "Bangli", "Tembuku", "Kintamani"},
                    {"Rendang", "Sidemen", "Manggis", "Karangasem", "Abang", "Bebandem", "Selat", "Kubu"},
                    {"Gerokgak", "Seririt", "Busung Biu", "Banjar", "Sukasada", "Buleleng", "Sawan", "Kubutambahan", "Tejakula"},
                    {"Denpasar Selatan", "Denpasar Timur", "Denpasar Barat", "Denpasar Utara"}
            },
            {
                    {"Sungailiat", "Belinyu", "Merawang", "Mendo Barat", "Pemali", "Bakam", "Riau Silip", "Puding Besar"},
                    {"Tanjung Pandan", "Membalong", "Selat Nasik", "Sijuk", "Badau"},
                    {"Toboali", "Lepar Pongok", "Air Gegas", "Simpang Rimba", "Payung", "Tukak Sadai", "Pulaubesar"},
                    {"Koba", "Pangkalan Baru", "Sungai Selan", "Simpang Katis", "Namang", "Lubuk Besar"},
                    {"Mentok", "Simpang Teritip", "Jebus", "Kelapa", "Tempilang", "Parittiga"},
                    {"Manggar", "Gantung", "Dendang", "Kelapa Kampit", "Damar", "Simpang Renggiang", "Simpang Pesak"},
                    {"Bukitintan", "Taman Sari", "Pangkal Balam", "Rangkui", "Gerunggang", "Gabek", "Grimaya"},

            },
            {
                    {"Limboto", "Telaga", "Batudaa", "Tibawa", "Batudaa Pantai", "Boliyohuto", "Telaga Biru", "Bongomeme", "Tolinggula", "Tolangohula", "Mootilango", "Pulubala"},
                    {"Panguyaman", "Wonosari", "Dulupi", "Tilamuta", "Mananggu", "Botumita", "Paguyaman Pantai"},
                    {"Tapa", "Kabila", "Suwawa", "Bonepantai", "Bulango Utara", "Tilongkabila", "Botupingge"},
                    {"Popayato", "Lemito", "Randangan", "Marisa", "Paguat"},
                    {"Atinggola", "Kwandangan", "Anggrek", "Sumalata", "Tolinggula"},
                    {"Kota Barat", "Kota Selatan", "Kota Utara", "Dungingi", "Kota Timur", "Kota Tengah"}
            }
    };
    private static double[][][][] latLngs = {
            {
                    {
                            {-8.357130, 114.645592}, {-8.347370, 114.726318}, {-8.357130, 114.645590}, {-8.248170, 114.486770}, {-8.357130, 114.645592},
                    },
                    {
                            {-8.491370, 115.037250}, {-8.468510, 114.989071}, {-8.455221, 114.938183}, {-8.550210, 115.077810}, {-8.545040, 115.119957}, {-8.575367, 115.047999}, {-8.450725, 115.108194}, {-8.388431, 115.054601}, {-8.346293, 115.101707}, {-8.367943, 114.943956},
                    },
                    {
                            {-8.726237, 115.136513}, {-8.565838, 115.087930}, {-8.530496, 115.187905}, {-8.354544, 115.147209}, {-8.642822, 115.117494}, {-8.785488, 115.183310},
                    },
                    {
                            {-8.5832745, 115.24052}, {-8.5665855, 115.28296}, {-8.512379, 115.301517}, {-8.435310, 115.288374}, {-8.496068, 115.248529}, {-8.444121, 115.270748}, {-8.518706, 115.238092}
                    },
                    {
                            {-8.745486, 115.502621}, {-8.515284, 115.343291}, {-8.524885, 115.373664}, {-8.538674, 115.427708}
                    },
                    {
                            {-8.427539, 115.2698514}, {-8.412174, 115.292219}, {-8.417689, 115.353152}, {-8.249828, 115.274953}
                    },
                    {
                            {-8.3814374, 115.3717885}, {-8.4873755, 115.4040315}, {-8.4921575, 115.5164452}, {-8.4501013, 115.5893459}, {-8.3632661, 115.5799035}, {-8.4388985, 115.5545462}, {-8.4102965, 115.4434026}, {-8.2594185, 115.5572472}
                    },
                    {
                            {-8.2045675, 114.5890115}, {-8.2591435, 114.8476359}, {-8.3124741, 114.92564}, {-8.236629, 114.962336}, {-8.218527, 115.0343174}, {-8.1301563, 115.0410602}, {-8.1539061, 115.1001919}, {-8.0801492, 115.1812337}, {-8.1373665, 115.3194305}
                    },
                    {
                            {-8.7036971, 115.1908275}, {-8.6328625, 115.2116281}, {-8.6622851, 115.169821}, {-8.6247687, 115.1941219}
                    }
            },
            {
                    {
                            {-1.8804041, 106.051401}, {-1.6432082, 105.7773564}, {-2.0034424, 106.0059154}, {-2.1955073, 105.7981621}, {-1.892572, 105.9718899}, {-1.8992265, 105.7817824}, {-1.7225206, 105.7667665}, {-2.0211339, 105.9193024},
                    },
                    {
                            {-2.7428137, 107.5852549}, {-3.1285255, 107.6419482}, {-2.9353095, 107.2368045}, {-2.6237276, 107.7022134}, {-2.8357726, 107.6075346}
                    },
                    {
                            {-3.049721, 106.3833265}, {-2.9311474, 106.8294118}, {-2.7324398, 106.2931345}, {-2.6552086, 105.8522721}, {-2.610477, 106.0830179}, {-3.0318621, 106.6150321}, {-2.8026779, 106.0827318}
                    },
                    {
                            {-2.3747111, 106.3142809}, {-2.1996279, 106.149912}, {-2.3847315, 105.9776422}, {-2.2836562, 106.0096724}, {-2.3247075, 106.1733432}, {-2.6033156, 106.5671803}
                    },
                    {
                            {-1.9947469, 105.125771}, {-1.9486155, 105.2578496}, {-1.7613755, 105.4708441}, {-1.8807415, 105.6597482}, {-2.0271059, 105.5813764}, {-1.6226412, 105.5034903}
                    },
                    {
                            {-2.9162688, 108.1806863}, {-2.9735265, 108.1430472}, {-3.0908525, 107.8862422}, {-2.6902075, 107.9730778}, {-2.7659224, 108.1307414}, {-2.8909484, 107.9138144}, {-3.1581411, 107.9399544}
                    },
                    {
                            {-2.1215405, 106.1340673}, {-2.1212032, 106.1099146}, {-2.1010105, 106.1184372}, {-2.1303001, 106.0923114}, {-2.1012175, 106.0584118}, {-2.0847245, 106.0949409}, {-2.1396936, 106.1075157}
                    }
            },
            {
                    {
                            {0.6893851, 122.9092494}, {0.5992104, 123.0276659}, {0.5646103, 122.937727}, {0.6843101, 122.7745093}, {0.5071957, 122.9213605}, {0.5976227, 122.573586}, {0.6970921, 122.9711554}, {0.5694343, 122.7661295}, {0.9551699, 122.0568048}, {0.7789911, 122.4804709}, {0.7438596, 122.5852593}, {0.6935956, 122.6790148}
                    },
                    {
                            {0.5872532, 122.4665818}, {0.7116201, 122.3225784, 12}, {0.6305702, 122.3111753}, {0.5735848, 122.2505658}, {0.6667748, 121.9978725}, {0.6636313, 122.0744105}, {0.5331108, 122.537632}
                    },
                    {
                            {0.6244273, 123.0755789}, {0.5464989, 123.0850784}, {0.5712292, 123.1441356}, {0.4072377, 123.189573}, {0.7073757, 123.0150579}, {0.5903507, 123.096646}, {0.518287, 123.0928337}
                    },
                    {
                            {0.5995035, 121.316971}, {0.6854958, 121.434322}, {0.5116708, 121.7147554}, {0.4577622, 121.927165}, {0.4981242, 122.0171525}
                    },
                    {
                            {0.8419725, 123.0901908}, {0.8390265, 122.8442544}, {0.8767714, 122.6515213}, {0.9501785, 122.3754259}, {0.9551699, 122.0568048}
                    },
                    {
                            {0.5396069, 123.0064949}, {0.542405, 123.0480513}, {0.562708, 123.0604928,}, {0.5605699, 123.0291747}, {0.539568, 123.0629952}, {0.5644125, 123.0463047}
                    }
            }
    };

    private static String[] types = {"Dokumen", "Barang"};

    private static String[] services = {
            "MAL (Murah, Agak Lama) ",
            "REG (Reguler)          ",
            "BAS (Besok Aja Sampai) "};
    private static int[] servicesPrice = {50, 80, 180};
    private static int[][] servicesEstimatedTime = {
            {3, 4}, {2, 3}, {1, 1}
    };

    private static String[] packaging = {"Kertas", "Kardus", "Bubble Wrap", "Kayu"};
    private static int[] packagingPrice = {1000, 2000, 1000, 2500};

    private static float[] surfaceAreas = new float[5];

    private static String[] similarProvinces = new String[5];
    private static int[] similarProvincesIndex = new int[5];

    private static int maxInArray = 5;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String senderName, senderAddress, senderPhone;
        String receiverName, receiverAddress, receiverPhone;
        int originProvinceIndex, originCityOrRegencyIndex, originDistrictIndex;
        int destinationProvinceIndex, destinationCityOrRegencyIndex, destinationDistrictIndex;
        double latOrigin, lngOrigin, latDestination, lngDestination;
        double distance;
        int serviceIndex;
        String receiptNumber;
        float packagingPrice;
        int subTotal = 0, total, money;
        char selected;
        int packageAmount = 0;

        int[] packageTypes = new int[maxInArray];
        String[] packageNames = new String[maxInArray];
        float[] weights = new float[maxInArray];
        float[] dimensions = new float[maxInArray];
        int[] packagingIndexes = new int[maxInArray];

        System.out.println("Data Pengirim");
        senderName = getStringValue(input, "nama pengirim: ", false);
        senderAddress = getStringValue(input, "alamat pengirim: ", false);
        senderPhone = getStringValue(input, "telepon pengirim: (62)", true);

        System.out.println("Data Penerima");
        receiverName = getStringValue(input, "nama penerima: ", false);
        receiverAddress = getStringValue(input, "alamat penerima: ", false);
        receiverPhone = getStringValue(input, "telepon penerima: (62)", true);

        System.out.println("Asal Pengiriman");
        //Ketik provinsi asal
        originProvinceIndex = getProvinceIndex(input);

        //Pilih Kabupaten/ Kota asal
        System.out.println("Kabupaten/ Kota: ");
        for (int i = 0; i < citiesOrRegencies[originProvinceIndex].length; i++) {
            System.out.printf("%d. %s\n", i + 1, citiesOrRegencies[originProvinceIndex][i]);
        }
        originCityOrRegencyIndex = getCityOrRegencyIndex(originProvinceIndex, input);

        //Pilih Kecamatan asal
        System.out.println("Kecamatan: ");
        for (int i = 0; i < districts[originProvinceIndex][originCityOrRegencyIndex].length; i++) {
            System.out.printf("%d. %s\n", i + 1, districts[originProvinceIndex][originCityOrRegencyIndex][i]);
        }
        originDistrictIndex = getDistrictIndex(originProvinceIndex, originCityOrRegencyIndex, input);

        latOrigin = latLngs[originProvinceIndex][originCityOrRegencyIndex][originDistrictIndex][0];
        lngOrigin = latLngs[originProvinceIndex][originCityOrRegencyIndex][originDistrictIndex][1];

        do {
            System.out.println("Tujuan Pengiriman");
            //Ketik provinsi tujuan
            destinationProvinceIndex = getProvinceIndex(input);

            //Pilih Kabupaten/ Kota tujuan
            System.out.println("Kabupaten/ Kota: ");
            for (int i = 0; i < citiesOrRegencies[destinationProvinceIndex].length; i++) {
                System.out.printf("%d. %s\n", i + 1, citiesOrRegencies[destinationProvinceIndex][i]);
            }
            destinationCityOrRegencyIndex = getCityOrRegencyIndex(destinationProvinceIndex, input);

            //Pilih Kecamatan tujuan
            System.out.println("Kecamatan: ");
            for (int i = 0; i < districts[originProvinceIndex][destinationCityOrRegencyIndex].length; i++) {
                System.out.printf("%d. %s\n", i + 1, districts[destinationProvinceIndex][destinationCityOrRegencyIndex][i]);
            }
            destinationDistrictIndex = getDistrictIndex(destinationProvinceIndex, destinationCityOrRegencyIndex, input);

            //Hitung Jarak
            latDestination = latLngs[destinationProvinceIndex][destinationCityOrRegencyIndex][destinationDistrictIndex][0];
            lngDestination = latLngs[destinationProvinceIndex][destinationCityOrRegencyIndex][destinationDistrictIndex][1];
            distance = calculateDistance(latOrigin, lngOrigin, latDestination, lngDestination);

            if (distance == 0) System.out.println("Tujuan Pengiriman tidak boleh sama seperti asal!");
        } while (distance == 0);

        do {
            //Pilih Jenis Barang
            System.out.println("Jenis barang: ");
            for (int i = 0; i < types.length; i++) {
                System.out.printf("%d. %s \n", i + 1, types[i]);
            }
            packageTypes[packageAmount] = getTypeIndex(input);

            //Masukkan Nama Barang
            packageNames[packageAmount] = getStringValue(input, "nama barang: ", false);

            //Masukkan Berat Barang
            weights[packageAmount] = getWeight(input);

            //Masukkan dimensi barang
            dimensions[packageAmount] = getDimension(input, packageTypes[packageAmount], packageAmount);

            //Pilih / tentukan packaging
            packagingIndexes[packageAmount] = getPackagingIndex(input, packageTypes[packageAmount], dimensions[packageAmount], packageAmount);

            //Menambah jumlah paket
            packageAmount++;

            //Validasi ketika batas maksimal barang yang ingin dikirim tercapai
            if (packageAmount == maxInArray) break;

            do {
                System.out.print("Apakah ada barang lain? (y/t): ");
                selected = Character.toLowerCase(input.next().charAt(0));
                if (selected != 'y' && selected != 't') System.out.println("Masukkan pilihan yang sesuai!");

            } while (selected != 'y' && selected != 't');

        } while (selected == 'y');

        //Hitung total biaya sebelum biaya pelayanan
        for (int i = 0; i < packageAmount; i++) {
            subTotal += dimensions[i] > 0 ? (int) Math.ceil(weights[i]) * (int) dimensions[i] : (int) Math.ceil(weights[i]);
        }
        subTotal *= distance;

        //Bulatkan sub total ke ratusan terdekat
        subTotal = roundToNearestHundred(subTotal);

        //Pilih jenis pengiriman
        System.out.println("Jenis pengiriman: ");
        System.out.printf("%s \t\t\t %s \t\t\t\t\t\t %s \t\t %s", "No", "Nama", "Harga", "Estimasi\n");
        for (int i = 0; i < services.length; i++) {
            System.out.printf("%d \t %s \t\t %s \t\t %d-%d Hari\n", i + 1, services[i], convertRupiah(subTotal * servicesPrice[i]), servicesEstimatedTime[i][0], servicesEstimatedTime[i][1]);
        }
        serviceIndex = getServiceIndex(input);

        //Buat Nomor Resi
        receiptNumber = generateReceiptNumber();

        //Hitung total biaya packaging
        packagingPrice = roundToNearestHundred((int) calculatePackagingPrice(surfaceAreas, packagingIndexes, packageAmount));

        //Hitung sub total dan packaging
        total = subTotal * servicesPrice[serviceIndex] + (int) packagingPrice;

        //Tampil data transaksi
        printRecipients(senderName, senderAddress, senderPhone, receiverName, receiverAddress, receiverPhone);
        printReceipt(receiptNumber, distance, packageAmount, packageNames, dimensions, weights, packagingIndexes, packagingPrice, subTotal, total, originProvinceIndex, originCityOrRegencyIndex, originDistrictIndex, destinationProvinceIndex, destinationCityOrRegencyIndex, destinationDistrictIndex, serviceIndex);

        //Ambil uang
        money = doTransaction(input, total);

        //Tampil struk
        printRecipients(senderName, senderAddress, senderPhone, receiverName, receiverAddress, receiverPhone);
        printReceipt(receiptNumber, distance, packageAmount, packageNames, dimensions, weights, packagingIndexes, packagingPrice, subTotal, total, originProvinceIndex, originCityOrRegencyIndex, originDistrictIndex, destinationProvinceIndex, destinationCityOrRegencyIndex, destinationDistrictIndex, serviceIndex);
        printBalance(total, money);
    }

    private static String getStringValue(Scanner input, String command, boolean phoneNumber) {
        String value;
        boolean phoneNumberValid = false;
        do {
            System.out.printf("Masukkan %s", command);
            value = input.nextLine();
            if (phoneNumber) {
                if (isPhoneNumberValid(value)) {
                    phoneNumberValid = true;
                }
            } else {
                phoneNumberValid = true;
            }
            if (!phoneNumberValid) System.out.println("Masukkan nomor telepon yang sesuai!");
        } while (value.trim().equals("") || !phoneNumberValid);
        return value;
    }

    private static boolean isPhoneNumberValid(String s) {
        Pattern p = Pattern.compile("(8)?[1-9][0-9]{9}|[0-9]{10}|[0-9]{11}|[0-9]{12}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private static boolean checkInputValid(String command, Scanner input, String type) {
        System.out.print(command);
        boolean check;
        String originType = type;
        switch (type) {
            case "int":
                check = input.hasNextInt();
                type = "angka";
                break;
            case "float":
                check = input.hasNextFloat();
                type = "angka";
                break;
            case "double":
                check = input.hasNextDouble();
                type = "angka";
                break;
            default:
                check = false;
                break;
        }
        while (!check) {
            String inputted = input.next();
            System.out.printf("\"%s\" bukan merupakan %s. \n", inputted, type);
            check = true;
            checkInputValid(command, input, originType);
        }
        return true;
    }

    private static String convertRupiah(float money) {
        Locale localeId = new Locale("id", "ID");
        NumberFormat currFormat = NumberFormat.getCurrencyInstance(localeId);
        return currFormat.format(money);
    }

    private static int roundToNearestHundred(int value) {
        return ((value + 99) / 100) * 100;
    }

    private static int getProvinceIndex(Scanner input) {
        int index = -1;
        String province;
        do {
            System.out.print("Masukkan Provinsi Tujuan: ");
            province = input.nextLine();
            for (int i = 0; i < provinces.length; i++) {
                if (provinces[i].toLowerCase().equals(province.toLowerCase())) {
                    index = i;
                    break;
                }
                if (i == provinces.length - 1) {
                    index = searchProvinceIndex(input, province);
                }
            }
            if (index == -1) System.out.println("Provinsi tidak ditemukan");
        } while (index == -1);
        return index;
    }

    private static int searchProvinceIndex(Scanner input, String keyword) {
        keyword = keyword.toLowerCase();
        int startIndex = 0, index = 0;
        for (int i = 0; i < provinces.length; i++) {
            if (!keyword.equals(provinces[i].toLowerCase())) {
                if (provinces[i].toLowerCase().contains(keyword)) {
                    similarProvinces[startIndex] = provinces[i];
                    similarProvincesIndex[startIndex] = i;
                    startIndex++;
                    if (startIndex == maxInArray) break;
                } else {
                    break;
                }
            }
        }
        if (startIndex > 0) {
            System.out.println("Mungkin maksud anda: ");
            for (int i = 0; i < startIndex; i++) {
                System.out.printf("%d. %s\n", i + 1, similarProvinces[i]);
            }

            do {
                if (checkInputValid("Pilih Provinsi(0 untuk batal): ", input, "int")) {
                    index = input.nextInt();
                    input.nextLine();
                    if (index < 0 || index > startIndex) System.out.println("Masukkan pilihan yang sesuai");
                }
            } while (index < 0 || index > startIndex);
        }

        if (index > 0) {
            return similarProvincesIndex[index - 1];
        } else {
            return -1;
        }

    }

    private static int getCityOrRegencyIndex(int provinceIndex, Scanner input) {
        int index = -1;
        do {
            if (checkInputValid("Pilih Kabupaten Tujuan: ", input, "int")) {
                index = input.nextInt();
                if (index < 1 || index > citiesOrRegencies[provinceIndex].length)
                    System.out.println("Masukkan pilihan yang sesuai!");
            }
        } while (index < 1 || index > citiesOrRegencies[provinceIndex].length);
        return index - 1;
    }

    private static int getDistrictIndex(int provinceIndex, int cityOrRegencyIndex, Scanner input) {
        int index = -1;
        do {
            if (checkInputValid("Pilih Kecamatan Tujuan: ", input, "int")) {
                index = input.nextInt();
                input.nextLine();
                if (index < 1 || index > districts[provinceIndex][cityOrRegencyIndex].length)
                    System.out.println("Masukkan pilihan yang sesuai!");
            }
        } while (index < 1 || index > districts[provinceIndex][cityOrRegencyIndex].length);
        return index - 1;
    }

    private static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        lat1 = Math.toRadians(lat1);
        lng1 = Math.toRadians(lng1);
        lat2 = Math.toRadians(lat2);
        lng2 = Math.toRadians(lng2);

        final double earthRadius = 6371.01;
        return Math.round(earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng1 - lng2)));
    }

    private static int getTypeIndex(Scanner input) {
        int type = -1;
        do {
            if (checkInputValid("Pilih jenis barang: ", input, "int")) {
                type = input.nextInt();
                input.nextLine();
                if (type < 1 || type > types.length) System.out.println("Masukkan pilihan yang sesuai!");
            }
        } while (type < 1 || type > types.length);
        return type - 1;
    }

    private static float getWeight(Scanner input) {
        float weight = 0;
        do {
            if (checkInputValid("Berat barang(kg): ", input, "float")) {
                weight = input.nextFloat();
                if (weight < 0) System.out.println("Masukkan angka yang sesuai!");
            }
        } while (weight < 0);
        return weight;
    }

    private static float getDimension(Scanner input, int typeIndex, int packageAmount) {
        float length = 0, width = 0, height = 0, dimension = 0;
        char selected;
        if (typeIndex > 0) {
            do {
                System.out.print("Apakah barang anda memiliki dimensi? (y/t): ");
                selected = Character.toLowerCase(input.next().charAt(0));
                if (selected != 'y' && selected != 't') System.out.println("Masukkan pilihan yang sesuai!");

            } while (selected != 'y' && selected != 't');
            if (selected == 'y') {
                do {
                    if (checkInputValid("Masukkan panjang(cm): ", input, "float")) {
                        length = input.nextFloat();
                        if (length < 1) System.out.println("Masukkan angka yang sesuai");
                    }
                } while (length < 1);
                do {
                    if (checkInputValid("Masukkan lebar(cm): ", input, "float")) {
                        width = input.nextFloat();
                        if (width < 1) System.out.println("Masukkan angka yang sesuai");
                    }
                } while (width < 1);
                do {
                    if (checkInputValid("Masukkan tinggi(cm): ", input, "float")) {
                        height = input.nextFloat();
                        if (height < 1) System.out.println("Masukkan angka yang sesuai");
                    }
                } while (height < 1);
                dimension = length * width * height / 6000;
            }
        }
        surfaceAreas[packageAmount] = calculatePackageSurfaceArea(length, width, height);
        return (float) Math.ceil(dimension);
    }

    private static float calculatePackageSurfaceArea(float length, float width, float height) {
        if (length == 0 && width == 0 && height == 0) {
            return 1;
        } else {
            return (float) Math.ceil(2 * (length * width + length * height + width * height)) / 100;
        }
    }

    private static float calculatePackagingPrice(float[] surfaceAreas, int[] packagingIndexes, int packageAmount) {
        float totalSurfaceArea = 0;
        for (int i = 0; i < packageAmount; i++) {
            totalSurfaceArea += surfaceAreas[i] * packagingPrice[packagingIndexes[i]];
        }
        return totalSurfaceArea;
    }

    private static int getPackagingIndex(Scanner input, float typeIndex, float dimension, int packageAmount) {
        System.out.println("Pilih Packaging");
        int index = 0, min = 0;
        if (typeIndex > 0 && dimension <= 5) {
            if (dimension == 0) {
                //Kalau barang tapi tidak punya dimensi
                for (int i = 1; i < packaging.length - 1; i++) {
                    System.out.printf("%d. %s\n", i, packaging[i]);
                }
                min = 1;
            } else if (dimension > 0) {
                //Kalau barang tapi punya dimensi
                for (int i = 1; i < packaging.length; i++) {
                    System.out.printf("%d. %s\n", i, packaging[i]);
                }
                min = 0;
            }

            do {
                if (checkInputValid("Masukkan pilihan: ", input, "int")) {
                    index = input.nextInt();
                    input.nextLine();
                    if (index < 1 || index > packaging.length - 1 - min)
                        System.out.println("Masukkan pilihan yang sesuai!");
                }
            } while (index < 1 || index > packaging.length - 1 - min);

        } else if (typeIndex > 0 && dimension > 5) {
            index = 3;
        } else if (typeIndex == 1 && dimension > 5 || dimension <= 5) {
            surfaceAreas[packageAmount] = 0;
        }
        return index;
    }

    private static int getServiceIndex(Scanner input) {
        int index = -1;
        do {
            if (checkInputValid("Pilih Jenis Layanan: ", input, "int")) {
                index = input.nextInt();
                if (index < 1 || index > services.length) System.out.println("Masukkan pilihan yang sesuai!");
            }
        } while (index < 1 || index > services.length);
        return index - 1;
    }

    private static String generateReceiptNumber() {
        while (true) {
            long numb = (long) (Math.random() * 100000000 * 1000000);
            if (String.valueOf(numb).length() == 13)
                return String.valueOf(numb);
        }
    }

    private static void printRecipients(String... recipients) {
        System.out.println("Pengirim");
        System.out.printf("Nama             : %s\n", recipients[0]);
        System.out.printf("Alamat           : %s\n", recipients[1]);
        System.out.printf("Tlp              : 62%s\n", recipients[2]);
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Penerima");
        System.out.printf("Nama             : %s\n", recipients[3]);
        System.out.printf("Alamat           : %s\n", recipients[4]);
        System.out.printf("Tlp              : 62%s\n", recipients[5]);
    }

    private static void printReceipt(String receiptNumber, double distance, int packageAmount, String[] packageNames, float[] dimensions, float[] weights, int[] packagingIndexes, float packagingPrice, long subTotal, int total, int... indexes) {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("No Pesanan       : %s\n", receiptNumber);
        System.out.printf("Asal             : %s, %s, %s\n", districts[indexes[0]][indexes[1]][indexes[2]], citiesOrRegencies[indexes[0]][indexes[1]], provinces[indexes[0]]);
        System.out.printf("Tujuan           : %s, %s, %s\n", districts[indexes[3]][indexes[4]][indexes[5]], citiesOrRegencies[indexes[3]][indexes[4]], provinces[indexes[3]]);
        System.out.printf("Jasa             : %s\n", services[indexes[6]]);
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Data Produk");
        for (int i = 0; i < packageAmount; i++) {
            System.out.printf("Nama             : %s\n", packageNames[i]);
            System.out.printf("Berat            : %.2f kg\n", weights[i]);
            System.out.printf("Dimensi          : %.2f kg\n", dimensions[i]);
            System.out.printf("Packaging        : %s\n", packaging[packagingIndexes[i]]);
            System.out.println("-----------------------------------------------------------------------------------");
        }
        System.out.println("Data Pengiriman");
        System.out.printf("Jarak            : %.0f km\n", distance);
        System.out.printf("Sub Total        : %s\n", convertRupiah(subTotal * servicesPrice[indexes[6]]));
        System.out.printf("Biaya Packaging  : %s\n", convertRupiah(packagingPrice));
        System.out.printf("Total            : %s\n", convertRupiah(total));
    }

    private static int doTransaction(Scanner input, int total) {
        int money = 0;
        do {
            if (checkInputValid("Masukkan uang: ", input, "int")) {
                money = input.nextInt();
                if (money < total) System.out.println("Uang yang anda masukkan kurang!");
            }
        } while (money < total);
        return money;
    }

    private static void printBalance(int total, int money) {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Total            : %s\n", convertRupiah(total));
        System.out.printf("Uang             : %s\n", convertRupiah(money));
        System.out.printf("Kembalian        : %s\n", convertRupiah(money - total));
    }

}
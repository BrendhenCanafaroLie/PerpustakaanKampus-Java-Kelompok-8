package helper;

import java.util.Scanner;

public class InputHelper {

    private static final Scanner sc = new Scanner(System.in);

    // Membaca angka dari keyboard, diulang sampai input valid
    public static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // Membaca satu baris teks dari keyboard
    public static String bacaString(String pesan) {
        System.out.print(pesan);
        return sc.nextLine().trim();
    }
}
package converter;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int num = scanner.nextInt();
        switch (scanner.nextInt()) {
            case 2:
                System.out.println("0b" + Integer.toBinaryString(num));
                break;
            case 8:
                System.out.println("0" + Integer.toOctalString(num));
                break;
            case 16:
                System.out.println("0x" + Integer.toHexString(num));
                break;
        }
    }
}

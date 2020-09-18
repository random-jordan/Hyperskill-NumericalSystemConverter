package converter;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int sourceRadix = scanner.nextInt();
        int num = parse(scanner.next(), sourceRadix);
        int newBase = scanner.nextInt();

        System.out.println(convert(num, newBase));
    }
    private static int parse(String num, int base) {
        int value = 0;
        if (base == 1) {
            for (int i = 0; i < num.length(); i++) {
                value = num.charAt(i) == '1' ? value + 1 : value;
            }
        } else {
            value = Integer.parseInt(num, base);
        }

        return value;
    }
    private static String convert(int num, int newBase) {
        StringBuilder srt = new StringBuilder();
        if (newBase == 36) {
            while (num > 0) {
                int part = num % 36;
                if (part < 11) {
                    srt.insert(0, (char) (part + 48));
                } else {
                    srt.insert(0, (char) (part + 87));
                }
                num = num / 36;
            }
        } else if (newBase == 1) {
            for (;num > 0; num--) {
                srt.append("1");
            }
        } else {
            return Integer.toString(num, newBase);
        }
        return srt.toString();
    }
}

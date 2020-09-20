package converter;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int sourceRadix;
        String oldNum;
        int newBase;
        try {
            sourceRadix = scanner.nextInt();
            oldNum = scanner.next();
            newBase = scanner.nextInt();
            if (newBase < 1 || newBase > 36 || sourceRadix < 1 || sourceRadix > 36) {
                throw new Exception("E");
            }
        } catch (Exception e) {
            System.out.println("input error");
            return;
        }

        double decimal = parse(oldNum, sourceRadix);
        String newType = convert(decimal, newBase);

        System.out.println(newType);
    }
    private static double parse(String num, int base) {
        String[] split = num.split("[.,]");
        String integerPart = split[0];
        String floatPart = split.length == 2 ? split[1] : "";
        double value = 0;

        if (base == 1) {
            for (int i = 0; i < integerPart.length(); i++) {
                value = integerPart.charAt(i) == '1' ? value + 1 : value;
            }
            for (int i = 0; i < floatPart.length(); i++) {
                value = floatPart.charAt(i) == '1' ? value + 0.1 : value;
            }
        } else {
            for (int i = 0; i < integerPart.length(); i++) {
                int temp = integerPart.charAt(i);
                double pow = Math.pow(base, integerPart.length() - i - 1);
                if (temp < 58) {
                    value += (temp - 48) * pow;
                } else {
                    value += (temp - 87) * pow;
                }
            }
            for (int i = 0; i < floatPart.length(); i++) {
                int temp = floatPart.charAt(i);
                double pow = Math.pow(base, -1 - i);
                if (temp < 58) {
                    value += (temp - 48) * pow;
                } else {
                    value += (temp - 87) * pow;
                }
            }
        }

        return value;
    }
    private static String convert(double num, int newBase) {
        StringBuilder srt = new StringBuilder();
        int integerPart = (int) num;
        if (integerPart == 0) {
            srt.append("0");
        }
        double floatPart = num - integerPart;

        if (newBase == 1) {
            for (;integerPart > 0; integerPart--) {
                srt.append("1");
            }
            if (floatPart > 0) {
                srt.append(".");
                for (; floatPart > 0; floatPart -= 0.1) {
                    srt.append("1");
                }
            }
        } else {
            // 0,1,2,...,10,a,b,c,..
            while (integerPart > 0) {
                int part = integerPart % newBase;
                if (part < 10) {
                    srt.insert(0, (char) (part + 48));
                } else {
                    srt.insert(0, (char) (part + 87));
                }
                integerPart = integerPart / newBase;
            }
            if (floatPart > 0) {
                srt.append(".");
                for (int iteration = 0; floatPart > 0 && iteration < 5; iteration++) {
                    double mul = newBase * floatPart;
                    long part = (long) mul;

                    if (part < 10) {
                        srt.append((char) (part + 48));
                    } else {
                        srt.append((char) (part + 87));
                    }
                    floatPart = mul - part;
                }
            }
        }
        return srt.toString();
    }
}

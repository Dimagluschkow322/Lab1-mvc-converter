package model;

public class ConverterModel {

    public double percentToKoef(double percent) {
        return percent / 100;
    }

    public double koefToPercent(double koef) {
        return koef * 100;
    }

    public String percentToFraction(double percent) {
        int numerator = (int) percent;
        int denominator = 100;
        int gcd = gcd(numerator, denominator);
        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    public double fractionToKoef(String fraction) {
        String[] parts = fraction.split("/");
        double num = Double.parseDouble(parts[0]);
        double den = Double.parseDouble(parts[1]);
        return num / den;
    }

    public double fractionToPercent(String fraction) {
        return fractionToKoef(fraction) * 100;
    }

    public String koefToFraction(double koef) {
        int numerator = (int) (koef * 100);
        int denominator = 100;
        int gcd = gcd(numerator, denominator);
        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
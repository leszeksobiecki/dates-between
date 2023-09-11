package sourcery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MirrorDateReflection {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a year in a range from 0 to 9999: ");
        int startYear = in.nextInt();
        System.out.println("Please enter another year in a range from 0 to 9999: ");
        int endYear = in.nextInt();

        if (startYear < 0 || endYear < 0)
            System.out.println("Year(s) can't be negative");
        else if (startYear > 9999 || endYear > 9999) {
            System.out.println("Year(s) can't be bigger than 9999");
        }
        else {
            printBonusDatesBetween(startYear, endYear);
        }
    }

    public static void printBonusDatesBetween(int fromYear, int toYear){
        List<String> mirroredDates = findMirroredDates(fromYear, toYear);
        System.out.println();
        System.out.println("Dates between two given years that remains the same, if numbers of the date are reversed:");
        for (String date : mirroredDates) {
            System.out.println(String.join("-",date.substring(0,4), date.substring(4,6), date.substring(6,8)));
        }
    }

    public static List<String> findMirroredDates(int startYear, int endYear) {
        List<String> mirroredDates = new ArrayList<>();
        // swap years if necessary
        if (startYear > endYear){
            int tmp = endYear;
            endYear = startYear;
            startYear = tmp;
        }

        for (int year = startYear; year <= endYear; year++) {
            for (int month = 1; month <= 12; month++) {
                for (int day = 1; day <= 31; day++) {
                    if (isValidDate(year, month, day)) {
                        // padding zeros for days, months and years
                        String date = String.format("%04d%02d%02d", year, month, day);
                        if (isMirrorReflection(date)) {
                            mirroredDates.add(date);
                        }
                    }
                }
            }
        }

        return mirroredDates;
    }

    public static boolean isValidDate(int year, int month, int day) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        // Additional date validation can be performed here if needed.
        return true;
    }

    public static boolean isMirrorReflection(String date) {
        String reversedDate = new StringBuilder(date).reverse().toString();
        return date.equals(reversedDate);
    }
}

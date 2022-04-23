package pl.pjait;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {

    public static void main(String[] args)
    {

        String name;
        do
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter Name: ");
            while (!myObj.hasNext("[A-Za-z]+"))
            {
                System.out.println("Used incorrect char, try again");
                myObj.next();
            }
            name = myObj.next();
        }
        while( name.length() < 2 );

        String surname;
        do
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter Surname: ");
            while (!myObj.hasNext("[A-Za-z]+"))
            {
                System.out.println("Used incorrect char, try again");
                myObj.next();
            }
            surname = myObj.next();
        }
        while( surname.length() < 2 );

        String pesel;
        do {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter PESEL: ");
            pesel = myObj.nextLine();
            if (isPeselValid(pesel) == false)
            {
                System.out.println("PESEL Incorrect");
            }
        }
        while (isPeselValid(pesel) == false);

        String nip;
        do {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter NIP: ");
            nip = myObj.nextLine();
            if (isValidNIP(nip) == false)
            {
                System.out.println("NIP Incorrect");
            }
        }
        while (isValidNIP(nip) == false);

        String accountNum;
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Bank Account NUM: ");
        while (!myObj.hasNext("[0-9]{6}")){
            System.out.println("Used incorrect char or too short, try again");
            myObj.next();
        }
        accountNum = myObj.next();

        String year;
        do {
            Scanner nameObj = new Scanner(System.in);
            System.out.println("Enter Year");
            year = nameObj.nextLine();
        }
        while (!isYearValid(pesel, year));

        String month;
        do {
            Scanner nameObj = new Scanner(System.in);
            System.out.println("Enter Month");
            month = nameObj.nextLine();
        }
        while (!isMonthValid(pesel, month, year));

        String day;
        do {
            Scanner nameObj = new Scanner(System.in);
            System.out.println("Enter Day");
            day = nameObj.nextLine();
        }
        while (!isDayValid(pesel, day));

        String gender;
        do{
            Scanner myOtherObj = new Scanner(System.in);
            System.out.println("Enter Gender: ");
            gender = myOtherObj.nextLine();
        }
        while(!isGenderValid(pesel, gender));

        int intYear = Integer.parseInt(year);
        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        LocalDate date = LocalDate.of(intYear, intMonth, intDay);
        LoanApplication loanApplication = new LoanApplication(name, surname, pesel, nip, accountNum, date, gender);
    }

    public static boolean isPeselValid(String pesel)
    {
        int[] weight = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        if (pesel.length() != 11) return false;
        int sum = 0;
        for (int i=0; i < 10; i++)
        {
            sum += Integer.parseInt(pesel.substring(i, i+1)) * weight[i];
        }
        int controlNum = Integer.parseInt(pesel.substring(10, 11));

        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        return (sum == controlNum);
    }

    public static boolean isValidNIP(String nip)
    {
        int nsize = nip.length();
        if (nsize != 10)
        {
            return false;
        }
        int[] weights = { 6, 5, 7, 2, 3, 4, 5, 6, 7 };
        int j = 0, sum = 0, control = 0;
        int csum = Integer.valueOf(nip.substring(nsize - 1));
        for (int i = 0; i < nsize - 1; i++)
        {
            char c = nip.charAt(i);
            j = Integer.valueOf(String.valueOf(c));
            sum += j * weights[i];
        }
        control = sum % 11;
        return (control == csum);
    }

    public static boolean isGenderValid(String pesel, String gender)
    {
        if (!gender.equals("female") && !gender.equals("male"))
        {
            System.out.println("Incorrect Gender, try again");
            return false;
        }
        if (Integer.parseInt(pesel.substring(9, 10)) % 2 == 0 && gender.equals("male"))
        {
            System.out.println("Doesn't match");
            return false;
        }
        if (Integer.parseInt(pesel.substring(9, 10)) % 2 == 1 && gender.equals("female"))
        {
            System.out.println("Doesn't match");
            return false;
        }
        return true;
    }

    public static boolean isYearValid(String pesel, String yearDate)
    {
        if (Integer.parseInt(yearDate) < 1900 || Integer.parseInt(yearDate) > 2020)
        {
            System.out.println("Invalid Year");
            return false;
        }

        int yearNum = Integer.parseInt(pesel.substring(0, 1));
        int year = Integer.parseInt(yearDate.substring(2, 3));
        if (yearNum != year)
        {
            System.out.println("Doesn't match");
            return false;
        }
        return true;
    }

    public static boolean isMonthValid(String pesel, String monthDate, String yearDate)
    {
        if (Integer.parseInt(monthDate) < 1 || Integer.parseInt(monthDate) > 12)
        {
            System.out.println("Invalid month");
            return false;
        }

        int monthNum = Integer.parseInt(pesel.substring(2, 3));
        int month = Integer.parseInt(monthDate.substring(0, 1));
        if (Integer.valueOf(yearDate) > 1999)
        {
            month = month + 20;
        }

        if (monthNum != month)
        {
            System.out.println("Doesn't match");
            return false;
        }
        return true;
    }

    public static boolean isDayValid(String pesel, String dayDate)
    {
        if (Integer.parseInt(dayDate) < 1 || Integer.parseInt(dayDate) > 31)
        {
            System.out.println("Invalid day");
            return false;
        }

        int dayNum = Integer.parseInt(pesel.substring(4, 5));
        int day = Integer.parseInt(dayDate.substring(0, 1));
        if (dayNum != day)
        {
            System.out.println("Doesn't match");

            return false;
        }
        return true;
    }

}

package com.codecool.ehotel.service.scanner;

public class ScannerImpl implements Scanner {
    public String getStringInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }

    public int getIntInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return  scanner.nextInt();
    }
}

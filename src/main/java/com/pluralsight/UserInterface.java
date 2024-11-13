package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;
    private OrderFileManager fileManager;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new OrderFileManager();
    }
}
package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

import java.util.List;
import java.util.Scanner;

public class DisplayMenu {

    private static final Scanner keyboard = new Scanner(System.in);
    public static void main( String[] args )
    {
        boolean exit = false;

        String userInput;
        while(!exit) {
            printMainMenuInstructions();
            int choice = keyboard.nextInt();
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    System.out.println("Exiting the app...");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid Input. Try again.");
                    break;

            }
        }
    }





    private static void printMainMenuInstructions() {
        System.out.println("======================================  WELCOME ! ==============================");
        System.out.println("|   ----------------------------------    MENU   ----------------------------  |");
        System.out.println("|   |                          Please Select an option:                     |  |");
        System.out.println("|   |                                0. Exit App                            |  |");
        System.out.println("|   |                          1. Display All Artists                       |  |");
        System.out.println("|   |                          2. Dispaly Artist By Id                      |  |");
        System.out.println("|   |                          3. Delete Artist By Id                       |  |");
        System.out.println("|   |                               4. Add Artist                           |  |");
        System.out.println("|   -------------------------------------------------------------------------  |");
        System.out.println("================================================================================");
    }
    }

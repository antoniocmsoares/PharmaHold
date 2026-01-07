// INTERFACE/MENU BUILDER
package pt.ipp.estg.pp.pharmahold;

import java.util.*;

public final class Interface { // so u cant overwrite it

    static Scanner input = new Scanner(System.in);

    // input int value
    public static int readInt() {
        int content = -9999;
        while (content == -9999) {
            try {
                content = input.nextInt();
            } catch (Exception exc) {
                if (content > 999999999){
                    System.out.println("Please enter a number up to 9 digits");
                }
                System.out.println("Please enter a valid number");
                input.nextLine(); // CLEANS BUFFER
            }
        }
        return content;
    }

    /*
     * public static String readString() {
     * String content = null;
     * content = input.nextLine();
     * return content;
     * }
     */

    // draw menu title
    public static void drawTitle(String content, int extraWidth) {
        int spacing = 23 + extraWidth;
        int width = content.length() + spacing;

        // Top border line
        System.out.print("┌");
        for (int i = 0; i < width; i++) {
            System.out.print("─");
        }
        System.out.println("┐");

        // Calculate left and right padding
        int startPadding = spacing / 2; // spaces before the text
        int endPadding = spacing / 2; // spaces after the text

        // Fix bad spacing when theres a coma
        if (spacing % 2 != 0) {
            endPadding++;
        }

        // Print the content line with padding
        System.out.print("│");
        for (int i = 0; i < startPadding; i++) {
            System.out.print(" ");
        }
        System.out.print(content);
        for (int i = 0; i < endPadding; i++) {
            System.out.print(" ");
        }
        System.out.println("│");

        // Bottom line
        System.out.print("└");
        for (int i = 0; i < width; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }

    // draw choices
    public static void drawButton(String content) {
        int width = content.length();

        // top line
        System.out.print("┌");
        for (int i = 0; i < width + 2; i++) {
            System.out.print("─");
        }
        System.out.println("┐");

        // button content
        System.out.println("│ " + content + " │");

        // bottom line
        System.out.print("└");
        for (int i = 0; i < width + 2; i++) {
            System.out.print("─");
        }
        System.out.println("┘");
    }

    // draw multiple choices
    public static void drawButtonList(String margin, String... contents) {
        if ("def".equals(margin)) {
            margin = "   ";
        }

        // Calc width for every button
        int[] widths = new int[contents.length];
        for (int i = 0; i < contents.length; i++) {
            widths[i] = contents[i].length();
        }

        // Line of top decoration
        for (int i = 0; i < contents.length; i++) {
            System.out.print("┌");
            for (int j = 0; j < widths[i] + 2; j++) {
                System.out.print("─");
            }
            System.out.print("┐" + margin);
        }
        System.out.println();

        // Line of the button content
        for (int i = 0; i < contents.length; i++) {
            System.out.print("│ " + contents[i] + " │");
            System.out.print(margin);
        }
        System.out.println();

        // Line of bottom decoration
        for (int i = 0; i < contents.length; i++) {
            System.out.print("└");
            for (int j = 0; j < widths[i] + 2; j++) {
                System.out.print("─");
            }
            System.out.print("┘" + margin);
        }
        System.out.println();
    }

    // input for menu choice
    public static int drawInput(int width) {
        int userChoice = -99;
        System.out.print("\n\n");
        System.out.print("┌");
        for (int i = 0; i < width - 1; i++) {
            System.out.print("─");
        }
        System.out.print("\n│ YOUR CHOICE: ");
        userChoice = readInt();
        return userChoice;
    }

    public static void drawFormInput(String placeholder, int width) {
        int totalWidth = width;
        placeholder = " " + placeholder + " ";
        System.out.print("┌");
        System.out.print(placeholder);
        for (int i = 0; i < totalWidth - placeholder.length() - 1; i++) {
            System.out.print("─");
        }
        System.out.println();
        System.out.print("│ ");
    }

    // draw perfil user
    public static void drawPerfil(Client user) {
    try {
        String name = user.getName();
        String address = user.getAddress();
        int points = user.getTotalPoints();
        double phone = user.getContact(); // FORGOT TO DEFINE METHOD IN USERS
    } catch (Exception e){
        System.out.println("Something happened, please try again.");
        return;
    }

        System.out.println("┌────────┐ ┌─────────────────────────────────────────\n│ ┌────┐ │ │ name: " + user.getName()+ "\n│ │ ?? │ │ │ phone: " + String.valueOf(user.getContact()).substring(0,3) + " " + String.valueOf(user.getContact()).substring(3,6) + " " + String.valueOf(user.getContact()).substring(6, 9) + " " +"\n├─┘    └─┤ │ address: " + user.getAddress() + "\n└────────┘ └─────────────────────────────────────────");
        Interface.drawButtonList(" ", "points: " + user.getTotalPoints(), "orders: " + user.getAllActiveOrders().size(), "prescription: " + user.getPrescriptionsList().size());

    }

    public static void drawClientMenu() {

    }

    public static void wait(int seconds) {
        seconds = seconds * 1000;
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // clears terminal, used every login/signup/choice
    public static void newWindow() {
        System.out.print("\n\n\n \033[H\033[2J"); // \033 move cursor to top \\033[2J cleans the content of the screen
    }
}
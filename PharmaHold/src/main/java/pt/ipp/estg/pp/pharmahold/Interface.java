package pt.ipp.estg.pp.pharmahold;

import java.time.YearMonth;
import java.util.Scanner;

public final class Interface {       //so u cant overwrite it

    static Scanner input = new Scanner(System.in);

    public static void drawTitle(String content, int extraWidth) {
        int spacing = 23 + extraWidth;
        int width = content.length() + spacing;

        // Top border line
        System.out.print("<");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println(">");

        // Calculate left and right padding
        int startPadding = spacing / 2; // spaces before the text
        int endPadding = spacing / 2;   // spaces after the text

        // Fix bad spacing when theres a coma
        if (spacing % 2 != 0) {
            endPadding++;
        }

        // Print the content line with padding
        System.out.print("|");
        for (int i = 0; i < startPadding; i++) {
            System.out.print(" ");
        }
        System.out.print(content);
        for (int i = 0; i < endPadding; i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        // Bottom line
        System.out.print("<");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println(">");
    }

    public static void drawButton(String content) {
        int width = content.length();

        // top line
        System.out.print("+");
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // button content
        System.out.println("| " + content + " |");

        // bottom line
        System.out.print("+");
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    public static void drawButtonList(String margin, String... contents) {
        if (margin == "def") {
            margin = "   ";
        }

        // Calc width for every button
        int[] widths = new int[contents.length];
        for (int i = 0; i < contents.length; i++) {
            widths[i] = contents[i].length();
        }

        // Line of top decoration
        for (int i = 0; i < contents.length; i++) {
            System.out.print("+");
            for (int j = 0; j < widths[i] + 2; j++) {
                System.out.print("-");
            }
            System.out.print("+" + margin);
        }
        System.out.println();

        // Line of the button content
        for (int i = 0; i < contents.length; i++) {
            System.out.print("| " + contents[i] + " |");
            System.out.print(margin);
        }
        System.out.println();

        // Line of bottom decoration
        for (int i = 0; i < contents.length; i++) {
            System.out.print("+");
            for (int j = 0; j < widths[i] + 2; j++) {
                System.out.print("-");
            }
            System.out.print("+" + margin);
        }
        System.out.println();
    }

    public static int drawInput(int width) {
        System.out.print("\n\n");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.print("\n| YOUR CHOICE: ");
        int userChoice = input.nextInt();
        return userChoice;
    }

    public static String drawFormInput(String placeholder, int width) {
        int totalWidth = width;
        int count = 0;
        placeholder = " " + placeholder + " ";
        System.out.print("-");
        System.out.print(placeholder);
        for (int i = 0; i < totalWidth - placeholder.length() - 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("| ");
        // INPUT CONTAINER
        count++;
        if (count > 0) {                                    // HAS LIMITATIONS
            input.next();
        }
        String userChoice = input.nextLine();

        return userChoice;
    }

    public static void drawPerfil(String name, String tef, String mor) {
    int leftWidth = 12;
    int rightWidth = 33;

    // linha superior
    for (int i = 0; i < leftWidth; i++) System.out.print("-");
    System.out.print("  ");
    for (int i = 0; i < rightWidth; i++) System.out.print("-");
    System.out.println();

    // linha 1: boneco + nome
    System.out.print("|   .--.   |  ");
    System.out.printf("| nam: %-24s |\n", name);

    // linha 2: boneco + telefone
    System.out.print("|  |.__.|  |  ");
    System.out.printf("| tef: %-24s |\n", tef);

    // linha 3: boneco + morada
    System.out.print("|  \\____/  |  ");
    System.out.printf("| mor: %-24s |\n", mor);

    // linha inferior
    for (int i = 0; i < leftWidth; i++) System.out.print("-");
    System.out.print("  ");
    for (int i = 0; i < rightWidth; i++) System.out.print("-");
    System.out.println();
}


    public static void newWindow() {
        System.out.print("\n\n\n \033[H\033[2J"); //\033 mov cursor to top \\033[2J cleans the content of the screan
    }
}

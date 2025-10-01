package seedu.sgsafe;
import java.util.Scanner;

public class SGSafe {
    public static void main(String[] args) {
        System.out.println("This is SGSafe");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}

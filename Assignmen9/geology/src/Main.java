import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of x1: ");
        int x1 = scanner.nextInt();

        System.out.print("Enter the value of x2: ");
        int x2 = scanner.nextInt();

        System.out.println("You entered x1 = " + x1 + " and x2 = " + x2);
        Landscape landscape = new Landscape();
        Landscape.Modification modification = new Landscape.Modification(x1, x2, Landscape.Operation.HILL);

        scanner.close();
    }
}

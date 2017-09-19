import java.util.ArrayList;
import java.util.Scanner;

//main frontend for simulating using the vending machine
public class VendMachineRunner {
    public static void main(String[] args) {
        Coin quarter = new Coin(0.2, 0.955);
        Coin nickel = new Coin(0.176, 0.835);
        Coin dime = new Coin(0.08, 0.705);
        Coin penny = new Coin(0.088, 0.750);

        ArrayList<Coin> initialChange = new ArrayList<Coin>();
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(dime));
        initialChange.add(new Coin(dime));
        initialChange.add(new Coin(nickel));

        User currentUser = new User(initialChange);

        VendMachine vendMachine = new VendMachine(new Integer[]{3, 3, 3, Integer.MAX_VALUE}, new MenuItem[]{new MenuItem(1.00, "Cola"), new MenuItem(0.50, "Chips"),
                new MenuItem(0.65, "Candy"), new MenuItem(0.00, "Coin Return")}, new Integer[]{5, 5, 5});

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Vend-O-Matic 2000");





    }
}

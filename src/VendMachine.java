import java.lang.Object.*;
import java.text.DecimalFormat;

public class VendMachine {

    private final Coin quarter = new Coin(0.2, 0.955);
    private final Coin nickel = new Coin(0.176, 0.835);
    private final Coin dime = new Coin(0.08, 0.705);
    private double moneyIn = 0.00;
    DecimalFormat df = new DecimalFormat("0.00");



    private final MenuItem cola = new MenuItem(1.00, "Cola");
    private final MenuItem chips = new MenuItem(0.50, "Chips");
    private final MenuItem candy = new MenuItem(0.65, "Candy");
    MenuItem[] menu = {cola, chips, candy};


    public String checkDisplay() {

        if (moneyIn == 0) {
            return "INSERT COIN";
        }

        else {
            return "$"+df.format(moneyIn);
        }
    }

    public String insertCoin(Coin coin) {
        double tolerance = 0.001;
        if (((Math.abs(coin.diameter-nickel.diameter)<=tolerance)  && (Math.abs(coin.weight-nickel.weight)<=tolerance))) {
            moneyIn = moneyIn + 0.05;
            return "$"+df.format(moneyIn);
        }

        else if (((Math.abs(coin.diameter-quarter.diameter)<=tolerance)  && (Math.abs(coin.weight-quarter.weight)<=tolerance))) {
            moneyIn = moneyIn + 0.25;
            return "$"+df.format(moneyIn);
        }

        else if (((Math.abs(coin.diameter-dime.diameter)<=tolerance)  && (Math.abs(coin.weight-dime.weight)<=tolerance))) {
            moneyIn = moneyIn + 0.10;
            return "$"+df.format(moneyIn);
        }

        else {
            if (moneyIn == 0) {
                return "INSERT COIN";
            }

            else {
                return "$"+df.format(moneyIn);
            }
        }
    }

    public String buyProduct(Integer selection) {
        if (moneyIn >= menu[selection].getPrice()) {
            moneyIn = 0.00;
            return "THANK YOU";
        }

        else {
            return "PRICE: $"+df.format(menu[selection].getPrice());
        }
    }
}

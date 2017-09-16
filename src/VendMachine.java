import java.lang.Object.*;
import java.text.DecimalFormat;

public class VendMachine {

    private final Coin quarter = new Coin(0.2, 0.955);
    private final Coin nickel = new Coin(0.176, 0.835);
    private final Coin dime = new Coin(0.08, 0.705);
    private double moneyIn = 0.00;
    DecimalFormat df = new DecimalFormat("0.00");

    public String checkDisplay() {
        return "INSERT COIN";
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
}

import java.lang.Object.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class VendMachine {

    private static final Coin quarter = new Coin(0.2, 0.955);
    private static final Coin nickel = new Coin(0.176, 0.835);
    private static final Coin dime = new Coin(0.08, 0.705);

    private double moneyIn = 0.00;

    DecimalFormat df = new DecimalFormat("0.00");

    CoinIdentifier identifier = new USCoinIdentifier();

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
        String coinType=identifier.identifyCoin(coin);
        if (coinType == "nickel") {
            moneyIn = moneyIn + 0.05;
            return "$"+df.format(moneyIn);
        }

        else if (coinType == "quarter") {
            moneyIn = moneyIn + 0.25;
            return "$"+df.format(moneyIn);
        }

        else if (coinType == "dime") {
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

    public String buyProduct(Integer selection, User user) {
        if (moneyIn == menu[selection].getPrice()) {
            moneyIn = 0.00;
            return "THANK YOU";
        }

        else if (moneyIn > menu[selection].getPrice()){
            moneyIn=moneyIn-menu[selection].getPrice();
            ArrayList coinReturn = new ArrayList();
            while (moneyIn>0.00){
                while (moneyIn>=0.249){
                    coinReturn.add(new Coin(quarter));
                    moneyIn=moneyIn-0.25;
                }

                while (moneyIn>=0.099){
                    coinReturn.add(new Coin(dime));
                    moneyIn=moneyIn-0.10;
                }

                while (moneyIn>=0.049){
                    coinReturn.add(new Coin(nickel));
                    moneyIn=moneyIn-0.05;
                }
            }
            user.pocket.addAll(coinReturn);

            return "THANK YOU";
        }

        else {
            return "PRICE: $"+df.format(menu[selection].getPrice());
        }
    }
}

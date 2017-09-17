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
    private final MenuItem returns = new MenuItem(0.00, "Coin Return");
    MenuItem[] menu = {cola, chips, candy, returns};

    private Integer[] stock = {1, 1, 3, Integer.MAX_VALUE};

    //This is in format lowest denomination accepted by the machine -> highest
    private Integer[] changeStore = {1, 2, 0};

    private boolean exactChangeOnly(){
        if (changeStore[0] == 0){
            return true;
        }

        else if (((changeStore[0] * 0.05) + (changeStore[1] *0.10)) < 0.20) {
            return true;
        }

        else {
            return false;
        }
    }

    public String checkDisplay() {
        boolean ex = exactChangeOnly();
        if (moneyIn == 0 && !(exactChangeOnly())) {
            return "INSERT COIN";
        }

        else if (moneyIn == 0 && (exactChangeOnly())){
            System.out.println();
            return "EXACT CHANGE ONLY";
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
            changeStore[0]++;
            return "$"+df.format(moneyIn);
        }

        else if (coinType == "quarter") {
            moneyIn = moneyIn + 0.25;
            changeStore[2]++;
            return "$"+df.format(moneyIn);
        }

        else if (coinType == "dime") {
            moneyIn = moneyIn + 0.10;
            changeStore[1]++;
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
        if (stock[selection] == 0) {
            return "SOLD OUT";
        }

        if ((moneyIn > menu[selection].getPrice()) && (exactChangeOnly())) {
            Integer b = 1;
            return "EXACT CHANGE ONLY";
        }

        if (moneyIn == menu[selection].getPrice()) {
            moneyIn = 0.00;
            stock[selection]--;
            return "THANK YOU";
        }

        else if (moneyIn > menu[selection].getPrice()){
            moneyIn=moneyIn-menu[selection].getPrice();
            ArrayList coinReturn = new ArrayList();
            while (moneyIn>0.00 && moneyIn>=0.049){
                while (moneyIn>=0.249 && changeStore[2]>0){
                    coinReturn.add(new Coin(quarter));
                    moneyIn=moneyIn-0.25;
                    changeStore[2]--;
                }

                while (moneyIn>=0.099 && changeStore[1]>0){
                    coinReturn.add(new Coin(dime));
                    moneyIn=moneyIn-0.10;
                    changeStore[1]--;
                }

                while (moneyIn>=0.049 && changeStore[0]>0){
                    coinReturn.add(new Coin(nickel));
                    moneyIn=moneyIn-0.05;
                    changeStore[0]--;
                }
            }
            user.pocket.addAll(coinReturn);
            moneyIn = 0.00;
            stock[selection]--;
            return "THANK YOU";
        }

        else {
            return "PRICE: $"+df.format(menu[selection].getPrice());
        }
    }

    public String returnCoin(User user) {
        buyProduct(3, user);
        return "INSERT COIN";
    }
}

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class VendMachine {

    private static final Coin quarter = new Coin(0.2, 0.955);
    private static final Coin nickel = new Coin(0.176, 0.835);
    private static final Coin dime = new Coin(0.08, 0.705);

    private double moneyIn = 0.00;

    private DecimalFormat df = new DecimalFormat("0.00");

    private CoinIdentifier identifier = new USCoinIdentifier();

    private MenuItem[] menu;

    private Integer[] stock;

    private Integer coinReturnIndex;

    //This is in format lowest denomination accepted by the machine -> highest
    private Integer[] changeStore;

    public VendMachine (Integer[] stock, MenuItem[] menu, Integer[] changeStore) {
        this.stock=stock;
        this.menu=menu;
        this.changeStore=changeStore;
        coinReturnIndex=stock.length-1;
    }

    private boolean exactChangeOnly() {
        return changeStore[0] == 0 || ((changeStore[0] * 0.05) + (changeStore[1] * 0.10)) < 0.20;
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

    public String insertCoin(Coin coin, User user) {
        double tolerance = 0.001;
        String coinType=identifier.identifyCoin(coin);
        if (Objects.equals(coinType, "nickel")) {
            moneyIn = moneyIn + 0.05;
            changeStore[0]++;
            return "$"+df.format(moneyIn);
        } else {
            if (Objects.equals(coinType, "quarter")) {
                moneyIn = moneyIn + 0.25;
                changeStore[2]++;
                return "$"+df.format(moneyIn);
            }

            else if (Objects.equals(coinType, "dime")) {
                moneyIn = moneyIn + 0.10;
                changeStore[1]++;
                return "$"+df.format(moneyIn);
            }

            else {
                user.pocket.add(coin);
                if (moneyIn == 0) {
                    return "INSERT COIN";
                }

                else {
                    return "$"+df.format(moneyIn);
                }
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
            ArrayList<Coin> coinReturn = new ArrayList<>();
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
        buyProduct(coinReturnIndex, user);
        return "INSERT COIN";
    }
}

import java.text.DecimalFormat;
import java.util.*;

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

    private Queue<Coin> nickelStore;

    private Queue<Coin> dimeStore;

    private Queue<Coin> quarterStore;

    //Stock is in format lowest denomination accepted by the machine -> highest

    public VendMachine (Integer[] stock, MenuItem[] menu, Integer[] changeStore) {
        this.stock=stock;
        this.menu=menu;
        coinReturnIndex=stock.length-1;
        nickelStore = new LinkedList<Coin>();
        dimeStore = new LinkedList<Coin>();
        quarterStore = new LinkedList<Coin>();
        for (int i=0; i < changeStore[0]; i++) {
            nickelStore.add(new Coin(nickel));
        }

        for (int i=0; i < changeStore[1]; i++) {
            dimeStore.add(new Coin(dime));
        }

        for (int i=0; i < changeStore[2]; i++) {
            quarterStore.add(new Coin(quarter));
        }
    }

    private boolean exactChangeOnly() {
        return nickelStore.size() == 0 || ((nickelStore.size()) * 0.05 + (dimeStore.size() * 0.10)) < 0.20;
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
        String coinType=identifier.identifyCoin(coin);
        if (Objects.equals(coinType, "nickel")) {
            moneyIn = moneyIn + 0.05;
            nickelStore.add(coin);
            return "$"+df.format(moneyIn);
        }

        else if (Objects.equals(coinType, "dime")) {
            moneyIn = moneyIn + 0.10;
            dimeStore.add(coin);
            return "$"+df.format(moneyIn);
        }

        else if (Objects.equals(coinType, "quarter")){
            moneyIn = moneyIn + 0.25;
            quarterStore.add(coin);
            return "$" + df.format(moneyIn);
        }

        else {
            user.giveCoin(coin);
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
            ArrayList<Coin> coinReturn = new ArrayList<>();
            while (moneyIn>0.00 && moneyIn>=0.049){
                while (moneyIn>=0.249 && quarterStore.size()>0){
                    coinReturn.add(quarterStore.remove());
                    moneyIn=moneyIn-0.25;
                }

                while (moneyIn>=0.099 && dimeStore.size()>0){
                    coinReturn.add(dimeStore.remove());
                    moneyIn=moneyIn-0.10;
                }

                while (moneyIn>=0.049 && nickelStore.size()>0){
                    coinReturn.add(nickelStore.remove());
                    moneyIn=moneyIn-0.05;
                }
            }
            user.giveCoin(coinReturn);
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

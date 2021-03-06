import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class User {

    private Queue<Coin> nickelPocket;

    private Queue<Coin> dimePocket;

    private Queue<Coin> quarterPocket;

    private Queue<Coin> otherCoinPocket;

    private CoinIdentifier identifier = new USCoinIdentifier();

    public User(){
        nickelPocket = new LinkedList<Coin>();
        dimePocket = new LinkedList<Coin>();
        quarterPocket = new LinkedList<Coin>();
        otherCoinPocket = new LinkedList<Coin>();
    }

    public User(ArrayList<Coin> init){
        nickelPocket = new LinkedList<Coin>();
        dimePocket = new LinkedList<Coin>();
        quarterPocket = new LinkedList<Coin>();
        otherCoinPocket = new LinkedList<Coin>();
        giveCoin(init);
    }

    public String checkMoney(){
        return "You have "+nickelPocket.size()+" nickel(s), " +dimePocket.size()+" dime(s), " +quarterPocket.size()+" quarter(s), and "+otherCoinPocket.size()+" unusable coin(s).";
    }

    public void giveCoin(Coin coin) {
        String coinType = identifier.identifyCoin(coin);
        if (Objects.equals(coinType, "nickel")) {
            nickelPocket.add(coin);
        }

        else if (Objects.equals(coinType, "quarter")) {
            quarterPocket.add(coin);
        }

        else if (Objects.equals(coinType, "dime")) {
            dimePocket.add(coin);
        }

        else {
            otherCoinPocket.add(coin);
        }
    }

    public void giveCoin(ArrayList<Coin> coins) {
        for (Coin o: coins) {
            giveCoin(o);
        }
    }

    public Integer getNumQuarters() {
        return quarterPocket.size();
    }

    public Coin takeQuarter() {
        return quarterPocket.poll();
    }

    public Integer getNumDimes() {
        return dimePocket.size();
    }

    public Coin takeDime() {
        return dimePocket.poll();
    }

    public Integer getNumNickels() {
        return nickelPocket.size();
    }

    public Coin takeNickel() {
        return nickelPocket.poll();
    }

    public Integer getNumOther() {
        return otherCoinPocket.size();
    }

    public Coin takeOther() {
        return otherCoinPocket.poll();
    }
}

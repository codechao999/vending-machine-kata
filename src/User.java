import java.util.ArrayList;
import java.util.Objects;

public class User {

    ArrayList<Coin> pocket;

    private CoinIdentifier identifier = new USCoinIdentifier();



    public User(){
        pocket = new ArrayList<>();
    }

    public User(ArrayList<Coin> init){
        pocket = new ArrayList<>(init);
    }

    public String checkMoney(){
        Integer dimeNum = 0, nickelNum = 0, quarterNum = 0, unusableNum = 0;
        for (Object o: pocket) {
            if (o instanceof Coin) {
                Coin coin = (Coin) o;
                String coinType = identifier.identifyCoin(coin);
                double tolerance = 0.001;
                if (Objects.equals(coinType, "nickel")) {
                    nickelNum++;
                }

                else if (Objects.equals(coinType, "quarter")) {
                    quarterNum++;
                }

                else if (Objects.equals(coinType, "dime")) {
                    dimeNum++;
                }

                else {
                    unusableNum++;
                }
            }
        }

        return "You have "+nickelNum.toString()+" nickel(s), " +dimeNum.toString()+" dime(s), " +quarterNum.toString()+" quarter(s), and "+unusableNum.toString()+" unusable coin(s).";
    }
}

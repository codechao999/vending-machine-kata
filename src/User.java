import java.util.ArrayList;

public class User {
    ArrayList pocket;
    private final Coin quarter = new Coin(0.2, 0.955);
    private final Coin nickel = new Coin(0.176, 0.835);
    private final Coin dime = new Coin(0.08, 0.705);
    public User(){
        pocket = new ArrayList();
    }

    public User(ArrayList init){
        pocket = new ArrayList(init);
    }

    public String checkMoney(){
        Integer dimeNum = 0, nickelNum = 0, quarterNum = 0, unusableNum = 0;
        for (Object o: pocket) {
            if (o instanceof Coin) {

                Coin coin = (Coin) o;
                double tolerance = 0.001;
                if (((Math.abs(coin.diameter-nickel.diameter)<=tolerance)  && (Math.abs(coin.weight-nickel.weight)<=tolerance))) {
                    nickelNum++;
                }

                else if (((Math.abs(coin.diameter-quarter.diameter)<=tolerance)  && (Math.abs(coin.weight-quarter.weight)<=tolerance))) {
                    quarterNum++;
                }

                else if (((Math.abs(coin.diameter-dime.diameter)<=tolerance)  && (Math.abs(coin.weight-dime.weight)<=tolerance))) {
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

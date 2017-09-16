public class USCoinIdentifier implements CoinIdentifier {

    private static final Coin quarter = new Coin(0.2, 0.955);
    private static final Coin nickel = new Coin(0.176, 0.835);
    private static final Coin dime = new Coin(0.08, 0.705);

    public String identifyCoin(Coin coin) {
        double tolerance = 0.001;
        if (((Math.abs(coin.diameter-nickel.diameter)<=tolerance)  && (Math.abs(coin.weight-nickel.weight)<=tolerance))) {
            return "nickel";
        }

        else if (((Math.abs(coin.diameter-quarter.diameter)<=tolerance)  && (Math.abs(coin.weight-quarter.weight)<=tolerance))) {
            return "quarter";
        }

        else if (((Math.abs(coin.diameter-dime.diameter)<=tolerance)  && (Math.abs(coin.weight-dime.weight)<=tolerance))) {
            return "dime";
        }

        else {
            return "reject";
        }
    }
}

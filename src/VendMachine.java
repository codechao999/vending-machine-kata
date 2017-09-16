public class VendMachine {

    private Coin quarter = new Coin(0.2, 0.955);
    private Coin nickel = new Coin(0.176, 0.835);
    private Coin dime = new Coin(0.08, 0.705);

    public String checkDisplay() {
        return "INSERT COIN";
    }

    public String insertCoin(Coin coin) {
        double tolerance = 0.001;
        if (((Math.abs(coin.diameter-nickel.diameter)<=tolerance)  && (Math.abs(coin.weight-nickel.weight)<=tolerance))) {
            return "$0.05";
        }

        else if (((Math.abs(coin.diameter-quarter.diameter)<=tolerance)  && (Math.abs(coin.weight-quarter.weight)<=tolerance))) {
            return "$0.25";
        }

        else if (((Math.abs(coin.diameter-dime.diameter)<=tolerance)  && (Math.abs(coin.weight-dime.weight)<=tolerance))) {
            return "$0.10";
        }

        else {
            return "INSERT COIN";
        }
    }
}

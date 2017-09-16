public class Coin {
    final double weight;
    final double diameter;

    public Coin(double weight, double diameter){
        this.weight = weight;
        this.diameter = diameter;
    }

    public Coin (Coin c) {
        this.weight = c.weight;
        this.diameter = c.diameter;
    }
}

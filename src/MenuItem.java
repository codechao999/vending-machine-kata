public class MenuItem {
    private double price;
    public String item;

    public MenuItem(double price, String item){
        this.price = price;
        this.item = item;
    }

    public double getPrice(){
        return price;
    }
}

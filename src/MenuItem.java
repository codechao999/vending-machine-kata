public class MenuItem {
    private double price;
    private String item;

    public MenuItem(double price, String item){
        this.price = price;
        this.item = item;
    }

    public double getPrice(){
        return price;
    }

    public String getItem() {
        return item;
    }
}

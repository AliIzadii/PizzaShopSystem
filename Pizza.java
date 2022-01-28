public class Pizza extends Food{
    private static int lastPizzaId;


    public Pizza(String name, double price){
        this.type = FoodType.Pizza;
        this.name = name;
        this.price = price;
        lastPizzaId++;
        this.foodId = lastPizzaId;
    }
}

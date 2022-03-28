public class Sandwich extends Food {
    private static int lastSandwichId;
    public Sandwich(String name, double price) {
        this.type = FoodType.Sandwich;
        this.name = name;
        this.price = price;
        lastSandwichId++;
        this.foodId = lastSandwichId;
    }
}

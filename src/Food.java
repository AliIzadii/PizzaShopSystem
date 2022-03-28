public abstract class Food {
    protected String name;
    protected int foodId;
    protected double price;
    protected FoodType type;
    int sellCount;
    public enum FoodType {
        Pizza,
        Sandwich,
    }
    public double getPrice(){
        return price;
    }
    public FoodType getType(){
        return type;
    }

    public String getName() {
        return name;
    }

    public int getSellCount() {
        return sellCount;
    }

    public int getFoodId() {
        return foodId;
    }
}

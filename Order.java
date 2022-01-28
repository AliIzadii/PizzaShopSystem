import java.util.ArrayList;

public class Order {
    int customerId;
    int sellerId;
    ArrayList<Food> foodList;
    int orderId;
    boolean hasDelivery;
    boolean hasService;
    private static int lastOrderId;

    double foodPrice;
    double servicePrice;
    double discount;
    double totalPrice;
    double totalPriceWithDiscount;



    public Order(int customerId, int sellerId){
        foodList = new ArrayList<Food>();
        this.customerId = customerId;
        this.sellerId = sellerId;
        lastOrderId++;
        this.orderId = lastOrderId;
    }
    public void add(Food food, int count){
        for(int i=0;i<count;i++){
            this.foodList.add(food);
            this.foodPrice += food.getPrice();
            food.sellCount++;
        }
    }
}

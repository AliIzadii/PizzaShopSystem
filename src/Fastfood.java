import java.util.ArrayList;

public class Fastfood {
    private ArrayList<Customer> customers;
    private ArrayList<Seller> sellers;
    private ArrayList<Food> foodList;
    private ArrayList<Order> orders;
    private int totalSellAmount;
    private double totalSell;
    private final double SERVICE_COST;
    private final double DELIVERY_COST;
    private final double CUSTOMER_DISCOUNT;  // if customer buy more than 10
    private final double SELLER_DISCOUNT_PERCENT;
    private final double CUSTOMER_REFERRER_DISCOUNT;
    private final double SELLER_DISCOUNT; // if seller sell more than 10


    public Fastfood(double serviceCost, double deliveryCost,
                    double customerDiscount, double sellerDiscountPercent,
                    double sellerDiscount,double customerReferrerDiscount){


        this.CUSTOMER_DISCOUNT = customerDiscount;
        this.SELLER_DISCOUNT_PERCENT = sellerDiscountPercent;
        this.SERVICE_COST = serviceCost;
        this.DELIVERY_COST = deliveryCost;
        this.CUSTOMER_REFERRER_DISCOUNT = customerReferrerDiscount;
        this.SELLER_DISCOUNT = sellerDiscount;

        foodList = new ArrayList<Food>();
        customers = new ArrayList<Customer>();
        sellers = new ArrayList<Seller>();
        orders = new ArrayList<Order>();
    }
    public void addFood(Food food){
        foodList.add(food);
    }

    public Food getFood(String name){

        for (Food food : foodList) {
            if(food.getName().equals(name))
                return food;
        }
        return null;
    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }
    public void addSeller(Seller seller){
        Customer customer = new Customer(seller.name,seller.family,0,seller.address,seller.phone);
        seller.customerId = customer.customerId;
        this.addCustomer(customer);
        this.sellers.add(seller);
    }
    public double newOrder(Order order){

        Customer customer = findCustomerById(order.customerId);
        Seller seller = findSellerById(order.sellerId);
        int count = order.foodList.size();
        totalSell += order.foodPrice;
        totalSellAmount += order.foodList.size();



        double totalPrice = order.foodPrice; // food + service
        double totalPriceWithDiscount = order.foodPrice; // food - discount + service


        boolean isCustomerASeller = false;
        // check customer is seller too
        for (Seller seller1 : sellers) {
            if(seller1.customerId == customer.customerId){
                isCustomerASeller = true;
                totalPriceWithDiscount *= ((100-SELLER_DISCOUNT_PERCENT)/100);

                if(seller1.sellMoreThanTen){
                    totalPriceWithDiscount -= SELLER_DISCOUNT;
                    seller1.sellMoreThanTen = false;
                }

                break;
            }
        }





        if(customer.discountReferrer){
            totalPriceWithDiscount -= CUSTOMER_REFERRER_DISCOUNT;
            customer.discountReferrer = false;
        }


        // if customer is only customer and is not a seller too, then if
        // the customer has bought more than 10 last time, then will use customer discount
        if(customer.discountMoreThanTen && !isCustomerASeller){
            totalPriceWithDiscount -= CUSTOMER_DISCOUNT;
            customer.discountMoreThanTen = false;
        }


        // if customer use service
        if(order.hasService){
            totalPrice += SERVICE_COST;
            totalPriceWithDiscount += SERVICE_COST;
        }

        // if customer use delivery
        if(order.hasDelivery){
            totalPrice += DELIVERY_COST;
            totalPriceWithDiscount += DELIVERY_COST;
        }

        order.totalPrice = totalPrice;
        order.totalPriceWithDiscount = totalPriceWithDiscount;

        orders.add(order);
        customer.totalOrder += count;
        customer.buyCount += count;
        seller.totalSell += count;
        seller.sellCount += count;
        if(customer.buyCount >= 10){
            customer.discountMoreThanTen = true;
            customer.buyCount-=10;
        }
        if(seller.sellCount >= 10){
            seller.sellMoreThanTen = true;
            seller.sellCount -= 10;
        }



        return totalPriceWithDiscount;
    }

    public Customer findCustomerById(int id){
        for (Customer customer : customers) {
            if(customer.customerId==id){
                return customer;
            }
        }
        return null;
    }
    public boolean existsCustomerWithId(int id){
        return (findCustomerById(id) != null);
    }

    public Seller findSellerById(int id){
        for (Seller seller : sellers) {
            if(seller.sellerId == id)
                return seller;
        }
        return null;
    }

    public Seller getBestSeller(){
        double sell = 0;
        Seller bestSeller = null;
        for (Seller seller : sellers) {
            if(seller.totalSell >= sell){
                sell = seller.totalSell;
                bestSeller = seller;
            }
        }
        return bestSeller;
    }

    public Customer getBestCustomer(){
        double buy = 0;
        Customer bestCustomer = null;
        for (Customer customer : customers) {
            if(customer.totalOrder >= buy){
                buy = customer.totalOrder;
                bestCustomer = customer;
            }
        }
        return bestCustomer;
    }

    public Food getPopularFood(){
        int count=0;
        Food popularFood = null;
        for (Food food : foodList) {
            if(food.sellCount >= count){
                count = food.sellCount;
                popularFood = food;
            }
        }
        return  popularFood;
    }
    
    public ArrayList<Order> getOrders(){
        return orders;
    }
    public ArrayList<Food> getFoodList(){
        return foodList;
    }
    public int getTotalSellAmount(){
        return totalSellAmount;
    }
    public double getTotalSell(){
        return  totalSell;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }
}

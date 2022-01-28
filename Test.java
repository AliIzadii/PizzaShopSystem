import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static Scanner scn;
    public static  Fastfood fastfood;
    public static String input;
    public static void main(String[] args) {
       scn = new Scanner(System.in);

       fastfood = new Fastfood(1000,2000,2000,20,2000,2000);
       printMenu();
       boolean x = true;
       while (x){

           input = scn.nextLine();
           int id = Integer.parseInt(input);
           switch (id){
               case 1:
                   newFood();
                   break;
               case 2:
                   addCustomer();
                   break;
               case 3:
                   addSeller();
                   break;
               case 4:
                   printFoodList();
                   break;
               case 5:
                   makeOrder();
                   break;
               case 6:
                   bestSeller();
                   break;
               case 7:
                   bestCustomer();
                   break;
               case 8:
                   printTotalSell();
                   break;
               case 9:
                   printPopularFood();
                   break;
               case 10:
                   printAllSellLists();
                   break;
               case 11:
                   printCustomersList();
                   break;
               case 12:
                   printSellersList();
                   break;
               default:
                   x=false;
           }
       }



    }
    public static void printCustomersList(){
        printLine();
        System.out.println("Customers: ");
        for (Customer customer : fastfood.getCustomers()) {
            System.out.println(String.format("%-20s %s" , customer.name+ " "+customer.family,
                    "customerId: "+customer.customerId ));
        }
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void printSellersList(){
        printLine();
        System.out.println("Sellers: ");
        for (Seller seller : fastfood.getSellers()) {
            System.out.println(String.format("%-20s %s" , seller.name+ " "+seller.family,
                    "sellerId: "+seller.sellerId ));
        }
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void printAllSellLists(){
        printLine();
        System.out.println("All sell list: ");

        ArrayList<Order> orders = fastfood.getOrders();
        for (Order order : orders) {
            Customer c = fastfood.findCustomerById(order.customerId);
            Seller   s = fastfood.findSellerById(order.sellerId);
            System.out.println(String.format("#OrderId: %-16s Customer: %-22s Seller: %s",
                    order.orderId,
                    c.name + " " + c.family,
                    s.name + " " + s.family));
            System.out.println(String.format("\tFood%-16s Price",""));
            for (Food food : order.foodList) {
                System.out.println(String.format("\t%-20s %s", food.getName(),food.getPrice()));
            }
            System.out.println("\tTotal Cost: " + order.totalPrice);
            System.out.println("\tTotal Cost With Discount: " + order.totalPriceWithDiscount);
            System.out.println("+++++++++");
        }
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void printPopularFood(){
        printLine();
        Food food = fastfood.getPopularFood();
        System.out.println("Popular food: " + food.getType());
        System.out.println("Food name: " + food.getName());
        System.out.println("Food total sell: " + food.getSellCount());

        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void printTotalSell(){
        printLine();
        System.out.println("Total sell: ");
        System.out.println("Total sell count: " + fastfood.getTotalSellAmount());
        System.out.println("Total sell value: " + fastfood.getTotalSell());
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void bestCustomer(){
        printLine();
        System.out.println("Best customer: ");
        Customer seller = fastfood.getBestCustomer();
        System.out.println("Customer: " + seller.name + " " + seller.family);
        System.out.println("Buy count: "+ seller.totalOrder);
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void bestSeller(){
        printLine();
        System.out.println("Best seller: ");
        Seller seller = fastfood.getBestSeller();
        System.out.println("Seller: " + seller.name + " " + seller.family);
        System.out.println("Sell count: "+ seller.totalSell);
        System.out.println("");
        writeAnyToBackMenu();
    }
    public static void addSeller(){
        printLine();
        System.out.println("Create new seller: ");
        System.out.print("Enter seller first name: ");
        String name = scn.nextLine();
        System.out.print("Enter seller last name: ");
        String family =  scn.nextLine();
        System.out.print("Enter seller address: ");
        String address = scn.nextLine();
        System.out.print("Enter seller phone: ");
        String phone = scn.nextLine();
        System.out.print("Enter seller shift[1=Noon,2=Night]: ");
        input = scn.nextLine();
        Seller.Shift shift = Seller.Shift.Noon;
        if(input.equals("2"))
            shift =Seller.Shift.Night;
        Seller seller = new Seller(name,family,shift,address,phone);
        int id = seller.sellerId;
        fastfood.addSeller(seller);

        System.out.println("new seller added with id: " + id +" and new customer with the seller info created too");
        System.out.print("add another?[y/n]: ");
        String cmd = scn.nextLine();
        if(cmd.equals("y")){
            addSeller();
        }else{
            printMenu();
        }
    }
    public static void makeOrder(){
        printLine();

        System.out.println("Create new order: ");
        System.out.print("Enter customerId: ");
        input = scn.nextLine();
        int customerId = Integer.parseInt(input);
        System.out.print("Enter sellerId: ");
        input = scn.nextLine();
        int sellerId = Integer.parseInt(input);
        Order order = new Order(customerId, sellerId);
        do {
            System.out.print("Enter food name: ");
            String food = scn.nextLine();
            System.out.print("Enter count: ");
            input = scn.nextLine();
            int count = Integer.parseInt(input);
            order.add(fastfood.getFood(food),count);
            System.out.print("add another food?[y/n]: ");
            input = scn.nextLine();
            if(input.equals("n")){
                break;
            }else{
                System.out.print("");
            }
        }while (true);
        System.out.print("has delivery?[y/n]: ");
        input = scn.nextLine();
        if(input.equals("y")){
            order.hasDelivery = true;
        }else {
            System.out.print("has service?[y/n]: ");
            input = scn.nextLine();
            if (input.equals("y")) {
                order.hasService = true;
            }
        }
        double pay = fastfood.newOrder(order);
        System.out.println("New order saved with orderId "+ order.orderId+ " and total cost to pay is " + pay + "T");
        System.out.print("another order?[y/n]: ");
        input = scn.nextLine();
        if (input.equals("y")) {
            makeOrder();
        }else{
            printMenu();
        }
    }
    public static void printFoodList(){
        printLine();
        System.out.println("Food List: ");
        ArrayList<Food> food = fastfood.getFoodList();
        int i=0;
        for (Food food1 : food) {
            i++;
            System.out.println(String.format("%-20s %s" , food1.getName(), "cost: "+food1.getPrice() ));
        }
        System.out.println("");
        writeAnyToBackMenu();
        printMenu();
    }
    public static void addCustomer(){
        printLine();
        System.out.println("Create new customer: ");
        System.out.print("Enter customer first name: ");
        String name = scn.nextLine();
        System.out.print("Enter customer last name: ");
        String family =  scn.nextLine();
        System.out.print("Enter customer referrer id[enter 0 if there is no referrer]: ");
        input = scn.nextLine();
        int referrerId = Integer.parseInt(input);
        System.out.print("Enter customer address: ");
        String address = scn.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scn.nextLine();
        Customer customer = new Customer(name,family,referrerId,address,phone);
        int id = customer.customerId;
        System.out.println("new customer added with id: " + id);
        fastfood.addCustomer(customer);
        System.out.print("add another?[y/n]: ");
        String cmd = scn.nextLine();
        if(cmd.equals("y")){
            addCustomer();
        }else{
            printMenu();
        }

    }
    public static void newFood(){
        printLine();
        System.out.println("Add new food: ");
        System.out.print("Enter food type[1=pizza|2=sandwich]: ");
        String type = scn.nextLine();
        String foodName="";
        double price;
        if(type.equals("1")){
            System.out.print("Enter pizza name[if pizza is double write 'double' after pizza name. ex 'peperoni double']: ");
            foodName = scn.nextLine();
            System.out.print("Enter pizza price: ");
            input = scn.nextLine();
            price = Double.parseDouble(input);
        }else{
            System.out.print("Enter sandwich name: ");
            foodName = scn.nextLine();
            System.out.print("Enter sandwich price: ");
            input = scn.nextLine();
            price = Double.parseDouble(input);
        }

        if(type.equals("1")){
            Pizza pizza = new Pizza(foodName,price);
            fastfood.addFood(pizza);
        }else{
            Sandwich sandwich = new Sandwich(foodName,price);
            fastfood.addFood(sandwich);
        }

        System.out.print("food added to list. add another?[y/n]: ");
        String cmd = scn.nextLine();
        if(cmd.equals("y")){
            newFood();
        }else{
            printMenu();
        }
    }
    public static void printMenu(){
        printLine();
        System.out.println("FastFood Shop");
        System.out.println("1. add new food");
        System.out.println("2. add new customer");
        System.out.println("3. add new seller");
        System.out.println("4. print food list");
        System.out.println("5. make order");
        System.out.println("6. print best seller");
        System.out.println("7. print best customer");
        System.out.println("8. print total sell");
        System.out.println("9. print popular food");
        System.out.println("10.print all sell list");
        System.out.println("11.print customers list");
        System.out.println("12.print sellers list");
        System.out.println("0 .print sellers list");
        System.out.print("Enter number: ");
    }
    public static void printLine(){
        System.out.println("-----------------------");
    }
    public static void writeAnyToBackMenu(){
        System.out.print("Enter 'm' to back main menu: ");
        scn.nextLine();
        printMenu();
    }
}

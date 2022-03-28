public class Seller extends Person{

    public enum Shift{
        Noon,
        Night
    }
    Shift shift;
    final int sellerId;
    int totalSell;
    int sellCount; // if become more than 10 then sellMoreThanTen will be true
    boolean sellMoreThanTen = false;
    int customerId;
    String address;
    String phone;
    private static int lastSellerId;
    public Seller(String name, String family, Shift shift, String address, String phone){
        lastSellerId++;
        this.sellerId = lastSellerId;
        this.name = name;
        this.family = family;
        this.shift = shift;
        this.phone = phone;
        this.address = address;
    }
}

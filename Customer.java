public class Customer extends Person{
    String address;
    String phone;
    int totalOrder;
    int referrerId;
    boolean discountReferrer = false;
    boolean discountMoreThanTen = false;
    int buyCount; // if become more than 10 discountMoreThanTen will be true
    final int customerId;
    static int lastCustomerId;
    public Customer(String name,String family,int referrerId,String address,String phone){
        lastCustomerId++;
        this.customerId = lastCustomerId;
        this.name = name;
        this.family = family;
        this.referrerId = referrerId;
        this.phone = phone;
        this.address = address;
    }
}

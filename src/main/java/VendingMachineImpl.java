import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    private List<Product> products;
    private Money moneyAmout=new Money(0, 0);
    private String manufacture="BestVM";

    public VendingMachineImpl(){
              products=new ArrayList<Product>();
    }

    @Override
    public String getManufacturer() {
        return manufacture;
    }

    @Override
    public Money getMoneyAmount() {
        return moneyAmout;
    }

    @Override
    public List<Product> getProduct() {
        return products;
    }

    @Override
    public void setProduct(Product product) {
        products.add(product);
    }

    @Override
    public void insertCoins(Money money) {
        if (money.getCents()%5==0){
        moneyAmout.add(money);     }
        else {System.out.println("Please insert correct amount of money");}
    }

    @Override
    public Money returnMoney() {
        Money result=moneyAmout;
        moneyAmout=new Money(0, 0);
        return result;
    }

    public Product getSpecifiedProduct(int productNumber){
        return products.get(productNumber-1);
    }

    public void substructMoney(Money money){
        int currentAmount =moneyAmout.getEuro()*100+moneyAmout.getCents();
        int productPrice = money.getEuro()*100+money.getCents();
        int finalAmount = currentAmount-productPrice;
        moneyAmout =new  Money(finalAmount/100, (finalAmount-(finalAmount/100)*100));
    }

    @Override
    public void buyProduct(int productNumber) {

        try{
        Money productPrice=getProduct().get(productNumber-1).getPrice();
        if (moneyAmout.isBiggerOrEquals(productPrice)) {
            substructMoney(getProduct().get(productNumber - 1).getPrice());
            Product currentProduct = getSpecifiedProduct(productNumber);
            if (currentProduct.getAvailable() > 1) {
                currentProduct.setAvailable(currentProduct.getAvailable() - 1);
            } else {
                getProduct().remove(productNumber - 1);
            }
        } else {
            System.out.println("Not enough money");
        }
    }catch (IndexOutOfBoundsException e){
            System.out.println("Product does not exists");
        }
    }
}

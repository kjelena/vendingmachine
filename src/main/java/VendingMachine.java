import java.util.List;

public interface VendingMachine {

    public String getManufacturer();

    public Money getMoneyAmount();

    public List<Product> getProduct();

    public void setProduct(Product product);

    public void insertCoins(Money money);

    public Money returnMoney();

    public void buyProduct(int productNumber);
}


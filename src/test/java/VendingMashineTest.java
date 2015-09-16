import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VendingMashineTest {
    VendingMachineImpl vendingMachine= new VendingMachineImpl();

    @Before
    public void setUp(){
        vendingMachine.setProduct(new Product("nuts", 1, new Money(0, 50)));
        vendingMachine.setProduct(new Product("orange", 4, new Money(1, 00)));
        vendingMachine.setProduct(new Product("apple", 3, new Money(1, 20)));
        vendingMachine.setProduct(new Product("drink", 2, new Money(2, 00)));
        vendingMachine.setProduct(new Product("sandwich", 5, new Money(3, 00)));
    }

    @Test
    public void shouldBuyProductWithoutChange(){
        Assert.assertEquals(vendingMachine.getProduct().size(), 5);
        vendingMachine.insertCoins(new Money(0, 50));
        vendingMachine.buyProduct(1);
        Assert.assertEquals(vendingMachine.getProduct().size(), 4);
    }


    @Test
    public void shouldBuyProductWithChange(){
        vendingMachine.insertCoins(new Money(0, 60));
        vendingMachine.buyProduct(1);
        Money change=vendingMachine.returnMoney();
        Assert.assertEquals(change.getCents(), new Money(0, 10).getCents());
        Assert.assertEquals(change.getEuro(), new Money(0, 10).getEuro());
    }


    @Test
    public void shouldNotAcceptIncorrectMoney(){
        vendingMachine.insertCoins(new Money(0, 2));
        vendingMachine.insertCoins(new Money(0, 1));
        Money change=vendingMachine.returnMoney();
        Assert.assertEquals(change.getCents(), new Money(0, 0).getCents());
    }


    @Test
    public void shouldNotAllowToBuyUnexistingProduct(){
        Assert.assertEquals(vendingMachine.getProduct().size(), 5);
        vendingMachine.insertCoins(new Money(0, 50));
        vendingMachine.buyProduct(10);
        Assert.assertEquals(vendingMachine.getProduct().size(), 5);
    }

    @Test
    public void shouldCorrectlyAcceptMoney(){
        vendingMachine.insertCoins(new Money(0, 60));
        vendingMachine.insertCoins(new Money(0, 60));
        Money change=vendingMachine.returnMoney();
        Assert.assertEquals(change.getCents(), new Money(1, 20).getCents());
        Assert.assertEquals(change.getEuro(), new Money(1, 20).getEuro());
    }


    @Test
    public void shouldCancelProductPurchase(){
        vendingMachine.insertCoins(new Money(0, 60));
        Money change=vendingMachine.returnMoney();
        Assert.assertEquals(change.getCents(), new Money(0, 60).getCents());
        Assert.assertEquals(change.getEuro(), new Money(0, 60).getEuro());
    }
}

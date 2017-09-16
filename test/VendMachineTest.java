import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendMachineTest {

    VendMachine vendMachine;
    Coin quarter;
    Coin nickel;
    Coin dime;
    Coin penny;

    @Before
    public void setUp() {
        User user = new User();
        vendMachine = new VendMachine();
        quarter = new Coin(0.2, 0.955);
        nickel = new Coin(0.176, 0.835);
        dime = new Coin(0.08, 0.705);
        penny = new Coin(0.088, 0.750);
    }

    @Test
    public void whenDisplayIsCheckedItSaysInsertCoin() {
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
    }

    @Test
    public void whenCoinIsInsertedItDisplaysTwentyFiveCents() {
        assertEquals("$0.25", vendMachine.insertCoin(quarter));
    }

    @Test
    public void whenValidCoinIsInsertedItDisplaysCorrectCoinValue() {
        assertEquals("$0.05", vendMachine.insertCoin(nickel));
    }

    @Test
    public void whenInvalidCoinIsInsertedItRejects() {
        assertEquals("INSERT COIN", vendMachine.insertCoin(penny));
    }

    @Test
    public void whenWeInsertACoinAfterInsertingOtherCoinsItAddsAndDisplaysResult() {
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(dime);
        assertEquals("$0.60", vendMachine.insertCoin(quarter));
    }

    @Test
    public void whenWeBuySomethingAndTheresEnoughMoneyWeDispenseProduct() {
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(quarter);
        assertEquals("THANK YOU", vendMachine.buyProduct(0));
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
    }

    @Test
    public void whenWeBuySomethingAndTheresNotEnoughMoneyItDisplaysThePrice() {
        vendMachine.insertCoin(quarter);
        assertEquals("PRICE: $1.00", vendMachine.buyProduct(0));
        assertEquals("$0.25", vendMachine.checkDisplay());
    }

    @Test
    public void whenWeBuySomethingWeCanBuyASpecificProduct() {
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(quarter);
        assertEquals("THANK YOU", vendMachine.buyProduct(1));
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(quarter);
        vendMachine.insertCoin(nickel);
        vendMachine.insertCoin(dime);
        assertEquals("THANK YOU", vendMachine.buyProduct(2));
    }
}

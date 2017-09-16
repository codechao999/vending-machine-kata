import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendMachineTest {

    VendMachine vendMachine;

    @Before
    public void setUp() {
        vendMachine = new VendMachine();
    }

    @Test
    public void whenDisplayIsCheckedItSaysInsertCoin() {
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
    }

    @Test
    public void whenCoinIsInsertedItDisplaysTwentyFiveCents() {
        Coin quarter = new Coin(0.2, 0.955);
        assertEquals("$0.25", vendMachine.insertCoin(quarter));
    }

    @Test
    public void whenValidCoinIsInsertedItDisplaysCorrectCoinValue() {
        Coin nickel = new Coin(0.176, 0.835);
        Coin dime = new Coin(0.08, 0.705);
        assertEquals("$0.05", vendMachine.insertCoin(nickel));
        assertEquals("$0.10", vendMachine.insertCoin(dime));
    }

    @Test
    public void whenInvalidCoinIsInsertedItRejects() {
        Coin penny = new Coin(0.088, 0.750);
        assertEquals("INSERT COIN", vendMachine.insertCoin(penny));
    }
}

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
}

import org.junit.Test;
import static org.junit.Assert.*;

public class VendMachineTest {
    @Test
    public void whenDisplayIsCheckedItSaysInsertCoin() {
        VendMachine vendMachine = new VendMachine();
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
    }
}

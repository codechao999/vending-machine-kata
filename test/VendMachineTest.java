import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendMachineTest {

    VendMachine vendMachine;
    Coin quarter;
    Coin nickel;
    Coin dime;
    Coin penny;
    User user;

    @Before
    public void setUp() {
        user = new User();
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
        assertEquals("$0.25", vendMachine.insertCoin(quarter, user));
    }

    @Test
    public void whenValidCoinIsInsertedItDisplaysCorrectCoinValue() {
        assertEquals("$0.05", vendMachine.insertCoin(nickel, user));
    }

    @Test
    public void whenInvalidCoinIsInsertedItRejects() {
        assertEquals("INSERT COIN", vendMachine.insertCoin(penny, user));
    }

    @Test
    public void whenWeInsertACoinAfterInsertingOtherCoinsItAddsAndDisplaysResult() {
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(dime, user);
        assertEquals("$0.60", vendMachine.insertCoin(quarter, user));
    }

    @Test
    public void whenWeBuySomethingAndTheresEnoughMoneyWeDispenseProduct() {
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        assertEquals("THANK YOU", vendMachine.buyProduct(0, user));
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
    }

    @Test
    public void whenWeBuySomethingAndTheresNotEnoughMoneyItDisplaysThePrice() {
        vendMachine.insertCoin(quarter, user);
        assertEquals("PRICE: $1.00", vendMachine.buyProduct(0, user));
        assertEquals("$0.25", vendMachine.checkDisplay());
    }

    @Test
    public void whenWeBuySomethingWeCanBuyASpecificProduct() {
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        assertEquals("THANK YOU", vendMachine.buyProduct(1, user));
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(nickel, user);
        vendMachine.insertCoin(dime, user);
        assertEquals("THANK YOU", vendMachine.buyProduct(2, user));
    }

    @Test
    public void whenWeBuySomethingAndWePutInMoreMoneyThanTheCostWeGetCorrectChangeBack(){
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        assertEquals("THANK YOU", vendMachine.buyProduct(2, user));
        assertEquals("You have 0 nickel(s), 1 dime(s), 0 quarter(s), and 0 unusable coin(s).", user.checkMoney());
    }

    @Test
    public void whenWeWantToPushTheReturnCoinButtonOurUnspentMoneyIsReturned() {
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(nickel, user);
        vendMachine.insertCoin(dime, user);
        assertEquals("INSERT COIN", vendMachine.returnCoin(user));
        assertEquals("You have 1 nickel(s), 1 dime(s), 1 quarter(s), and 0 unusable coin(s).", user.checkMoney());
    }

    @Test
    public void whenWeSelectAnItemThatIsOutOfStockDisplaySaysSoldOutAndThenGoesBackToNormal() {
        //we have to make sure we deplete the stock of product 2
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.buyProduct(1, user);
        assertEquals("SOLD OUT", vendMachine.buyProduct(1, user));
        assertEquals("INSERT COIN", vendMachine.checkDisplay());
        vendMachine.insertCoin(quarter, user);
        vendMachine.buyProduct(2, user);
        assertEquals("$0.25", vendMachine.checkDisplay());
    }

    @Test
    public void whenTheMachineCantMakeChangeForAnyOfTheProductsItSellsItDisplaysExactChangeOnly() {
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(dime, user);
        vendMachine.insertCoin(dime, user);
        vendMachine.buyProduct(2, user);
        assertEquals("EXACT CHANGE ONLY", vendMachine.checkDisplay());
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        vendMachine.insertCoin(quarter, user);
        assertEquals("EXACT CHANGE ONLY", vendMachine.buyProduct(2, user));
    }

    @Test
    public void whenWePutInAnInvalidCoinItIsReturnedToOurPocket(){
        vendMachine.insertCoin(penny, user);
        assertEquals("You have 0 nickel(s), 0 dime(s), 0 quarter(s), and 1 unusable coin(s).", user.checkMoney());
    }
}

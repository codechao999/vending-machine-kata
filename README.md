# vending-machine-kata

Vend-O-Tron 2000

Welcome to the Vend-O-Tron 2000 operating manual. 

The Vend-O-Tron 2000 is a java-based digital vending machine operating system, designed to be able to simulate, with minimal modification into
any vending machine, anywhere. Also included are tests for vending machine functions, as well as user-simulation functions, and a basic interactive
console for directly interacting with the simulation. 

RUNNING THE CONSOLE

Running the example console is very easy. Make sure you have the latest version of Java Platform, and run the included .jar file. 

INTERNAL OPERATIONS GUIDE

Although the code is written to be as user-friendly as possible, this section will walk through the code and explain its function.

_________________________________________________________

VendMachine class:

The VendMachine class specifies VendMachine objects, which are the vending machine itself. 

Variables of the VendMachine class:

VendMachine will usually make ideal coins of the denominations it supports, to use in Coin copy constructors whenever it has to make a
new coin of a certain type. 

moneyIn keeps track of the current dollar amount of money deposited so far.

identifier is an object that is used to identify Coin objects based on size and diameter.

menu is an array of MenuItems, products that are currently available for purchase at this machine. The last menu item is always the Coin Return button,
costing nothing and used to trigger a return of all deposited coins.

stock is an integer array defining the current number of items available. The array is the same size as menu as each index of stock corresponds to the
same index in menu. The last value is always MAX_VALUE, as it refers to the Coin Return item.

coinReturnIndex keeps track of what menu index the coin return is located at for easy selection

For each coin supported by the vending machine, a coinStore, a Queue<Coin> LinkedList is made. This is so inserted coins for the change store can be properly sorted.

Constructor:

The constructor takes 3 arguments, all of which are arrays. Stock initializes the stock variable explained above, as does menu. changeStore is an 
integer array which denotes how many coins to initialize each coinStore with, from lowest denomination to highest.

getMenuItem: 

retrieves the string corresponding to the MenuItem in menu at index selection

exactChangeOnly: 

determines if there is a possibility that change would not be able to be given with the current stock. It is determined by checking if change can be made for the
lowest possible change amount (without having to return entered coins), which is 5 cents, and the highest possible change amount, which is 20 cents. Middle change amounts 
are either not needed to be checked, or always doable if we can make the lowest and the highest with current change.

checkDisplay:

As it says, returns what the display currently reads according to the vending machine specification

insertCoin:

Takes the coin to be inserted and the user inserting it. It uses the identifier to identify the coin, and adds the appropriate amount to the moneyIn variable and 
adds the coin to the correct coinStore. If the coin is not supported by the machine, it gives it back to the user and does not change moneyIn. It returns what the display
reads after entry or rejection.

buyProduct:

Takes the menu selection (integer) and the user selecting. It first checks if the stock at the selection index is at least one, or else it is sold out and 
it returns the display as such. Then, if the machine is only taking exact change, and more money is entered than the item costs, it displays EXACT CHANGE ONLY
and does nothing. Then, if exact change is given, we simply return the moneyIn to zero and dispense the item, decrementing the appropriate stock number.

If more money is put in than the item costs, and we can make change (the machine is not in exact change only mode), we subtract the item price from the moneyIn variable
and add coins from our coinStores to the coinReturn arraylist<Coin>, subtracting the coin we return from moneyIn, until moneyIn is zero. We make change from largest denomination to smallest, as that
will assure the least amount of coins are returned for change. At the end, we give the user all the coins in the coin return, decrement the stock, and assure moneyIn is set to zero, since due to double precision we might be off.
Then we return THANK YOU and the method is done. If not enough money is entered to cover the cost of the item, we return the price of the item.

returnCoin:

takes in the user and simply "buys" the coin return item, which costs $0. Therefore it will trigger the buyProduct method to make change, and return the user's money. Due to how the machine works,
the user may end up with different coins than he put in, but will always have the same money returned. 

________________________________________________________

User Class:

The User class represents a simulated user object. The variables the object has is a coinPocket, which is a Queue<Coin> LinkedList for each type of coin the user needs for the vending machine,
as well as a coinPocket for other coins that the vending machine does not take. Pennies and such. The user also has an identifier object to identify any new coins they may be given.

Constructor: 

There is a default constructor and a more specific constructor. The default does not give the user any coins and just initializes the coinPocket constructs.
The other constructor takes in an arraylist of Coins for the user to start with, and also gives them all to the user.

checkMoney:

Returns a string specifying the how many coins of each type the user has, including other coins that can't be used by the vending machine.

giveCoin(Coin):

Takes a Coin, identifies it, and puts it in the right coinPocket.

giveCoin(ArrayList<Coin>):

For each coin in the array, we call giveCoin(Coin) with that coin passed in.

getNum* and take*:

These methods get the number of * coins, and removes/returns a coin from the *pocket, or null if there's none there.

___________________________________________________

MenuItem Class:

This class represents an item for sale in the vending machine. The object variables are a double price and a String for the name of the item.

Constructor: 

Takes a double and a String to initialize the price and name of the item.

Getters:

Return the double price or String item, depending on what getter you call.

_______________________________________________

CoinIdentifier Interface:

An interface for CoinIdentifier classes. Implementing classes must have an identifyCoin function that returns a String of the coin's name, and takes in a Coin object.

Coins are identified by their diameter and weight.

__________________________________________________

USCoinIdentifier Class:

Identifies US Coins supported by the vending machine. Has private static final variables of the ideal weights and diameters of various coins, which are used to do
double comparison to determine what the coin is, based on its weight and diameter. It will return the name of the coin or "reject" if it is not a supported coin.
All coins are compared to a tolerance of .001

_________________________________________________

Coin class:

Represents Coin objects. Has a final double weight and diameter of the coin.

Constructor: 

Initializes the coin's weight and diameter with the passed in doubles, respectively.

Copy Constructor:

Makes a new coin based on the values of a passed in other Coin to copy from.

_________________________________________________

VendMachineForm Class and attached .form:

Swing GUI specifying a simple console for interactivity with a US vending machine selling Cola, Chips and Candy. 

variables are Swing elements or the VendMachine

Swing Elements:

private JTextField welcomeToTheVendTextField:

Just a welcome message.

private JTextField textField1:

A readout of the users money using user.checkMoney.

private JPanel panelMain:

The panel the gui resides on.

private JButton insertQuarterButton:

A button that, if the user has at least one quarter (found using user.getNumQuarters()), calls insertCoin, with the coin being taken from the user's quarterPocket (user.takeQuarter()), and sets the display text field (textField2) to the output of that call.
Then refreshes the user's money display.

private JTextField textField2:

The display of the vending machine


private JButton insertDimeButton;
private JButton insertNickelButton;
private JButton insertOtherButton:

All does what insertQuarterButton does but for the appropriate coin.


private JButton button1;
private JButton button2;
private JButton button3;
private JButton button4:

Set to the 1st, 2nd, 3rd, 4th menu item, pressing the button calls buyProduct for that selection, unless that item is coin return, in which it then calls returnCoin

private JButton refreshDisplayButton:

Refreshes textField2 with the current display, using checkDisplay()

Constructor:

Makes several Coin objects, puts it into an ArrayList and makes a new user with that ArrayList of initial coins.

Also sets button1-4's text with the menu item String corresponding to that item.

Also defines action listeners to give buttons functionality as specified.

_____________________________________________________

Tests:

Also included is the VendMachineTest class. These test the functionality of all the classes other than the form. They are named with what they test, so 
no explanation should be needed.

___________________________________________________________

END DOCUMENTATION





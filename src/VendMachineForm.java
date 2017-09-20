import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class VendMachineForm {
    private JTextField welcomeToTheVendTextField;
    private JTextField textField1;
    private JPanel panelMain;
    private JButton insertQuarterButton;
    private JTextField textField2;
    private JButton insertDimeButton;
    private JButton insertNickelButton;
    private JButton insertOtherButton;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton refreshDisplayButton;

    Coin quarter = new Coin(0.2, 0.955);
    Coin nickel = new Coin(0.176, 0.835);
    Coin dime = new Coin(0.08, 0.705);
    Coin penny = new Coin(0.088, 0.750);



    VendMachine vendMachine = new VendMachine(new Integer[]{3, 3, 3, Integer.MAX_VALUE}, new MenuItem[]{new MenuItem(1.00, "Cola"), new MenuItem(0.50, "Chips"),
            new MenuItem(0.65, "Candy"), new MenuItem(0.00, "Coin Return")}, new Integer[]{5, 5, 5});

    public VendMachineForm() {

        ArrayList<Coin> initialChange = new ArrayList<Coin>();
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(quarter));
        initialChange.add(new Coin(dime));
        initialChange.add(new Coin(dime));
        initialChange.add(new Coin(nickel));
        initialChange.add(new Coin(penny));

        User currentUser = new User(initialChange);


        textField1.setText(currentUser.checkMoney());

        textField2.setText(vendMachine.checkDisplay());

        button1.setText(vendMachine.getMenuItem(0));
        button2.setText(vendMachine.getMenuItem(1));
        button3.setText(vendMachine.getMenuItem(2));
        button4.setText(vendMachine.getMenuItem(3));

        insertQuarterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getNumQuarters() > 0) {
                    textField2.setText(vendMachine.insertCoin(currentUser.takeQuarter(), currentUser));
                }
                textField1.setText(currentUser.checkMoney());
            }
        });
        insertDimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getNumDimes() > 0) {
                    textField2.setText(vendMachine.insertCoin(currentUser.takeDime(), currentUser));
                }
                textField1.setText(currentUser.checkMoney());
            }
        });
        insertNickelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getNumNickels() > 0) {
                    textField2.setText(vendMachine.insertCoin(currentUser.takeNickel(), currentUser));
                }
                textField1.setText(currentUser.checkMoney());
            }
        });
        insertOtherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser.getNumOther() > 0) {
                    textField2.setText(vendMachine.insertCoin(currentUser.takeOther(), currentUser));
                }
                textField1.setText(currentUser.checkMoney());
            }
        });
        refreshDisplayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(currentUser.checkMoney());
                textField2.setText(vendMachine.checkDisplay());
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(button1.getText(), "Coin Return")) {
                    textField2.setText(vendMachine.buyProduct(0, currentUser));
                }

                else {
                    textField2.setText(vendMachine.returnCoin(currentUser));
                }

                textField1.setText(currentUser.checkMoney());
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(button2.getText(), "Coin Return")) {
                    textField2.setText(vendMachine.buyProduct(1, currentUser));
                }

                else {
                    textField2.setText(vendMachine.returnCoin(currentUser));
                }

                textField1.setText(currentUser.checkMoney());
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(button3.getText(), "Coin Return")) {
                    textField2.setText(vendMachine.buyProduct(2, currentUser));
                }

                else {
                    textField2.setText(vendMachine.returnCoin(currentUser));
                }

                textField1.setText(currentUser.checkMoney());
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Objects.equals(button4.getText(), "Coin Return")) {
                    textField2.setText(vendMachine.buyProduct(3, currentUser));
                }

                else {
                    textField2.setText(vendMachine.returnCoin(currentUser));
                }

                textField1.setText(currentUser.checkMoney());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VendMachineForm");
        frame.setContentPane(new VendMachineForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package aufgabe9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.lang.Math;

public class taschenrechner extends JFrame implements KeyListener {

    String s1;
    String s2;
    double o1;
    double o2;
    double res;

    JTextField op1TextField;
    JTextField op2TextField;
    JTextField resTextField;
    JRadioButton degRadioButton;
    JRadioButton radRadioButton;
    JCheckBox brightCheckBox;
    JButton sumButton;
    JButton multButton;
    JButton subButton;
    JButton divButton;
    JButton sinButton;
    JButton cosButton;
    JButton expButton;
    JButton log2Button;
    JButton clearButton;

    @Override
    public void keyTyped(KeyEvent e) {
        if (op1TextField.getForeground() == Color.RED || op2TextField.getForeground() == Color.RED) {
            if (op1TextField.getBackground() == Color.BLACK) {
                op1TextField.setForeground(Color.YELLOW);
            } else {
                op1TextField.setForeground(Color.BLACK);
            }
            if (op2TextField.getBackground() == Color.BLACK) {
                op2TextField.setForeground(Color.YELLOW);
            } else {
                op2TextField.setForeground(Color.BLACK);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        JFrame meinTaschenrechner = new taschenrechner();
    }

    public taschenrechner() {
        setTitle("TextFieldDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel op1Label = new JLabel("Operand x");
        JLabel op2Label = new JLabel("Operand y");
        JLabel resLabel = new JLabel("Result");

        op1TextField = new JTextField("0",10);
        op2TextField = new JTextField("0",10);
        resTextField = new JTextField("0",10);
        resTextField.setEditable(false);

        sumButton = new JButton("+");
        multButton = new JButton("*");
        subButton = new JButton("-");
        divButton = new JButton("/");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        expButton = new JButton("x^y");
        log2Button = new JButton("log2");
        clearButton = new JButton("Clear");
        degRadioButton = new JRadioButton("Deg");
        radRadioButton = new JRadioButton("Rad");
        brightCheckBox = new JCheckBox("Helles Display");


        sumButton.addActionListener(event -> addition());
        multButton.addActionListener(event -> multiplikation());
        subButton.addActionListener(event -> subtraktion());
        divButton.addActionListener(event -> division());
        sinButton.addActionListener(event -> sinus());
        cosButton.addActionListener(event -> cosinus());
        expButton.addActionListener(event -> exponential());
        log2Button.addActionListener(event -> log2());
        clearButton.addActionListener(event -> clear());
        brightCheckBox.addActionListener(event -> helligkeit());
        op1TextField.addKeyListener(this);
        op2TextField.addKeyListener(this);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3,2));
        panel1.add(op1Label);
        panel1.add(op1TextField);
        panel1.add(op2Label);
        panel1.add(op2TextField);
        panel1.add(resLabel);
        panel1.add(resTextField);
        panel1.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel panel2 = new JPanel();
        panel2.add(degRadioButton);
        degRadioButton.setSelected(true);
        panel2.add(radRadioButton);
        panel2.add(brightCheckBox);
        ButtonGroup radiogroup = new ButtonGroup();
        radiogroup.add(degRadioButton);
        radiogroup.add(radRadioButton);
        brightCheckBox.setSelected(true);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2,4));
        panel3.add(sumButton);
        panel3.add(multButton);
        panel3.add(subButton);
        panel3.add(divButton);
        panel3.add(sinButton);
        panel3.add(cosButton);
        panel3.add(expButton);
        panel3.add(log2Button);
        panel3.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(clearButton);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setContentPane(panel);

        pack();
        setResizable(false);
        setVisible(true);
    }

    private void clear() {
        op1TextField.setText("0");
        op2TextField.setText("0");
        resTextField.setText("0");
    }

    private void helligkeit() {
        if (brightCheckBox.isSelected()) {
            op1TextField.setBackground(Color.WHITE);
            op2TextField.setBackground(Color.WHITE);
            resTextField.setBackground(Color.WHITE);
            if (op1TextField.getForeground() != Color.RED) {
                op1TextField.setForeground(Color.BLACK);
            }
            if (op2TextField.getForeground() != Color.RED) {
                op2TextField.setForeground(Color.BLACK);
            }
            resTextField.setForeground(Color.BLACK);
        } else {
            op1TextField.setBackground(Color.BLACK);
            op2TextField.setBackground(Color.BLACK);
            resTextField.setBackground(Color.BLACK);
            if (op1TextField.getForeground() != Color.RED) {
                op1TextField.setForeground(Color.YELLOW);
            }
            if (op2TextField.getForeground() != Color.RED) {
                op2TextField.setForeground(Color.YELLOW);
            }
            resTextField.setForeground(Color.YELLOW);
        }
    }

    private boolean ausrechnen() {
        s1 = op1TextField.getText();
        s2 = op2TextField.getText();
        try {
            o1 = Double.parseDouble(s1);
        }  catch (IllegalArgumentException x){
            op1TextField.setForeground(Color.RED);
            System.err.print("Falsche Eingabe bei den Operanden!\n");
            return false;
        }
        try {
            o2 = Double.parseDouble(s2);
        }  catch (IllegalArgumentException x){
            op2TextField.setForeground(Color.RED);
            System.err.print("Falsche Eingabe bei den Operanden!\n");
            return false;
        }
        return true;
    }

    private void addition() {
        if(ausrechnen()) {
            res = o1 + o2;
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void multiplikation() {
        if (ausrechnen()) {
            res = o1 * o2;
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void subtraktion() {
        if (ausrechnen()) {
            res = o1 - o2;
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void division() {
        if (ausrechnen()) {
            res = o1 / o2;
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void sinus() {
        op2TextField.setText("0");
        if (ausrechnen()) {
            if (degRadioButton.isSelected()) {
                res = Math.sin(Math.toRadians(o1));
            } else {
                res = Math.sin(o1);
            }
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void cosinus() {
        op2TextField.setText("0");
        if (ausrechnen()) {
            if (degRadioButton.isSelected()) {
                res = Math.cos(Math.toRadians(o1));
            } else {
                res = Math.cos(o1);
            }
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void exponential() {
        if (ausrechnen()) {
            res = Math.pow(o1, o2);
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }

    private void log2() {
        op2TextField.setText("0");
        if (ausrechnen()) {
            res = Math.log(o1) / Math.log(2);
            resTextField.setText("" + String.format(Locale.US, "%.6f", res));
        }
    }
}

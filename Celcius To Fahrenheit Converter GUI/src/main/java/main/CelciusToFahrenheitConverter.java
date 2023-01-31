package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CelciusToFahrenheitConverter extends JFrame {
    private JPanel mainPanel;
    private JTextField celciusTextField;
    private JTextField fahrenheitTextField;
    private JLabel celciusLabel;
    private JLabel fahrenheitLabel;
    private JButton converterButton;

    public CelciusToFahrenheitConverter(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tempFahrenheit = (int) ((Double.parseDouble(celciusTextField.getText())) * 1.8 + 32);
                fahrenheitTextField.setText(String.valueOf(tempFahrenheit));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new CelciusToFahrenheitConverter("Celcius to Fahrenheit Converter GUI");
        frame.setVisible(true);
    }
}

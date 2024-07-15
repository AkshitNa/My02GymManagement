package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GymCalculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private JPanel buttonPanel;
    private double firstNumber;
    private String operation;
    private boolean isNewNumber = true;

    public GymCalculator() {
        setTitle("Gym Calculator");
        setSize(420, 500);
        setLocation(1000,45);
        setResizable(false);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 36));
        displayField.setPreferredSize(new Dimension(400, 100)); // Adjusted field size

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 6, 5, 5)); // Adjusted grid layout

        // Add buttons with colors
        addButton("7", Color.LIGHT_GRAY);
        addButton("8", Color.LIGHT_GRAY);
        addButton("9", Color.LIGHT_GRAY);
        addButton("/", Color.GREEN);
        addButton("4", Color.LIGHT_GRAY);
        addButton("5", Color.GRAY);
        addButton("6", Color.LIGHT_GRAY);
        addButton("*", Color.GREEN);
        addButton("1", Color.LIGHT_GRAY);
        addButton("2", Color.LIGHT_GRAY);
        addButton("3", Color.LIGHT_GRAY);
        addButton("-", Color.GREEN);
        addButton("0", Color.DARK_GRAY);
        addButton(".", Color.LIGHT_GRAY);
        addButton("C", Color.DARK_GRAY);
        addButton("+", Color.GREEN);

        // Add an empty panel to occupy the space
        JPanel emptyPanel = new JPanel();
        buttonPanel.add(emptyPanel);

        // Add the "=" button
        JButton equalButton = new JButton("=");
        equalButton.setFont(new Font("Arial", Font.PLAIN, 36));
        equalButton.setBackground(Color.ORANGE); // Set background color
        equalButton.addActionListener(this);
        buttonPanel.add(equalButton);

        // Add the close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 20));
        closeButton.setBackground(Color.RED); // Set background color
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });
        buttonPanel.add(closeButton);

        panel.add(displayField, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    private void addButton(String label, Color color) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 36));
        button.setBackground(color); // Set background color
        button.addActionListener(this);
        buttonPanel.add(button);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (isNewNumber) {
            displayField.setText("");
            isNewNumber = false;
        }
        if (Character.isDigit(command.charAt(0))) {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals(".")) {
            if (!displayField.getText().contains(".")) {
                displayField.setText(displayField.getText() + ".");
            }
        } else if (command.equals("C")) {
            displayField.setText("");
            isNewNumber = true;
        } else if (command.equals("=")) {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = 0;
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        result = Double.NaN; // Handle division by zero
                    }
                    break;
            }
            displayField.setText(String.valueOf(result));
            isNewNumber = true;
        } else {
            // Check if the display field is empty or ends with a digit before adding the operator
            String currentText = displayField.getText();
            if (!currentText.isEmpty() && Character.isDigit(currentText.charAt(currentText.length() - 1))) {
                operation = command;
                displayField.setText(displayField.getText() + " " + operation + " ");
                firstNumber = Double.parseDouble(displayField.getText().split(" ")[0]);
                isNewNumber = true;
            } else if (command.equals(".") || command.equals("-")) {
                displayField.setText(displayField.getText() + command);
            }
        }
    }


    public static void main(String[] args) {
        new GymCalculator();
    }
}


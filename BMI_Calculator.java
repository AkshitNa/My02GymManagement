package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BMI_Calculator extends JFrame implements ActionListener {
    private JLabel l1, l2, l3, l4, l5, l6;
    private JButton bt1;
    private JTextField tf1, tf2, tf3, tf4;
    private Font f1, f2, f3;
    private JPanel p1, p2, p3, p4, p5;

    public BMI_Calculator() {
        super("BMI Calculator");
        setSize(890, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        f1 = new Font("Lucida Fax", Font.BOLD, 25);
        f2 = new Font("MS UI Gothic", Font.BOLD, 18);
        f3 = new Font("Gadugi", Font.BOLD, 12);

        // Setting Icon on FRAME
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/BMI001.png"));
        Image image1 = img1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        l1 = new JLabel(new ImageIcon(image1));

        l2 = new JLabel("BMI Calculator");
        l2.setHorizontalAlignment(JLabel.CENTER);
        l3 = new JLabel("Enter Your Weight (in Kg)");
        l4 = new JLabel("Enter Height (in cm)");
        l5 = new JLabel("Enter Age (in years)");

        bt1 = new JButton("Calculate BMI");

        // Set button background color using a gradient
        bt1.setBackground(new Color(0, 102, 204));

        // Set button foreground (text) color
        bt1.setForeground(Color.WHITE);

        // Set button border
        bt1.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));

        bt1.addActionListener(this);

        l6 = new JLabel("Result will appear here");

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();

        l2.setFont(f1);
        l3.setFont(f2);
        l4.setFont(f2);
        l5.setFont(f2);
        l6.setFont(f2);

        tf1.setFont(f2);
        tf2.setFont(f2);
        tf3.setFont(f2);
        tf4.setFont(f2);

        bt1.setFont(f2);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 2, 10, 10));

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));

        p2.add(l1);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(5, 2, 10, 10));

        p3.add(l3);
        p3.add(tf1);
        p3.add(l4);
        p3.add(tf2);
        p3.add(l5);
        p3.add(tf3);
        p3.add(new JLabel());
        p3.add(bt1);
        p3.add(l6);
        p3.add(tf4);

        p1.add(p2);
        p1.add(p3);

        setLayout(new BorderLayout(0, 0));

        l2.setForeground(Color.YELLOW);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);
        l6.setForeground(Color.WHITE);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p1, "Center");

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields to calculate BMI.", "Incomplete Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double weight = Double.parseDouble(tf1.getText()); // weight in kg
        double height = Double.parseDouble(tf2.getText()); // height in cm
        int age = Integer.parseInt(tf3.getText()); // age in years

        double bmi = weight / ((height / 100) * (height / 100));
        tf4.setText(String.format("%.2f", bmi));

        // Display BMI range message
        if (bmi < 18.5) {
            JOptionPane.showMessageDialog(this, "You are underweight.", "BMI Range", JOptionPane.INFORMATION_MESSAGE);
        } else if (bmi >= 18.5 && bmi < 25) {
            JOptionPane.showMessageDialog(this, "You have a normal weight.", "BMI Range", JOptionPane.INFORMATION_MESSAGE);
        } else if (bmi >= 25 && bmi < 30) {
            JOptionPane.showMessageDialog(this, "You are overweight.", "BMI Range", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "You are obese.", "BMI Range", JOptionPane.INFORMATION_MESSAGE);
        }

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public static void main(String args[]) {
        new BMI_Calculator().setVisible(true);
    }
}

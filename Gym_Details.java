package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gym_Details extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    JTextField tf1, tf2;
    JButton bt1, bt2;
    Font f1, f2;
    JPanel p1, p2, p3;

    Gym_Details() {
        super("Gym Details");
        setSize(950, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        f1 = new Font("Lucida Fax", Font.BOLD, 40);
        f2 = new Font("MS UI Gothic", Font.BOLD, 18);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/home2.jpg"));
        Image imag = img.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT);
        l1 = new JLabel(new ImageIcon(imag));

        l2 = new JLabel("CynoGym");
        l3 = new JLabel("Owner Name : ");
        l4 = new JLabel("Address : ");
        l5 = new JLabel("Akshit Nautiyal");
        l6 = new JLabel("Saurab Garden, Dehradun");
        bt1 = new JButton("See All Faculties");
        bt2 = new JButton("Cancel");

        // Set custom appearance for buttons and text fields
        bt1.setBackground(new Color(30, 144, 255)); // Dodger Blue
        bt1.setForeground(Color.WHITE);
        bt1.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));
        bt1.setFont(new Font("MS UI Gothic", Font.BOLD, 22)); // Increase font size
        bt1.setMargin(new Insets(10, 20, 10, 20)); // Increase button height

        bt2.setBackground(new Color(255, 69, 0)); // Red-Orange
        bt2.setForeground(Color.WHITE);
        bt2.setBorder(BorderFactory.createLineBorder(new Color(255, 69, 0), 2));
        bt2.setFont(new Font("MS UI Gothic", Font.BOLD, 22)); // Increase font size
        bt2.setMargin(new Insets(10, 20, 10, 20)); // Increase button height

        // Set custom appearance for labels
        l2.setFont(new Font("Arial", Font.BOLD, 50)); // Larger font
        l2.setForeground(new Color(255, 215, 0)); // Gold
        l3.setFont(f2);
        l4.setFont(f2);
        l5.setFont(f2);
        l6.setFont(f2);

        // Add ActionListener
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        l2.setHorizontalAlignment(JLabel.CENTER);
        l3.setHorizontalAlignment(JLabel.CENTER);
        l4.setHorizontalAlignment(JLabel.CENTER);

        // Set foreground colors for remaining labels
        l3.setForeground(Color.WHITE); // White
        l4.setForeground(Color.WHITE); // White
        l5.setForeground(Color.WHITE); // White
        l6.setForeground(Color.WHITE); // White

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        p1.setLayout(new GridLayout(3, 2, 10, 10));
        p1.add(l3);
        p1.add(l5);
        p1.add(l4);
        p1.add(l6);
        p1.add(bt1);
        p1.add(bt2);

        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l2);

        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l1);

        // Set background colors
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        // Add components to frame
        setLayout(new BorderLayout(0, 0));
        add(p2, "North");
        add(p3, "Center");
        add(p1, "South");

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            new Gym_Gadgets().setVisible(true);
            this.setVisible(false);
        }
        if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                dispose(); // Terminate the program
            }
        }
    }

    public static void main(String[] args) {
        new Gym_Details().setVisible(true);
    }
}

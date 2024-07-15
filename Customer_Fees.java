package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer_Fees extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10; // Added l10 for "Fee Paid"
    JButton bt1, bt2, bt3; // Added bt3 for the new button
    Choice ch1, ch2, ch3; // Added ch3 for drop-down list of "Fee Paid"
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JPanel p1, p2, p3, buttonPanel; // Added buttonPanel

    Customer_Fees() {
        super("Customer Fees");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        Font f = new Font("MS UI Gothic", Font.BOLD, 18);
        Font f1 = new Font("Lucida Fax", Font.BOLD, 25);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/17.png"));
        Image im=img.getImage().getScaledInstance(100,200,Image.SCALE_DEFAULT);
        l6=new JLabel(new ImageIcon(im));

        l1 = new JLabel("    Receipt ID");
        l2 = new JLabel("    Name");
        l3 = new JLabel("    Category");
        l7 = new JLabel("    Month");
        l4 = new JLabel("    Charges Per Month");
        l5 = new JLabel("    Customer Fees");
        l9 = new JLabel("    Trainer Name");
        l10 = new JLabel("    Fee Paid"); // Added label for "Fee Paid"
        l5.setFont(f1);
        l5.setHorizontalAlignment(JLabel.CENTER);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf6 = new JTextField();

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l7.setFont(f);
        l9.setFont(f);
        l10.setFont(f); // Set font for "Fee Paid"

        tf1.setFont(f);
        tf2.setFont(f);
        tf3.setFont(f);
        tf4.setFont(f);
        tf6.setFont(f);

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        l9.setForeground(Color.WHITE);
        l10.setForeground(Color.WHITE); // Set color for "Fee Paid"
        l5.setForeground(Color.YELLOW);

        ch1 = new Choice();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select receiptNo from add_customer";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("ReceiptNo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ch1.setFont(f);

        ch2 = new Choice();
        ch2.add("January");
        ch2.add("February");
        ch2.add("March");
        ch2.add("April");
        ch2.add("May");
        ch2.add("June");
        ch2.add("July");
        ch2.add("August");
        ch2.add("September");
        ch2.add("October");
        ch2.add("November");
        ch2.add("December");

        ch2.setFont(f);

        ch3 = new Choice(); // Drop-down list for "Fee Paid"
        ch3.add("Paid");
        ch3.add("Unpaid");

        ch3.setFont(f);

        bt1 = new JButton("Submit");
        bt2 = new JButton("Cancel");
        bt3 = new JButton("Update"); // New button for updating

        bt1.setFont(f);
        bt2.setFont(f);
        bt3.setFont(f);

        // Improve button appearance
        bt1.setBackground(new Color(0, 102, 204)); // Dark Blue
        bt1.setForeground(Color.WHITE);
        bt1.setFocusPainted(false); // Remove focus border
        bt1.setBorderPainted(false); // Remove border
        bt1.setOpaque(true); // Ensure background color is visible

        bt2.setBackground(new Color(204, 0, 0)); // Dark Red
        bt2.setForeground(Color.WHITE);
        bt2.setFocusPainted(false);
        bt2.setBorderPainted(false);
        bt2.setOpaque(true);

        bt3.setBackground(new Color(0, 153, 51)); // Dark Green
        bt3.setForeground(Color.WHITE);
        bt3.setFocusPainted(false);
        bt3.setBorderPainted(false);
        bt3.setOpaque(true);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this); // Add action listener for new button

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l5);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(8, 2, 10, 10)); // Increased rows for "Fee Paid"
        p2.add(l1);
        p2.add(ch1);
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l9);
        p2.add(tf6);
        p2.add(l7);
        p2.add(ch2);
        p2.add(l10); // Added label for "Fee Paid"
        p2.add(ch3); // Added drop-down list for "Fee Paid"
        p2.add(l4);
        p2.add(tf3);

        buttonPanel = new JPanel(); // New panel for buttons
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10)); // Use GridLayout with 1 row and 3 columns
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        buttonPanel.add(bt1);
        buttonPanel.add(bt2);
        buttonPanel.add(bt3);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l6);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        setLayout(new BorderLayout(0, 0));

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg) {
                int id = Integer.parseInt(ch1.getSelectedItem());
                try {
                    ConnectionClass obj1 = new ConnectionClass();
                    String q1 = "select * from add_customer where receiptNo='" + id + "'";
                    ResultSet rest1 = obj1.stm.executeQuery(q1);
                    while (rest1.next()) {
                        tf1.setText(rest1.getString("name"));
                        tf2.setText(rest1.getString("category"));
                        tf6.setText(rest1.getString("trainerName"));
                    }
                    String tname = tf6.getText();
                    ConnectionClass obj2 = new ConnectionClass();
                    String q2 = "select fees from fee_trainer where name='" + tname + "'";
                    ResultSet rest = obj2.stm.executeQuery(q2);
                    while (rest.next()) {
                        tf3.setText(rest.getString("Fees"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bt1) {
                String id = ch1.getSelectedItem();
                String name = tf1.getText();
                String category = tf2.getText();
                String feesText = tf3.getText();
                String trainer_name = tf6.getText();
                String month = ch2.getSelectedItem();
                String feePaid = ch3.getSelectedItem(); // Get selected value for "Fee Paid"

                // Format the current date in 'yyyy-MM-dd HH:mm:ss' format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());

                // Check if any of the fields are empty
                if (name.isEmpty() || category.isEmpty() || feesText.isEmpty() || trainer_name.isEmpty() || month.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                } else {
                    float fees = Float.parseFloat(feesText);

                    ConnectionClass obj2 = new ConnectionClass();
                    String q2 = "insert into pay_customer values('" + id + "','" + name + "','" + category + "','" + fees + "','" + month + "','" + feePaid + "', '" + date + "')";
                    obj2.stm.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Your Fees Successfully Submitted :)");
                }
            }

            if (e.getSource() == bt3) { // Check if the "Update" button is clicked
                String id = ch1.getSelectedItem();
                String name = tf1.getText();
                String category = tf2.getText();
                String feesText = tf3.getText();
                String trainer_name = tf6.getText();
                String month = ch2.getSelectedItem();
                String feePaid = ch3.getSelectedItem(); // Get selected value for "Fee Paid"

                // Format the current date in 'yyyy-MM-dd HH:mm:ss' format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());

                // Check if any of the fields are empty
                if (name.isEmpty() || category.isEmpty() || feesText.isEmpty() || trainer_name.isEmpty() || month.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                } else {
                    float fees = Float.parseFloat(feesText);

                    ConnectionClass obj2 = new ConnectionClass();
                    // Modify the SQL query to update the record instead of inserting
                    String q2 = "update pay_customer set name='" + name + "', category='" + category + "', fees='" + fees + "', month='" + month + "', status='" + feePaid + "', currentDate='" + date + "' where receiptNo='" + id + "'";
                    obj2.stm.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Record Updated Successfully :)");
                }
            }

            if (e.getSource() == bt2) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    dispose(); // Terminate the program
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new Customer_Fees().setVisible(true);
    }
}
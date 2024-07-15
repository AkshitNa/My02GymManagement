package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer_Attendance extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5;
    Choice ch1, ch2, ch3;
    Font f1, f2;
    JButton bt1, bt2;
    JPanel p1, p2;

    public Customer_Attendance() {
        super("Customer Attendance");
        setSize(400, 250);
        setLocation(120,45);
        setResizable(false);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        f1 = new Font("Lucida Fax", Font.BOLD, 25);
        f2 = new Font("MS UI Gothic", Font.BOLD, 18);

        l1 = new JLabel("Receipt No.");
        l2 = new JLabel("Select Time");
        l3 = new JLabel("Take Attendance");
        l4 = new JLabel("Customer Attendance");

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.YELLOW);

        l4.setFont(f1);
        l4.setHorizontalAlignment(JLabel.CENTER);

        ch1 = new Choice();
        ch1.add("Morning Time");
        ch1.add("Evening Time");

        ch2 = new Choice();
        ch2.add("Absent");
        ch2.add("Present");

        ch3 = new Choice();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "Select receiptNo from add_Customer";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch3.add(rest.getString("receiptNo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        bt1 = new JButton("Submit");
        bt2 = new JButton("Cancel");
        bt1.addActionListener(this);
        bt2.addActionListener(this);

        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.RED);

        l1.setFont(f2);
        l2.setFont(f2);
        l3.setFont(f2);
        ch3.setFont(f2);
        ch1.setFont(f2);
        ch2.setFont(f2);
        bt1.setFont(f2);
        bt2.setFont(f2);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2, 10, 10));
        p1.setBackground(Color.BLACK);
        p1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        p1.add(l1);
        p1.add(ch3);
        p1.add(l2);
        p1.add(ch1);
        p1.add(l3);
        p1.add(ch2);
        p1.add(bt1);
        p1.add(bt2);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.setBackground(Color.BLACK);
        p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        p2.add(l4);

        setLayout(new BorderLayout(0, 0));

        add(p2, BorderLayout.NORTH);
        add(p1, BorderLayout.CENTER);

        getContentPane().setBackground(Color.DARK_GRAY); // Set background color

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


    public void actionPerformed(ActionEvent e) {

        String receiptNo = ch3.getSelectedItem();
        String select_time = ch1.getSelectedItem();
        String take_attn = ch2.getSelectedItem();

        // Format the current date in 'yyyy-MM-dd HH:mm:ss' format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        if (e.getSource() == bt1) {
            // Check if any of the text fields are empty
            if (receiptNo == null || select_time == null || take_attn == null) {
                JOptionPane.showMessageDialog(null, "Fill all the fields first");
            } else {
                try {
                    ConnectionClass obj1 = new ConnectionClass();
                    String q1 = "insert into customer_attendance values('" + receiptNo + "','" + select_time + "','"
                            + take_attn + "','" + date + "')";
                    obj1.stm.executeUpdate(q1);
                    JOptionPane.showMessageDialog(null, "Data Inserted Successfully!!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                dispose(); // Terminate the program
            }
        }
    }


    public static void main(String args[]) {
        new Customer_Attendance().setVisible(true);
    }
}

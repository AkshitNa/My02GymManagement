package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trainer_Salary extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    JButton bt1, bt2, bt3;
    Choice ch1, ch2, ch3;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    JPanel p1, p2, p3, buttonPanel;

    Trainer_Salary() {
        super("Trainer Salary");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        Font f = new Font("MS UI Gothic", Font.BOLD, 18);
        Font f1 = new Font("Lucida Fax", Font.BOLD, 25);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/17.png"));
        Image im = img.getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT);
        l6 = new JLabel(new ImageIcon(im));

        l1 = new JLabel("    Trainer ID");
        l2 = new JLabel("    Name");
        l3 = new JLabel("    Category");
        l7 = new JLabel("    Month");
        l8 = new JLabel("    Salary To Be Paid");
        l4 = new JLabel("    Charges Per Client");
        l5 = new JLabel("    Trainer Salary");
        l9 = new JLabel("    No. of Customers Trains");
        l10 = new JLabel("    Salary Status");
        l5.setFont(f1);
        l5.setHorizontalAlignment(JLabel.CENTER);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf5 = new JTextField();
        tf6 = new JTextField();

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l7.setFont(f);
        l8.setFont(f);
        l9.setFont(f);
        l10.setFont(f);

        tf1.setFont(f);
        tf2.setFont(f);
        tf3.setFont(f);
        tf4.setFont(f);
        tf5.setFont(f);
        tf6.setFont(f);

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        l8.setForeground(Color.WHITE);
        l9.setForeground(Color.WHITE);
        l10.setForeground(Color.WHITE);
        l5.setForeground(Color.YELLOW);

        ch1 = new Choice();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select tid from add_trainer";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("tid"));
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

        ch3 = new Choice();
        ch3.add("Paid");
        ch3.add("Unpaid");

        ch3.setFont(f);

        bt1 = new JButton("Submit");
        bt2 = new JButton("Update");
        bt3 = new JButton("Cancel");

        bt1.setFont(f);
        bt2.setFont(f);
        bt3.setFont(f);

        bt1.setBackground(new Color(0, 102, 204));
        bt1.setForeground(Color.WHITE);
        bt1.setFocusPainted(false);
        bt1.setBorderPainted(false);
        bt1.setOpaque(true);

        bt2.setBackground(new Color(0, 153, 51));
        bt2.setForeground(Color.WHITE);
        bt2.setFocusPainted(false);
        bt2.setBorderPainted(false);
        bt2.setOpaque(true);

        bt3.setBackground(new Color(204, 0, 0));
        bt3.setForeground(Color.WHITE);
        bt3.setFocusPainted(false);
        bt3.setBorderPainted(false);
        bt3.setOpaque(true);

        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l5);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(8, 2, 10, 10));
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
        p2.add(l8);
        p2.add(tf5);
        p2.add(l4);
        p2.add(tf3);
        p2.add(l10);
        p2.add(ch3);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
        add(buttonPanel, BorderLayout.SOUTH);

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
                int tid = Integer.parseInt(ch1.getSelectedItem());
                int numCustomers = 0;

                try {
                    ConnectionClass obj1 = new ConnectionClass();
                    String q1 = "SELECT * FROM add_trainer WHERE tid='" + tid + "'";
                    ResultSet rest1 = obj1.stm.executeQuery(q1);
                    if (rest1.next()) {
                        tf1.setText(rest1.getString("name"));
                        tf2.setText(rest1.getString("category"));
                    }

                    String q2 = "SELECT fees FROM fee_trainer WHERE tid='" + tid + "'";
                    ResultSet rest2 = obj1.stm.executeQuery(q2);
                    if (rest2.next()) {
                        float fees = rest2.getFloat("fees");
                        tf3.setText(String.valueOf(fees));
                    } else {
                        tf3.setText("");
                    }

                    String trainerName = tf1.getText();
                    String q3 = "SELECT COUNT(*) AS num_customers FROM add_customer WHERE trainerName='" + trainerName + "'";
                    ResultSet rest3 = obj1.stm.executeQuery(q3);
                    if (rest3.next()) {
                        numCustomers = rest3.getInt("num_customers");
                        tf6.setText(String.valueOf(numCustomers));
                    }

                    String chargesText = tf3.getText();
                    float chargesPerClient = 0;
                    if (!chargesText.isEmpty()) {
                        chargesPerClient = Float.parseFloat(chargesText);
                    }

                    float salaryToBePaid;
                    String category = tf2.getText();
                    if (category.equalsIgnoreCase("personal")) {
                        salaryToBePaid = 0.75f * numCustomers * chargesPerClient;
                    } else if (category.equalsIgnoreCase("public")) {
                        salaryToBePaid = 0.70f * numCustomers * chargesPerClient;
                    } else {
                        // Handle other categories if needed
                        salaryToBePaid = 0; // Default value
                    }

// Round the salaryToBePaid to three decimal places
                    String formattedSalary = String.format("%.2f", salaryToBePaid);

                    tf5.setText(formattedSalary);
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
                String feeStatus = ch3.getSelectedItem();

                // Format the current date in 'yyyy-MM-dd HH:mm:ss' format
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());

                if (name.isEmpty() || category.isEmpty() || feesText.isEmpty() || trainer_name.isEmpty() || month.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields.");
                } else {
                    float fees = Float.parseFloat(feesText);
                    int numCustomers = Integer.parseInt(tf6.getText()); // Retrieve number of customers

                    // Calculate salary to be paid based on category and number of customers
                    float salaryToBePaid;
                    if (category.equalsIgnoreCase("personal")) {
                        salaryToBePaid = 0.65f * numCustomers * fees;
                    } else if (category.equalsIgnoreCase("public")) {
                        salaryToBePaid = 0.55f * numCustomers * fees;
                    } else {
                        salaryToBePaid = 0; // Default value
                    }

                // Calculate total profit
                    float tcharge = Float.parseFloat(tf3.getText());
                    float profitFromEachTrainer = (tcharge * numCustomers) - salaryToBePaid;
                    System.out.println("Total profit: " + profitFromEachTrainer);


                    // Insert the data into the table
                    ConnectionClass obj2 = new ConnectionClass();
                    String q2 = "INSERT INTO trainer_salary (tid, name, category, NoCustomers, month, tcharge, tsalary, status, profit, currentDate) VALUES ('" + id + "','" + name + "','" + category + "','" + numCustomers + "','" + month + "','" + feesText + "','" + salaryToBePaid + "','" + feeStatus + "','" + profitFromEachTrainer + "','" + date + "')";
                    obj2.stm.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Trainer salary details inserted successfully.");
                }
            }


            if (e.getSource() == bt2) { // If "Update" button is clicked
                String id = ch1.getSelectedItem(); // Get the selected Trainer ID
                ConnectionClass obj = new ConnectionClass();
                try {
                    // Format the current date in 'yyyy-MM-dd HH:mm:ss' format
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String date = sdf.format(new Date());

                    // Update the data in the table
                    String query = "UPDATE trainer_salary SET category=?, NoCustomers=?, tsalary=?, month=?, status=?, profit=?, currentDate=? WHERE tid=?";
                    PreparedStatement pstmt = obj.con.prepareStatement(query);
                    pstmt.setString(1, tf2.getText()); // category
                    pstmt.setInt(2, Integer.parseInt(tf6.getText())); // NoCustomers
                    pstmt.setFloat(3, Float.parseFloat(tf5.getText())); // tsalary
                    pstmt.setString(4, ch2.getSelectedItem()); // month
                    pstmt.setString(5, ch3.getSelectedItem()); // status
                    pstmt.setFloat(6, Float.parseFloat(tf5.getText())); // revenue
                    pstmt.setString(7, date); // currentDate
                    pstmt.setString(8, id); // tid
                    int rowsUpdated = pstmt.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Trainer salary details updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Trainer salary details not found or no changes made.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            if (e.getSource() == bt3) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String args[]) {
        new Trainer_Salary().setVisible(true);
    }
}
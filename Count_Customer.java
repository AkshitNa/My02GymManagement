package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Count_Customer extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JButton bt1, bt2, printButton;
    JTextField tf1;
    Choice ch1;
    JPanel p1, p2, p3;
    Font f1, f2;
    JTable table;
    DefaultTableModel model;

    Count_Customer() {
        super("Check Trainer Customers ");
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Setting Icon on FRAME
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        f1 = new Font("Lucida Fax", Font.BOLD, 26);
        f2 = new Font("MS UI Gothic", Font.BOLD, 18);

        l1 = new JLabel("Customers Under A Trainer");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l2 = new JLabel("  Trainer Id      ");
        l3 = new JLabel("     Trainer Name    ");

        tf1 = new JTextField(15);
        tf1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Set border color and thickness

        ch1 = new Choice();

        Font choiceFont = new Font("MS UI Gothic", Font.BOLD, 16);
        ch1.setFont(choiceFont);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        try {
            ConnectionClass obj1 = new ConnectionClass();
            String q1 = "select tid from add_trainer";
            ResultSet rest1 = obj1.stm.executeQuery(q1);
            while (rest1.next()) {
                ch1.add(rest1.getString("tid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Dimension choiceSize = new Dimension(150, 30);
        ch1.setPreferredSize(choiceSize);

        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int id = Integer.parseInt(ch1.getSelectedItem());
                try {
                    ConnectionClass obj2 = new ConnectionClass();
                    String q2 = "select name from add_trainer where tid='" + id + "'";
                    ResultSet rest2 = obj2.stm.executeQuery(q2);
                    while (rest2.next()) {
                        tf1.setText(rest2.getString("name"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });

        bt1 = new JButton("Check");
        bt1.setForeground(Color.RED);
        bt1.setBackground(Color.WHITE); // Set background color
        bt1.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Set border color and thickness
        bt1.setOpaque(true); // Make button opaque to show background color
        bt1.addActionListener(this);

        printButton = new JButton("Print");
        printButton.setForeground(Color.BLUE);
        printButton.setBackground(Color.WHITE); // Set background color
        printButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // Set border color and thickness
        printButton.setOpaque(true); // Make button opaque to show background color
        printButton.addActionListener(this);

        bt2 = new JButton("Cancel");
        bt2.setForeground(Color.GREEN);
        bt2.setBackground(Color.WHITE); // Set background color
        bt2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Set border color and thickness
        bt2.setOpaque(true); // Make button opaque to show background color
        bt2.addActionListener(this);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Color buttonTextColor = new Color(0, 0, 0);

        bt1.setFont(buttonFont);
        bt2.setFont(buttonFont);
        printButton.setFont(buttonFont);

        bt1.setForeground(buttonTextColor);
        bt2.setForeground(buttonTextColor);
        printButton.setForeground(buttonTextColor);

        bt1.setMargin(new Insets(5, 10, 5, 10));
        bt2.setMargin(new Insets(5, 10, 5, 10));
        printButton.setMargin(new Insets(5, 10, 5, 10));

        Dimension buttonSize = new Dimension(100, 30);
        bt1.setPreferredSize(buttonSize);
        bt2.setPreferredSize(buttonSize);
        printButton.setPreferredSize(buttonSize);

        l1.setFont(f1);
        l2.setFont(f2);
        l3.setFont(f2);
        tf1.setFont(f2);
        ch1.setFont(f2);

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();

        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        p2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        p2.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        p2.add(ch1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        p2.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        p2.add(tf1, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center-aligned with spacing
        buttonPanel.setBackground(Color.BLACK);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        buttonPanel.add(bt1);
        buttonPanel.add(printButton);
        buttonPanel.add(bt2);
        p2.add(buttonPanel, gbc);

        l1.setForeground(Color.YELLOW);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);
        setLayout(new BorderLayout(0, 0));

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.WEST);

        String[] columnNames = {"Receipt No", "Name", "Email", "Address", "Contact No", "Payment Plan", "Gender", "Height", "Weight", "Remarks", "Trainer Name", "Category"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 200));
        add(scrollPane, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            int trainerId = Integer.parseInt(ch1.getSelectedItem());
            String trainerName = tf1.getText();
            fetchCustomerDetails(trainerId, trainerName);
        }

        if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(this, "Are you Sure?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                dispose();
            }
        }

        if (e.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void fetchCustomerDetails(int trainerId, String trainerName) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM add_customer WHERE trainerName = ? OR receiptNo = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, trainerName);
            pstmt.setInt(2, trainerId);
            ResultSet rs = pstmt.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("receiptNo"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("contactNo"),
                        rs.getString("paymentPlan"),
                        rs.getString("gender"),
                        rs.getFloat("height"),
                        rs.getFloat("weight"),
                        rs.getString("remarks"),
                        rs.getString("trainerName"),
                        rs.getString("category")
                };
                model.addRow(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch customer details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new Count_Customer().setVisible(true);
    }
}

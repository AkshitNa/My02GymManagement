package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Customer_Information extends JFrame implements ActionListener
{
    String[] x={"Receipt Id","Name","Father's Name","Email","Address",
            "Contact No.","Payment Plan","Gender","Height",
            "Weight","Remarks","Trainer Name","Category"};
    JButton bt;
    String y[][]=new String[20][14];
    int i=0,j=0;
    JTable t;
    JLabel l1,l2;
    JTextField tf1;
    JPanel p1,p2,p3;
    Font f,f1;
    Customer_Information()
    {
        super("Customer Information");
        setSize(1500,600);
        setLocationRelativeTo(null);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        f=new Font("MS UI Gothic",Font.BOLD,15);
        try
        {
            ConnectionClass obj=new ConnectionClass();
            String q="Select * from add_Customer";
            ResultSet rest=obj.stm.executeQuery(q);
            while(rest.next())
            {
                y[i][j++]=rest.getString("receiptNo");
                y[i][j++]=rest.getString("name");
                y[i][j++]=rest.getString("fname");
                y[i][j++]=rest.getString("email");
                y[i][j++]=rest.getString("address");
                y[i][j++]=rest.getString("contactNo");
                y[i][j++]=rest.getString("paymentPlan");
                y[i][j++]=rest.getString("gender");
                y[i][j++]=rest.getString("height");
                y[i][j++]=rest.getString("weight");
                y[i][j++]=rest.getString("remarks");
                y[i][j++]=rest.getString("trainerName");
                y[i][j++]=rest.getString("category");
                i++;
                j=0;
            }
            t=new JTable(y,x);
            t.setFont(new Font("MS UI Gothic",Font.PLAIN,18)); // Adjusted font size
            t.setBackground(Color.BLACK);
            t.setForeground(Color.WHITE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        JScrollPane sp=new JScrollPane(t);
        sp.setPreferredSize(new Dimension(1200, 400)); // Adjusted size
        f1=new Font("Lucida Fax",Font.BOLD,25);
        
        l1=new JLabel("Want To Delete Any Customer ?");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(f1);
        l1.setForeground(Color.YELLOW);
        
        l2=new JLabel("Customer's Receipt Id");
        l2.setFont(f1);
        l2.setForeground(Color.GRAY);
        
        p1=new JPanel();
        p1.setLayout(new GridLayout(1,1,10,10));
        p1.add(l1);

        tf1=new JTextField();
        bt=new JButton("Delete Customer");
        tf1.setFont(new Font("MS UI Gothic",Font.PLAIN,18)); // Adjusted font size
        tf1.setPreferredSize(new Dimension(200, 30)); // Adjusted size
        bt.setFont(new Font("MS UI Gothic",Font.BOLD,18)); // Adjusted font size
        bt.setPreferredSize(new Dimension(200, 40)); // Adjusted size


        bt.setBorder(BorderFactory.createLineBorder(Color.RED)); // Adding border
        bt.setBackground(Color.BLACK);
        bt.setForeground(Color.RED);
        bt.setFocusPainted(false); // Removing focus border

        // Adding hover effect
        bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt.setBackground(Color.RED.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt.setBackground(Color.BLACK);
            }
        });

        bt.addActionListener(this);
        
        p2=new JPanel();
        p2.setLayout(new GridLayout(1,3,10,10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt);
        
        p3=new JPanel();
        p3.setLayout(new GridLayout(2,1,10,10));
        p3.add(p1);
        p3.add(p2);
        
        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);
        
        add(p3,"South");
        add(sp);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt) {
            String idText = tf1.getText();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Provide A valid Customer Receipt Number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(idText);
                ConnectionClass obj1 = new ConnectionClass();

                // Check if the Customer Receipt No exists in the add_Customer table
                String checkCustomerQuery = "SELECT * FROM add_Customer WHERE receiptNo = ?";
                PreparedStatement checkCustomerStatement = obj1.con.prepareStatement(checkCustomerQuery);
                checkCustomerStatement.setInt(1, id);
                ResultSet resultSet = checkCustomerStatement.executeQuery();
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Customer With Receipt : " + id + " does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Delete related rows from customer_attendance table
                String deleteAttendanceQuery = "DELETE FROM customer_attendance WHERE receiptNo = ?";
                PreparedStatement deleteAttendanceStatement = obj1.con.prepareStatement(deleteAttendanceQuery);
                deleteAttendanceStatement.setInt(1, id);
                deleteAttendanceStatement.executeUpdate();

                // Delete related rows from ProgressTracker table
                String deleteProgressTrackerQuery = "DELETE FROM ProgressTracker WHERE receiptNo = ?";
                PreparedStatement deleteProgressTrackerStatement = obj1.con.prepareStatement(deleteProgressTrackerQuery);
                deleteProgressTrackerStatement.setInt(1, id);
                deleteProgressTrackerStatement.executeUpdate();

                // Delete related rows from pay_customer table
                String deletePaymentQuery = "DELETE FROM pay_customer WHERE receiptNo = ?";
                PreparedStatement deletePaymentStatement = obj1.con.prepareStatement(deletePaymentQuery);
                deletePaymentStatement.setInt(1, id);
                deletePaymentStatement.executeUpdate();

                // Delete customer from add_Customer table
                String deleteCustomerQuery = "DELETE FROM add_Customer WHERE receiptNo = ?";
                PreparedStatement deleteCustomerStatement = obj1.con.prepareStatement(deleteCustomerQuery);
                deleteCustomerStatement.setInt(1, id);
                deleteCustomerStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer With Receipt No : " + id + " is Successfully Deleted !!");
                setVisible(false);
                new Customer_Information().setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Provide A Valid Customer Receipt Number", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle SQLException
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String args[])
    {
        new Customer_Information().setVisible(true);
    }
}

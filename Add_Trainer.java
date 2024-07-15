package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Add_Trainer extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8,tf9,tf10;
    Choice ch1,ch2,ch3;
    JButton bt1,bt2;
    JPanel p1,p2,p3;
    Font f1,f;
    public Add_Trainer()
    {
        super("Adding Trainer");
        setSize(750,700);
        setLocation(2,65);
        setResizable(false);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/16.png"));
        Image im=img.getImage().getScaledInstance(270,600,Image.SCALE_DEFAULT);
        l15=new JLabel(new ImageIcon(im));



        f1=new Font("Lucida Fax",Font.BOLD,25);
        f=new Font("MS UI Gothic",Font.BOLD,18);

        l1=new JLabel("Full Name");
        l2=new JLabel("Father's Name");
        l3=new JLabel("Email");
        l4=new JLabel("Address");
        l5=new JLabel("Contact No.");
        l6=new JLabel("Emergency No.");
        l7=new JLabel("Trainer Category");
        l8=new JLabel("Trainer ID");
        l9=new JLabel("MIN Agreement");
        l10=new JLabel("Gender");
        l11=new JLabel("Height (cm)");
        l12=new JLabel("Weight (kg)");
        l13=new JLabel("Remarks");
        l14=new JLabel(" CynoGym  ");

        l14.setFont(f1);
        l14.setForeground(Color.YELLOW);
        l14.setHorizontalAlignment(JLabel.CENTER);

        tf1=new JTextField();
        tf2=new JTextField();
        tf3=new JTextField();
        tf4=new JTextField();
        tf5=new JTextField();
        tf6=new JTextField();
        tf7=new JTextField();
        tf8=new JTextField();
        tf9=new JTextField();
        tf10=new JTextField();

        ch1=new Choice();
        ch2=new Choice();
        ch3=new Choice();

        ch1.add("Public");
        ch1.add("Personal");

        ch2.add("3 Months");
        ch2.add("6 Months");
        ch2.add("1 years");
        ch2.add("2 Years");
        ch2.add("5 Years");

        ch3.add("Male");
        ch3.add("Female");

        bt1=new JButton("Submit");
        bt2=new JButton("Cancel");

        bt1.setBackground(Color.BLACK);
        bt1.setForeground(Color.CYAN);
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.RED);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
        l7.setFont(f);
        l8.setFont(f);
        l9.setFont(f);
        l10.setFont(f);
        l11.setFont(f);
        l12.setFont(f);
        l13.setFont(f);

        tf1.setFont(f);
        tf2.setFont(f);
        tf3.setFont(f);
        tf4.setFont(f);
        tf5.setFont(f);
        tf6.setFont(f);
        tf7.setFont(f);
        tf8.setFont(f);
        tf9.setFont(f);
        tf10.setFont(f);

        ch1.setFont(f);
        ch2.setFont(f);
        ch3.setFont(f);

        bt1.setFont(f);
        bt2.setFont(f);

        p1=new JPanel();
        p1.setLayout(new GridLayout(14,2,10,10));
        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(tf2);
        p1.add(l3);
        p1.add(tf3);
        p1.add(l4);
        p1.add(tf4);
        p1.add(l5);
        p1.add(tf5);
        p1.add(l6);
        p1.add(tf6);
        p1.add(l7);
        p1.add(ch1);
        p1.add(l8);
        p1.add(tf7);
        p1.add(l9);
        p1.add(ch2);
        p1.add(l10);
        p1.add(ch3);
        p1.add(l11);
        p1.add(tf8);
        p1.add(l12);
        p1.add(tf9);
        p1.add(l13);
        p1.add(tf10);

        p1.add(bt1);
        p1.add(bt2);

        p1.setBackground(Color.BLACK);

        p2=new JPanel();
        p2.setLayout(new GridLayout(1,1,10,10));
        p2.setBackground(Color.BLACK);
        p2.add(l14);

        p3=new JPanel();
        p3.setLayout(new GridLayout(1,1,10,10));
        p3.setBackground(Color.BLACK);
        p3.add(l15);

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);
        l6.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        l8.setForeground(Color.WHITE);
        l9.setForeground(Color.WHITE);
        l10.setForeground(Color.WHITE);
        l11.setForeground(Color.WHITE);
        l12.setForeground(Color.WHITE);
        l13.setForeground(Color.WHITE);

        tf6.setForeground(Color.RED);

        setLayout(new BorderLayout(0,0));

        add(p2,"North");
        add(p3,"West");
        add(p1,"Center");

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
            // Check if any of the text fields are empty
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty() || tf7.getText().isEmpty() || tf8.getText().isEmpty() || tf9.getText().isEmpty() || tf10.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields first");
            } else {
                // If all fields are filled, proceed with data insertion
                String name = tf1.getText();
                String fname = tf2.getText();
                String email = tf3.getText();
                String address = tf4.getText();
                String cont = tf5.getText();
                String eno = tf6.getText();
                String tcat = ch1.getSelectedItem();
                int tid = Integer.parseInt(tf7.getText());
                String minag = ch2.getSelectedItem();
                String gender = ch3.getSelectedItem();
                float height = Float.parseFloat(tf8.getText());
                float weight = Float.parseFloat(tf9.getText());
                String remarks = tf10.getText();

                try {
                    ConnectionClass obj = new ConnectionClass();
                    String q = "insert into add_trainer values('" + tid + "','" + name + "','" + fname + "','" + email + "','" + address + "','" + cont + "','" + eno + "','" + tcat + "','" + minag + "','" + gender + "','" + height + "','" + weight + "','" + remarks + "')";
                    obj.stm.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Data Inserted Successfully!!");
                    JOptionPane.showMessageDialog(null, "Cynogym Welcomes The New Trainer!!");
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(null, "Are you Sure?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                setVisible(false);
                dispose(); // Terminate the program
            }
        }
    }

    public static void main(String args[])
    {
        new Add_Trainer().setVisible(true);
    }
}

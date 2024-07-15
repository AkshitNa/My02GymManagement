package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf;
    JPasswordField pf;
    JPanel p1;
    JButton bt1, bt2;
    Font f, f1;

    LoginPage() {
        super("Login Box");
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        f = new Font("Arial", Font.BOLD, 20);
        f1 = new Font("Arial", Font.BOLD, 15);

        l1 = new JLabel("Welcome To CynoGym");
        l2 = new JLabel("Username");
        l3 = new JLabel("Password");

        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        l3.setForeground(Color.BLACK);
        l1.setFont(f);
        l2.setFont(f1);
        l3.setFont(f1);

        tf = new JTextField();
        pf = new JPasswordField();

        tf.setFont(f1);
        pf.setFont(f1);

        // Image
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("Gym_Management/icons/login 2.png"));
        Image img2 = img.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        l4 = new JLabel(img3);

        bt1 = new JButton("Login");
        bt2 = new JButton("Close");

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        bt1.setBackground(Color.BLACK);
        bt2.setBackground(Color.BLACK);
        bt1.setForeground(Color.WHITE);
        bt2.setForeground(Color.WHITE);
        bt1.setFont(f1);
        bt2.setFont(f1);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(3, 2, 10, 10));
        p1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        p1.add(l2);
        p1.add(tf);
        p1.add(l3);
        p1.add(pf);
        p1.add(bt1);
        p1.add(bt2);

        setLayout(new BorderLayout(30, 30));

        add(l1, BorderLayout.NORTH);
        add(l4, BorderLayout.EAST);
        add(p1, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String name = tf.getText();
        String pass = pf.getText();
        if (e.getSource() == bt1){
            try {
                ConnectionClass obj=new ConnectionClass();
                String q="select * from loginpage where username='"+name+"' and password='"+pass+"'";
                ResultSet rest=obj.stm.executeQuery(q);
                // Here goes your database connection and login verification logic
                // For demonstration purpose, let's just show a message dialog

                if(rest.next())
                {
                    JOptionPane.showMessageDialog(null, "Logged in successfully!");
                    new HomePage().setVisible(true);
                    this.setVisible(false);
                    dispose(); // Terminate the program
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"No Data Available!!");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bt2) {
            int option = JOptionPane.showConfirmDialog(null, "Do you really want to close the application?",
                    "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new LoginPage().setVisible(true);
        });
    }
}

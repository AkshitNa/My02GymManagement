package Gym_Management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Trainer_Information extends JFrame implements ActionListener {
    String[] x = {"Trainer Id", "Full Name", "Father's Name", "Email", "Address",
            "Contact No.", "Emergency No.", "Category", "Min Agreement", "Gender",
            "Height", "Weight", "Remarks"};
    JButton bt;
    JPanel p1, p2, p3;
    JLabel l1, l2;
    JTextField tf1;
    Font f1, f2;
    String y[][] = new String[20][13];
    int i = 0, j = 0;
    JTable t;
    Font f;

    Trainer_Information() {
        super("Trainer Information");
        setSize(1600, 600);
        setLocationRelativeTo(null);

        // Setting Icon on FRAME
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        f = new Font("MS UI Gothic", Font.BOLD, 15);
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "Select * from add_trainer";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                y[i][j++] = rest.getString("tid");
                y[i][j++] = rest.getString("name");
                y[i][j++] = rest.getString("fname");
                y[i][j++] = rest.getString("email");
                y[i][j++] = rest.getString("address");
                y[i][j++] = rest.getString("contactNo");
                y[i][j++] = rest.getString("emergencyNo");
                y[i][j++] = rest.getString("category");
                y[i][j++] = rest.getString("minAgreement");
                y[i][j++] = rest.getString("gender");
                y[i][j++] = rest.getString("height");
                y[i][j++] = rest.getString("weight");
                y[i][j++] = rest.getString("remarks");
                i++;
                j = 0;
            }
            t = new JTable(y, x);
            t.setFont(new Font("MS UI Gothic", Font.PLAIN, 18)); // Adjusted font size
            t.setBackground(Color.BLACK);
            t.setForeground(Color.WHITE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JScrollPane sp = new JScrollPane(t);
        sp.setPreferredSize(new Dimension(1200, 400)); // Adjusted size

        f1 = new Font("Lucida Fax", Font.BOLD, 25);

        l1 = new JLabel("Want To Delete A Trainer ?");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(new Font("Lucida Fax", Font.BOLD, 20)); // Adjusted font size
        l1.setForeground(Color.YELLOW);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l1);

        tf1 = new JTextField();
        bt = new JButton("Delete Trainer");
        tf1.setFont(new Font("MS UI Gothic", Font.PLAIN, 18)); // Adjusted font size
        tf1.setPreferredSize(new Dimension(200, 30)); // Adjusted size
        bt.setFont(new Font("MS UI Gothic", Font.BOLD, 18)); // Adjusted font size
        bt.setPreferredSize(new Dimension(200, 40)); // Adjusted size

        // Customizing button appearance
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

        l2 = new JLabel("Trainer ID");
        l2.setFont(new Font("Lucida Fax", Font.BOLD, 20)); // Adjusted font size
        l2.setForeground(Color.GRAY);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 3, 10, 10));
        p2.add(l2);
        p2.add(tf1);
        p2.add(bt);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(2, 1, 10, 10));
        p3.add(p1);
        p3.add(p2);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        add(p3, "South");
        add(sp);

        pack(); // Adjusts frame size to fit components
        setLocationRelativeTo(null);
        setResizable(false);

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
                JOptionPane.showMessageDialog(this, "Please Provide A valid Trainer ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(idText);
                ConnectionClass obj1 = new ConnectionClass();

                // Delete related rows from trainer_salary table
                String deleteSalaryQuery = "DELETE FROM trainer_salary WHERE tid = ?";
                PreparedStatement deleteSalaryStatement = obj1.con.prepareStatement(deleteSalaryQuery);
                deleteSalaryStatement.setInt(1, id);
                deleteSalaryStatement.executeUpdate();

                // Delete related rows from trainer_attendance table
                String deleteAttendanceQuery = "DELETE FROM trainer_attendance WHERE tid = ?";
                PreparedStatement deleteAttendanceStatement = obj1.con.prepareStatement(deleteAttendanceQuery);
                deleteAttendanceStatement.setInt(1, id);
                deleteAttendanceStatement.executeUpdate();

                // Delete related rows from fee_trainer table
                String deleteFeeQuery = "DELETE FROM fee_trainer WHERE tid = ?";
                PreparedStatement deleteFeeStatement = obj1.con.prepareStatement(deleteFeeQuery);
                deleteFeeStatement.setInt(1, id);
                deleteFeeStatement.executeUpdate();

                // Delete trainer from add_trainer table
                String deleteTrainerQuery = "DELETE FROM add_trainer WHERE tid = ?";
                PreparedStatement deleteTrainerStatement = obj1.con.prepareStatement(deleteTrainerQuery);
                deleteTrainerStatement.setInt(1, id);
                deleteTrainerStatement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Trainer With ID " + id + " is Successfully Deleted !!");
                setVisible(false);
                new Trainer_Information().setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Provide A Valid Trainer ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Handle SQLException
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Trainer_Information().setVisible(true);
    }
}

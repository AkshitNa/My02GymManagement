package Gym_Management;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Gym_Profit extends JFrame {
    private Choice monthChoice;
    private Choice yearChoice; // Added year choice
    private JTextField profitTextField;
    private ConnectionClass connection;

    public Gym_Profit() {
        setTitle("CynoGym Profit");
        setSize(450, 250); // Increased height to accommodate the new field
        setLocation(885, 45);
        setResizable(false);
        connection = new ConnectionClass();

        try {
            UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Increased rows and added space between components
        panel.setBorder(new CompoundBorder(
                new EmptyBorder(20, 20, 20, 20),
                new LineBorder(new Color(0, 128, 128), 2, true)));

        JLabel monthLabel = new JLabel("Select Month:");
        monthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(monthLabel);

        monthChoice = new Choice();
        String[] months = getMonths();
        for (String month : months) {
            monthChoice.add(month);
        }
        monthChoice.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(monthChoice);

        JLabel yearLabel = new JLabel("Select Year:"); // Changed label text
        yearLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(yearLabel);

        yearChoice = new Choice(); // Added year choice
        for (int year = 2024; year <= 2050; year++) {
            yearChoice.add(String.valueOf(year));
        }
        yearChoice.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(yearChoice);

        JLabel profitLabel = new JLabel("Profit:");
        profitLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(profitLabel);

        profitTextField = new JTextField();
        profitTextField.setEditable(false);
        profitTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(profitTextField);

// Add empty borders or insets to create space
        monthLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Adjust right inset as needed
        yearLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Adjust right inset as needed
        profitLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Adjust right inset as needed

        add(panel);
        monthChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    calculateProfit(); // Call method to calculate profit whenever month or year changes
                }
            }
        });

        yearChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    calculateProfit(); // Call method to calculate profit whenever month or year changes
                }
            }
        });

        animateComponents(panel);
    }

    private void animateComponents(Component comp) {
        if (comp instanceof JPanel) {
            JPanel panel = (JPanel) comp;
            for (Component c : panel.getComponents()) {
                animateComponents(c);
            }
        } else if (comp instanceof JLabel) {
            JLabel label = (JLabel) comp;
            label.setOpaque(true);
            label.setBackground(new Color(230, 57, 70)); // Set label background color to Strong Red
            label.setForeground(Color.WHITE); // Use white foreground color
            label.setBorder(new RoundedBorder(10)); // Rounded border
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    label.setBackground(new Color(191, 29, 39)); // Set darker shade for hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    label.setBackground(new Color(230, 57, 70)); // Restore original color
                }
            });
        } else if (comp instanceof Choice) {
            Choice choice = (Choice) comp;
            choice.setBackground(new Color(230, 57, 70)); // Set choice box background color to Strong Red
            choice.setForeground(Color.WHITE); // Use white foreground color
            choice.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    choice.setBackground(new Color(191, 29, 39)); // Set darker shade for hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    choice.setBackground(new Color(230, 57, 70)); // Restore original color
                }
            });
        } else if (comp instanceof JComponent) {
            JComponent jComp = (JComponent) comp;
            jComp.setOpaque(true);
            jComp.setBackground(new Color(230, 57, 70)); // Set default background color to Strong Red
            jComp.setForeground(Color.WHITE); // Use white foreground color
            jComp.setBorder(new RoundedBorder(10)); // Rounded border
            jComp.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    jComp.setBackground(new Color(191, 29, 39)); // Set darker shade for hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jComp.setBackground(new Color(230, 57, 70)); // Restore original color
                }
            });
        }
    }


    private String[] getMonths() {
        return new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }

    private void calculateProfit() {
        String selectedMonth = monthChoice.getSelectedItem();
        String selectedYear = yearChoice.getSelectedItem();
        fetchProfit(selectedMonth, selectedYear);
    }

    private void fetchProfit(String selectedMonth, String selectedYear) {
        try {
            String query = "SELECT SUM(profit) AS totalProfit FROM trainer_salary WHERE month = '" + selectedMonth + "' AND YEAR(currentDate) = '" + selectedYear + "'";
            ResultSet rs = connection.stm.executeQuery(query);
            if (rs.next()) {
                int totalProfit = rs.getInt("totalProfit");
                if (rs.wasNull()) {
                    profitTextField.setText("No data available");
                } else {
                    profitTextField.setText(String.valueOf(totalProfit));
                }
            } else {
                profitTextField.setText("No data available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gym_Profit().setVisible(true);
            }
        });
    }
}

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage extends JFrame implements ActionListener {
    private JLabel backgroundLabel;
    private Font menuFont, menuItemFont;

    public HomePage() {
        super("CynoGym Welcomes You");
        initializeUI();
        setLookAndFeel();

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Set full-screen mode and remove window decorations
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
    }

    private void initializeUI() {
        // Setting Icon on FRAME
        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        menuFont = new Font("Segoe UI", Font.BOLD, 20);
        menuItemFont = new Font("Segoe UI", Font.PLAIN, 18);

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        // Create a gradient panel for background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(44, 62, 80); // Dark blue
                Color color2 = new Color(52, 152, 219); // Light blue
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        gradientPanel.setLayout(new BorderLayout());
        backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/Gym_Management/icons/HOME.jpg")));
        gradientPanel.add(backgroundLabel);
        add(gradientPanel);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        String[] menuNames = {"   CynoHome   ", "     Manage Members     ", "   Attendance   ", "   Member Profiles   ", "   Update Profiles   ",
                "   Fitness Plans   ", "     Financials     ", "   ToolKit   ", "     Exit     "};
        String[][] itemNames = {
                {"Gym Details", "Gym Gadgets", "Exercises Images"},
                {"Add Trainer", "Add Customer"},
                {"Trainer Attendance", "Customer Attendance"},
                {"Trainer Information", "Customer Information"},
                {"Update Trainer", "Update Customer"},
                {"Progress Tracker", "Fitness Bot", "BMR Calculator", "BMI Calculator", "Gym GPT"},
                {"Trainer Fees", "Customer Fees", "Trainer Salary", "Profit"},
                {"Calculator", "Weather App"},
                {"Exit"}
        };

        for (int i = 0; i < menuNames.length; i++) {
            JMenu menu = new JMenu(menuNames[i]);
            menu.setFont(menuFont);
            menu.setForeground(Color.WHITE); // Adjusted foreground color
            for (String itemName : itemNames[i]) {
                JMenuItem menuItem = new JMenuItem(itemName);
                menuItem.setFont(menuItemFont);
                menuItem.setForeground(Color.BLACK); // Adjusted foreground color
                menuItem.setBackground(Color.WHITE); // Adjusted background color
                menuItem.addActionListener(this); // Add action listener here

                // Add a "new" sticker to the menu item if it's the "Gym GPT" option
                if (itemName.equals("Gym GPT")) {
                    JLabel newLabel = new JLabel("New ");
                    newLabel.setForeground(Color.ORANGE); // Set color for the "new" sticker
                    newLabel.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0)); // Adjust spacing to the right
                    menuItem.setLayout(new BorderLayout());
                    menuItem.add(newLabel, BorderLayout.EAST); // Add "new" sticker to the right side
                }

                menu.add(menuItem);
            }
            menuBar.add(menu);
        }

        return menuBar;
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add Trainer":
                new Add_Trainer().setVisible(true);
                break;
            case "Add Customer":
                new Add_Customer().setVisible(true);
                break;
            case "Trainer Attendance":
                new Trainer_Attendance().setVisible(true);
                break;
            case "Customer Attendance":
                new Customer_Attendance().setVisible(true);
                break;
            case "Trainer Information":
                new Trainer_Information().setVisible(true);
                break;
            case "Customer Information":
                new Customer_Information().setVisible(true);
                break;
            case "Fitness Bot":
                new FitnessBot().setVisible(true);
                break;
            case "Update Trainer":
                new Update_Trainer().setVisible(true);
                break;
            case "Update Customer":
                new Update_Customer().setVisible(true);
                break;
            case "Trainer Fees":
                new Trainer_Fees().setVisible(true);
                break;
            case "Customer Fees":
                new Customer_Fees().setVisible(true);
                break;
            case "Trainer Salary":
                new Trainer_Salary().setVisible(true);
                break;
            case "Profit":
                new Gym_Profit().setVisible(true);
                break;
            case "Gym Details":
                new Gym_Details().setVisible(true);
                break;
            case "Gym Gadgets":
                new Gym_Gadgets().setVisible(true);
                break;
            case "Exercises Images":
                new Exercises_Split().setVisible(true);
                break;
            case "Calculator":
                new GymCalculator().setVisible(true);
                break;
            case  "Progress Tracker":
                new ProgressTrackerApp().setVisible(true);
                break;
            case "BMR Calculator":
                new BMR_Calculator().setVisible(true);
                break;
            case "BMI Calculator":
                new BMI_Calculator().setVisible(true);
                break;
            case "Weather App":
                new WeatherApp().setVisible(true);
                break;
            case "Gym GPT":
                new Gym_GPT().setVisible(true);
                break;
            case "Exit":
                JOptionPane.showMessageDialog(null, "Thank you! Please Visit Again!!");
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
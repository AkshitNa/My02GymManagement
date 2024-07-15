package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.awt.Desktop;
import java.io.File;

public class ProgressTrackerApp extends JFrame implements ActionListener {
    private JLabel receiptNoLabel, nameLabel, weightLabel, bodyFatLabel, heightLabel, chestCircumferenceLabel, waistCircumferenceLabel, hipCircumferenceLabel, imageLabel;
    private JComboBox<Integer> receiptNoComboBox;
    private JTextField nameField, weightField, bodyFatField, heightField, chestCircumferenceField, waistCircumferenceField, hipCircumferenceField;
    private JButton submitButton, uploadButton, displayImageButton;
    private File selectedImage;

    public ProgressTrackerApp() {
        setTitle("Progress Tracker");
        setSize(450, 550); // Increased height for image display
        setLocation(1000,45);
        setResizable(false);

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);


        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Initialize components
        receiptNoLabel = new JLabel("    Receipt No:");
        nameLabel = new JLabel("    Customer Name:");
        weightLabel = new JLabel("    Weight (kg):");
        bodyFatLabel = new JLabel("    Body Fat (%):");
        heightLabel = new JLabel("    Height (cm):");
        chestCircumferenceLabel = new JLabel("    Chest Measurement (cm):");
        waistCircumferenceLabel = new JLabel("    Waist Measurement (cm):");
        hipCircumferenceLabel = new JLabel("    Hips Measurement (cm):");
        imageLabel = new JLabel();
        uploadButton = new JButton("Upload Image");
        uploadButton.addActionListener(this);
        uploadButton.setBackground(Color.GRAY);
        displayImageButton = new JButton("Display Image");
        displayImageButton.addActionListener(this);
        displayImageButton.setBackground(Color.ORANGE);

        // Initialize JComboBox
        receiptNoComboBox = new JComboBox<>();
        fetchReceiptNumbers();

        // Initialize text fields
        nameField = new JTextField(20);
        weightField = new JTextField(10);
        bodyFatField = new JTextField(10);
        heightField = new JTextField(10);
        chestCircumferenceField = new JTextField(10);
        waistCircumferenceField = new JTextField(10);
        hipCircumferenceField = new JTextField(10);

        // Initialize submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.GREEN);

        // Set layout
        JPanel panel = new JPanel(new GridLayout(9, 2)); // Increased row count for image display
        setLayout(new BorderLayout());

        // Add components to the panel
        panel.add(receiptNoLabel);
        panel.add(receiptNoComboBox);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(weightLabel);
        panel.add(weightField);
        panel.add(bodyFatLabel);
        panel.add(bodyFatField);
        panel.add(heightLabel);
        panel.add(heightField);
        panel.add(chestCircumferenceLabel);
        panel.add(chestCircumferenceField);
        panel.add(waistCircumferenceLabel);
        panel.add(waistCircumferenceField);
        panel.add(hipCircumferenceLabel);
        panel.add(hipCircumferenceField);
        panel.add(new JLabel()); // Placeholder
        panel.add(imageLabel);

        // Add the panel to the center and the submit button to the bottom
        add(panel, BorderLayout.CENTER);

        // Create button panel for the bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(uploadButton);
        buttonPanel.add(displayImageButton);
        buttonPanel.add(submitButton);

        // Add button panel to the bottom of the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to receiptNoComboBox to fetch name when receipt number is selected
        receiptNoComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedReceiptNo = (int) receiptNoComboBox.getSelectedItem();
                fetchCustomerName(selectedReceiptNo);
            }
        });

        setVisible(true);
    }

    // Method to fetch receipt numbers from the database and populate the JComboBox
    private void fetchReceiptNumbers() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT receiptNo FROM add_customer"; // Fetch from ProgressTracker table
            ResultSet rs = obj.stm.executeQuery(q);
            while (rs.next()) {
                receiptNoComboBox.addItem(rs.getInt("receiptNo"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to fetch customer name based on receipt number
    private void fetchCustomerName(int receiptNo) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT name FROM add_customer WHERE receiptNo = ?"; // Fetch from ProgressTracker table
            PreparedStatement pstmt = obj.con.prepareStatement(q);
            pstmt.setInt(1, receiptNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                nameField.setText(name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final long MAX_IMAGE_SIZE_BYTES = 5 * 1024 * 1024;

    // Method to handle file upload action
    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            selectedImage = fileChooser.getSelectedFile();
            long fileSize = selectedImage.length();
            if (fileSize > MAX_IMAGE_SIZE_BYTES) {
                JOptionPane.showMessageDialog(this, "Image size exceeds 5MB limit.");
                selectedImage = null;
            } else {
                imageLabel.setIcon(new ImageIcon(selectedImage.getPath()));
            }
        }
    }

    // Method to display the image associated with the selected receiptID
    private void displayImage(int receiptNo) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT photo FROM ProgressTracker WHERE receiptNo = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setInt(1, receiptNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                InputStream input = rs.getBinaryStream("photo");
                if (input != null) {
                    // Create a temporary file to store the image data
                    File tempFile = File.createTempFile("tempImage", ".jpg");
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    fos.close();
                    input.close();

                    // Open the temporary file using the default system application
                    Desktop.getDesktop().open(tempFile);

                    // Delete the temporary file when it's no longer needed
                    tempFile.deleteOnExit();
                } else {
                    JOptionPane.showMessageDialog(this, "No image found for this receipt.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check if any field is empty
            if (nameField.getText().isEmpty() ||
                    weightField.getText().isEmpty() ||
                    bodyFatField.getText().isEmpty() ||
                    heightField.getText().isEmpty() ||
                    chestCircumferenceField.getText().isEmpty() ||
                    waistCircumferenceField.getText().isEmpty() ||
                    hipCircumferenceField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all the fields first");
                return; // Stop further execution
            }

            // Proceed with data insertion if all fields are filled
            int receiptNo = (int) receiptNoComboBox.getSelectedItem();
            String name = nameField.getText();
            double weight = Double.parseDouble(weightField.getText());
            double bodyFat = Double.parseDouble(bodyFatField.getText());
            double height = Double.parseDouble(heightField.getText());
            double chestCircumference = Double.parseDouble(chestCircumferenceField.getText());
            double waistCircumference = Double.parseDouble(waistCircumferenceField.getText());
            double hipCircumference = Double.parseDouble(hipCircumferenceField.getText());

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "INSERT INTO ProgressTracker (receiptNo, name, weight, body_fat, height, chest_circumference, waist_circumference, hip_circumference, photo, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
                PreparedStatement pstmt = obj.con.prepareStatement(query);
                pstmt.setInt(1, receiptNo);
                pstmt.setString(2, name);
                pstmt.setDouble(3, weight);
                pstmt.setDouble(4, bodyFat);
                pstmt.setDouble(5, height);
                pstmt.setDouble(6, chestCircumference);
                pstmt.setDouble(7, waistCircumference);
                pstmt.setDouble(8, hipCircumference);
                if (selectedImage != null) {
                    FileInputStream fis = new FileInputStream(selectedImage);
                    pstmt.setBinaryStream(9, fis, (int) selectedImage.length());
                } else {
                    pstmt.setNull(9, Types.BLOB);
                }

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Progress tracked successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to track progress.");
                }

                obj.con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == uploadButton) {
            uploadImage();
        } else if (e.getSource() == displayImageButton) {
            int selectedReceiptNo = (int) receiptNoComboBox.getSelectedItem();
            displayImage(selectedReceiptNo);
        }
    }

    public static void main(String[] args) {
        new ProgressTrackerApp();
    }
}

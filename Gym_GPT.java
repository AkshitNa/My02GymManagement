package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.*;
import java.io.*;
import java.net.*;

public class Gym_GPT extends JFrame {
    private JLabel askLabel;
    private JTextField askTextField;
    private JButton gymGPTButton;
    private JTextArea responseTextArea;

    private static final String API_KEY = "Your_API_KEY";
    private static final String API_URL_FORMAT = "Your_API_URL";

    public Gym_GPT() {
        setTitle("Gym GPT");
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add a window listener to handle the close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Setting Icon on FRAME

        ImageIcon loginImage = new ImageIcon(getClass().getResource("/Gym_Management/icons/777.png"));
        Image resultloginImage = loginImage.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        setIconImage(resultloginImage);

        // Create components
        askLabel = new JLabel("Ask ME:");
        askLabel.setFont(new Font("Arial", Font.BOLD, 16));
        askTextField = new JTextField(20);
        gymGPTButton = new JButton("Gym GPT");
        gymGPTButton.setFont(new Font("Arial", Font.BOLD, 14));
        gymGPTButton.setBackground(new Color(59, 89, 182));
        gymGPTButton.setForeground(Color.WHITE);
        responseTextArea = new JTextArea(10, 30);
        responseTextArea.setEditable(false);

        // Set layout
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.add(askLabel);
        topPanel.add(askTextField);
        topPanel.add(gymGPTButton);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(responseTextArea), BorderLayout.CENTER);

        // Add action listener to the button
        gymGPTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateResponse();
            }
        });
    }

    private void generateResponse() {
        String input = askTextField.getText();
        if (!input.isEmpty()) {
            try {
                String response = getGPTResponse(input); // Pass the user's input as prompt
                responseTextArea.append("AI Response: " + response + "\n");
            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
                responseTextArea.append("Error generating response. Please try again.\n");
            }
        } else {
            responseTextArea.append("Please enter a question.\n");
        }
        askTextField.setText("");
    }


    private String getGPTResponse(String prompt) throws IOException {
        URL url = new URL(API_URL_FORMAT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("model", "gpt-3.5-turbo");
        json.put("temperature", 1);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt); // Use the prompt parameter here
        messages.put(message);
        json.put("messages", messages);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = json.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gym_GPT().setVisible(true);
            }
        });
    }
}

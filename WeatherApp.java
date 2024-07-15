package Gym_Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class WeatherApp extends JFrame {
    private JTextField cityTextField;
    private JTextField weatherTextField;
    private JTextField temperatureTextField;
    private JTextField cityDisplayTextField;
    private JTextField humidityTextField;
    private JTextField pressureTextField;
    private JTextField countryTextField;

    public WeatherApp() {
        setTitle("Weather App");
        setSize(600, 400);
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

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240)); // Set background color

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(200, 200, 200)); // Set panel background color
        JLabel cityPromptLabel = new JLabel("     Enter City:");
        cityPromptLabel.setForeground(Color.WHITE); // Set text color
        cityTextField = new JTextField(20);
        JButton getWeatherButton = new JButton("     Get Weather");
        getWeatherButton.setBackground(new Color(50, 150, 250)); // Set button background color
        getWeatherButton.setForeground(Color.WHITE); // Set button text color

        inputPanel.add(cityPromptLabel);
        inputPanel.add(cityTextField);
        inputPanel.add(getWeatherButton);

        JPanel weatherInfoPanel = new JPanel();
        weatherInfoPanel.setBackground(new Color(220, 220, 220)); // Set panel background color
        weatherInfoPanel.setLayout(new GridLayout(6, 2, 5, 5));

        weatherInfoPanel.add(createLabel("     Weather:"));
        weatherTextField = new JTextField();
        weatherTextField.setEditable(false);
        weatherInfoPanel.add(weatherTextField);

        weatherInfoPanel.add(createLabel("     Temperature:"));
        temperatureTextField = new JTextField();
        temperatureTextField.setEditable(false);
        weatherInfoPanel.add(temperatureTextField);

        weatherInfoPanel.add(createLabel("     City:"));
        cityDisplayTextField = new JTextField();
        cityDisplayTextField.setEditable(false);
        weatherInfoPanel.add(cityDisplayTextField);

        weatherInfoPanel.add(createLabel("     Humidity:"));
        humidityTextField = new JTextField();
        humidityTextField.setEditable(false);
        weatherInfoPanel.add(humidityTextField);

        weatherInfoPanel.add(createLabel("     Pressure:"));
        pressureTextField = new JTextField();
        pressureTextField.setEditable(false);
        weatherInfoPanel.add(pressureTextField);

        weatherInfoPanel.add(createLabel("     Country:"));
        countryTextField = new JTextField();
        countryTextField.setEditable(false);
        weatherInfoPanel.add(countryTextField);

        add(inputPanel, BorderLayout.NORTH);
        add(weatherInfoPanel, BorderLayout.CENTER);

        // Add ActionListener to the button to trigger getWeather() method
        getWeatherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getWeather();
            }
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE); // Set label text color to white for better visibility
        return label;
    }

    private void getWeather() {
        String city = cityTextField.getText().trim();
        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a city name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String apiKey = "586d32bea48f396539330dc75c933f4a";
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(city, "UTF-8") + "&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response and extract relevant weather information
                parseWeatherInfo(response.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Failed to fetch weather information. HTTP Error Code: " + conn.getResponseCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void parseWeatherInfo(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            String weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15; // Convert Kelvin to Celsius
            String cityName = jsonObject.getString("name");
            int humidity = jsonObject.getJSONObject("main").getInt("humidity");
            double pressure = jsonObject.getJSONObject("main").getDouble("pressure");
            String countryName = jsonObject.getJSONObject("sys").getString("country");

            weatherTextField.setText(weather);
            temperatureTextField.setText(String.format("%.2f°C", temperature));
            cityDisplayTextField.setText(cityName);
            humidityTextField.setText(humidity + "%");
            pressureTextField.setText(pressure + " hPa");
            countryTextField.setText(countryName);
        } catch (JSONException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error parsing weather information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WeatherApp().setVisible(true);
            }
        });
    }
}

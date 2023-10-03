import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;

public class vsuals extends JFrame{
    private static final long serialVersionUID = 1L;
    private JTextArea responseTextArea;
    private JTextField locationTextField;
    public vsuals() {
        setTitle("WeatherAPI Example");
        setSize(400, 200); // Adjusted the height for a cleaner look
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Create a label for the location input
        JLabel locationLabel = new JLabel("Enter Location:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(locationLabel, gbc);

        // Create the location text field
        locationTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(locationTextField, gbc);

        // Create a label for the response
        JLabel responseLabel = new JLabel("Weather Response:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(responseLabel, gbc);

        // Create the response text area within a scroll pane
        responseTextArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(responseTextArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(scrollPane, gbc);

        // Create the fetch button
        JButton fetchButton = new JButton("Fetch Weather");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(fetchButton, gbc);

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });

        setContentPane(contentPanel);
        pack(); // Pack the frame to fit the components nicely
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    private void fetchWeather() {
        String apiKey = "685468e6b7884520aaf125923231707"; // Replace with your WeatherAPI API key
        String location = "London"; // Replace with the desired location
        try {
            URL url = new URL("https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            responseTextArea.setText(response.toString()); // Display the raw response in the text area
            // TODO: Parse the response JSON and update the GUI with the relevant weather information
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new vsuals();
    }
}

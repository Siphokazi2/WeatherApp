import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Visuals extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea responseTextArea;
    private JTextField locationTextField;

    public Visuals() {
        setTitle("WeatherAPI Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        responseTextArea = new JTextArea();
        contentPanel.add(responseTextArea, BorderLayout.SOUTH);

        locationTextField = new JTextField();
        contentPanel.add(locationTextField, BorderLayout.NORTH);

        JButton fetchButton = new JButton("Fetch Weather");
        fetchButton.setPreferredSize(new Dimension(100, 100)); // Set preferred size to 100x100
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchWeather();
            }
        });

        // Center the button using an EmptyBorder
        int padding = 5;
        fetchButton.setBorder(new EmptyBorder(padding, padding, padding, padding));
        contentPanel.add(fetchButton, BorderLayout.CENTER);

        setContentPane(contentPanel);
        setVisible(true);
    }

    // ... (the rest of your code remains the same)



    private void fetchWeather() {
        String apiKey = "685468e6b7884520aaf125923231707"; // Replace with your WeatherAPI API key
        String location = locationTextField.getText().trim(); // Get the location from the search bar

        if (location.isEmpty()) {
            responseTextArea.setText("Please enter a location.");
            return;
        }

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
        new Visuals();
    }
}

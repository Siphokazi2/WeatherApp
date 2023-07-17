import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class start {
    public static void main(String[] args){
        System.out.println("hello world");
        String key = "685468e6b7884520aaf125923231707";
        String location = "Johannesburg"; // Replace with the desired location
        String weatherData = getWeather(key, location);
        System.out.println(weatherData);
    }
    public static String getWeather(String apiKey, String location) {
        try {
            String url = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }










}

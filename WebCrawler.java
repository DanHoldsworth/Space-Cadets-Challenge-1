import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebCrawler {

    public static void main(String args[]) {

        String address;
        String username;

        try {
            InputStreamReader inputReader = new InputStreamReader(System.in);
            InputStreamReader URLReader;
            BufferedReader usernameReader = new BufferedReader(inputReader);
            BufferedReader webReader;
            URL url;
            String returnLine;
            String name;
            int endOfName;

            System.out.println("Please enter a username: ");
            username = usernameReader.readLine();
            address = "https://www.ecs.soton.ac.uk/people/" + username;
            url = new URL(address);
            URLReader = new InputStreamReader(url.openStream());
            webReader = new BufferedReader(URLReader);
            
            returnLine = webReader.readLine();
            while(returnLine != null) {
                if (returnLine.length() < 26) {
                    returnLine = webReader.readLine();
                }
                else if (returnLine.substring(16, 24).equals("og:title")) {
                    endOfName = returnLine.length() - 4;
                    name = returnLine.substring(35, endOfName);
                    System.out.println(name);
                    returnLine = null;
                }
                else {
                    returnLine = webReader.readLine();
                }
            }

            webReader.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}

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

            System.out.println("Please enter a username: "); //prompts user for an input
            username = usernameReader.readLine(); //reads input
            address = "https://www.ecs.soton.ac.uk/people/" + username; //creates web address
            url = new URL(address); //creates URL object from web address
            URLReader = new InputStreamReader(url.openStream());
            webReader = new BufferedReader(URLReader);
            
            returnLine = webReader.readLine(); //reads the first line of the website
            while(returnLine != null) { //cycles until the return is null meaning the whole file has been read
                if (returnLine.length() < 26) { //checks whether the line is long enough to be the correct one
                    returnLine = webReader.readLine();
                }
                else if (returnLine.substring(16, 24).equals("og:title")) { //looks for the correct line
                    endOfName = returnLine.length() - 4; //gets the position of the final character of the name
                    name = returnLine.substring(35, endOfName); //gets the substring which makes up the name from the whole line
                    System.out.println(name); //prints the name
                    returnLine = null; //escapes the while loop by setting returnLine to null
                }
                else {
                    returnLine = webReader.readLine(); //skips to the next line of the file if the line is not correct
                }
            }

            webReader.close(); //closes the connection to the URL
        } catch (IOException e) {
            System.out.println("IO exception"); //catches IO exceptions
        }
    }
}

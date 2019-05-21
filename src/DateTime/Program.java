package DateTime;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Program {

    public static void main(String args[]) throws IOException {
        int port = 13;
        String hostname = "time.nist.gov";

        try (Socket socket = new Socket(hostname, port)) {

            socket.setSoTimeout(1000);

            InputStream input = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(input);
            int character;
            StringBuilder data = new StringBuilder();

            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }

            System.out.println(data);

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}

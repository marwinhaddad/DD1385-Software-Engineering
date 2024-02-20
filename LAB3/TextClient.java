import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TextClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4713);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out =  new PrintWriter(socket.getOutputStream());

            out.println("Charlotta");
            out.flush();

            System.out.println(in.readLine());

            Scanner sc = new Scanner(System.in);
            System.out.println("ROCK/ PAPER/ SCISSORS:");

            while (true) {
                String input = sc.nextLine();

                out.println(input);
                out.flush();

                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

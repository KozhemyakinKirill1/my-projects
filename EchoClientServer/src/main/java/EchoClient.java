import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
	public static void main(String[] args) {
		try{
			InetAddress localHost = InetAddress.getLocalHost();
			Socket socket = new Socket(localHost,8000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Connected to Server");
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.println("Enter your text: ");
				String input = sc.nextLine();
				if ("exit".equalsIgnoreCase(input)){
					break;
				}
				out.println(input);
				String response = buffer.readLine();
				System.out.println("Server response: " + response);
			}
		} 
		catch (Exception e) {

		}
	}
}
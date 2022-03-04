import java.net.*;
import java.io.*;


public class EchoServer {
	public static void main(String[] args){
		try (ServerSocket echoServer = new ServerSocket(8000)){
			Socket clientSocket = echoServer.accept();
			System.out.println("Client is connected!");
			BufferedReader bufferReader = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter outPut = new PrintWriter(clientSocket.getOutputStream(), true);
			String line;
			while ((line = bufferReader.readLine()) != null){
				System.out.println("Client wrote: " + line);
				outPut.println(line);
			}
		}
		catch (Exception e ) {
			e.printStackTrace();
		}
	}
}
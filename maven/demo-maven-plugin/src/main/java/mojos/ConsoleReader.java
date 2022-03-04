package mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

@Mojo(name = "reader")
public class ConsoleReader extends AbstractMojo {
    public void execute(){

        try {
            FileReader file = new FileReader("file.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

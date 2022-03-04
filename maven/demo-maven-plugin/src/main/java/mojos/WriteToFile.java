package mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Mojo(name="writingToFile")
public class WriteToFile extends AbstractMojo {


    @Parameter(property = "project.groupId")
    private String groupId;

    @Parameter(property = "project.artifactId")
    private String artifactId;

    @Parameter(property = "project.version")
    private String version;

    @Parameter(property = "timestamp")
    private String buildTimestamp;

    public void execute() {
        try {
            File file = new File("file.txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(groupId + "\n" + artifactId + "\n" + version + "\n" + buildTimestamp);
            fileWriter.flush();
            fileWriter.close();
            getLog().info("File was wrote!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

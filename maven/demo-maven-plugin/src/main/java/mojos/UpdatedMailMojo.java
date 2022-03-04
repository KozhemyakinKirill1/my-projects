package mojos;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "updatedReporter")
public class UpdatedMailMojo extends AbstractMojo {
    @Parameter(property = "updatedReporter.username", defaultValue = "")
    private String username;

    @Parameter(property = "updatedReporter.password")
    private String password;

    @Parameter(property = "updatedReporter.theme")
    private String theme;

    public void execute() {
        MailSender postman = new MailSender();
        getLog().info("Trying to sent a email by " + this.getClass().getSimpleName());
        postman.sendMail(username, password, theme);
        getLog().info("Email has been sent successfully.");
    }
}

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHContentBuilder;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class winurl {
    private static String OAuth = "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";
    private static GitHub github;
    private static winurl instance = null;

    private winurl(String x) {
        try {
            github = GitHub.connectUsingOAuth(OAuth);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private winurl() {
    }

    public static winurl getInstance() {
        if (instance == null)
            instance = new winurl("");
        return instance;
    }

    public void writeRepoFile(String filePath, String content, String commitMessage) {

        try {
            GHRepository repo = github.getRepository("XCode-MasterX/CG-Collections");
            GHContent fileToUpdate = repo.getFileContent(filePath, "main");
            fileToUpdate.update(content, commitMessage);
        } catch (GHFileNotFoundException e) {
            if (!createRepoFile(filePath, content, commitMessage))
                System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private boolean createRepoFile(String filePath, String content, String commitMessage) {
        try {
            GHRepository repo = github.getRepository("XCode-MasterX/CG-Collections");
            GHContentBuilder contentBuilder = repo.createContent();
            contentBuilder.path(filePath).content(content).message(commitMessage).branch("main").commit();
            return true;
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return false;
        }
    }

    public String readRepoFile(String filePath) {
        try {
            GHRepository repo = github.getRepository("XCode-MasterX/CG-Collections");
            GHContent file = repo.getFileContent(filePath, "main");
            String content = new String(file.read().readAllBytes(), StandardCharsets.UTF_8);
            return content;
        } catch (GHFileNotFoundException e) {
            return "Nothing found";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[] newWindow(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(instance.getClass().getResource(path));
        if (!path.equals("CommentViewer.fxml"))
            loader.setRoot(new AnchorPane());
        Parent x = loader.load();
        Scene scene = new Scene(x);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        return new Object[] { loader.getController(), stage };
    }
}
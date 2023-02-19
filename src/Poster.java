import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Poster {
    String poster, repo;
    Stage stage;
    private AccountAlgo ac = new AccountAlgo();

    @FXML
    private TextArea descArea;

    @FXML
    private Label idLabel;

    @FXML
    private Button submit;

    @FXML
    private TextField titleField;

    @FXML
    protected Label typeLabel;

    public Poster() {
        repo = winurl.getInstance().readRepoFile("postsMade.txt");
    }

    @FXML
    void submitClicked(ActionEvent event) {
        if (descArea.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("BIG ISSUE!");
            alert.setContentText("The description can't be empty!");
            alert.showAndWait();

            return;
        }

        if (titleField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("BIG ISSUE!");
            alert.setContentText("The title can't be empty!");
            alert.showAndWait();

            return;
        }

        String content = "Poster: " + poster + "\nTitle: " + titleField.getText() + "\n" + typeLabel.getText()
                + "\nContent: "
                + descArea.getText();
        String postId = createID();

        winurl.getInstance().writeRepoFile(postId + ".post", content, "New post by: " + poster);
        winurl.getInstance().writeRepoFile("postsMade.txt", repo.trim() + postId + "\n", "New Post ID");
        stage.close();
    }

    private String createID() {
        String id = "";
        for (int i = 0; i < 10; i++)
            id += (char) (65 + (int) (Math.random() * 26));
        while (repo.contains(id))
            id = ac.getEncryption(id, id);
        return id;
    }

    @FXML
    void submitEnter(MouseEvent event) {
        submit.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #000000;");
    }

    @FXML
    void submitExit(MouseEvent event) {
        submit.setStyle("-fx-background-radius: 10; -fx-background-color: #ffA500; -fx-text-fill: #000000;");
    }

    @FXML
    void titleKeyRelease(KeyEvent event) {
        idLabel.setText("ID: " + createID());
    }
}
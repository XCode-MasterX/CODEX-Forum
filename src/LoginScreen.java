import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginScreen {
    private winurl w = winurl.getInstance();
    private AccountAlgo algo = new AccountAlgo();
    Stage stage;
    String user;

    @FXML
    Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label intro;

    @FXML
    private Label userLabel;

    @FXML
    private Label passLabel;

    @FXML
    void loginClicked(ActionEvent event) {
        user = username.getText();
        String pass = password.getText();
        algo = new AccountAlgo(w.readRepoFile("users.txt").split("\n"),
                w.readRepoFile("passwords.txt").split("\n"));
        userLabel.setWrapText(true);
        passLabel.setWrapText(true);
        userLabel.setFont(new Font("Arial", 12));
        passLabel.setFont(new Font("Arial", 12));

        // Here start a bunch of guard clauses.
        if (user.equals("")) {
            userLabel.setText("Username is a required field.");
            return;
        }
        if (pass.equals("")) {
            passLabel.setText("Password is a required field.");
            return;
        }
        if (contains(user, "!@#$%^&*()<>?:\"\',./\\|{}[]+=_-")) {
            userLabel.setText("Username can't contain special characters. Try again.");
            return;
        }
        if (!algo.tryLogin(user, pass)) {
            intro.setText("   Um.. Looks like something was wrong.\n   Mind rechecking the username and password?");
            intro.setTextFill(Color.web("#FF76a3"));
            intro.setFont(new Font("Arial", 18));
            return;
        }

        try {
            intro.setText("Login Success!");
            intro.setFont(new Font("Arial", 24));
            intro.setTextFill(Color.web("#00FFFF"));

            Forum x = (Forum) w.newWindow("Forum.fxml")[0];
            x.sendClicked(null);
            String postFile = w.readRepoFile("postsMade.txt").trim();

            // This is a guard clause in case, there are no posts found
            if (postFile == null || postFile.trim().isEmpty()) {
                stage.close();
                return;
            }

            String postRecords[] = postFile.split("\n");
            x.index = (int) (Math.random() * postRecords.length);
            x.refresh(x.index);
            x.prevInstance = new LoginScreen();
            x.prevInstance.user = this.user;
            x.callThis();

            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpClicked(ActionEvent event) {
        String previous = w.readRepoFile("users.txt").trim();
        String user = username.getText(), pass = password.getText();
        pass = algo.getEncryption(user, pass);

        // GUARD CLAUSE START
        if (user.equals("")) {
            userLabel.setText("Username is a required field.");
            return;
        }
        if (pass.equals("")) {
            passLabel.setText("Password is a required field.");
            return;
        }
        if (contains(user, "!@#$%^&*()<>?:\"',./\\|{}[]+=_-")) {
            userLabel.setText("Username can't contain special characters. Try again.");
            userLabel.setWrapText(true);
            return;
        }
        // GUARD CLAUSE END

        if (!previous.contains(username.getText())) {
            w.writeRepoFile("users.txt", (previous + "\n" + user).trim(), "new user");
            previous = w.readRepoFile("passwords.txt");
            w.writeRepoFile("passwords.txt", (previous + "\n" + pass).trim(), "new user's password");
            return;
        }

        String userList[] = previous.trim().split("\n");
        String passwordList[] = w.readRepoFile("passwords.txt").trim().split("\n");
        int index = index(userList, user);

        passwordList[index] = pass;
        w.writeRepoFile("users.txt", previous.trim(), "user exists");
        w.writeRepoFile("passwords.txt", arrayMerge(passwordList).trim(), "password updated");
    }

    private boolean contains(String text, String req) {
        for (char c : req.toCharArray())
            if (text.contains(c + ""))
                return true;
        return false;
    }

    public String arrayMerge(ArrayList<String> list) {
        String ret = "";
        for (String s : list)
            ret += s;
        return ret;
    }

    public String arrayMerge(String list[]) {
        String ret = "";
        for (String s : list)
            ret += s;
        return ret;
    }

    private int index(String[] list, String check) {
        for (int i = 0; i < list.length; i++)
            if (list[i].equals(check))
                return i;
        return -1;
    }

    @FXML
    void loginEnter(MouseEvent event) {
        loginButton.setStyle("-fx-background-radius: 20; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void loginExit(MouseEvent event) {
        loginButton.setStyle("-fx-background-radius: 20; -fx-background-color: #FFA500;");
    }

    @FXML
    void signUpEnter(MouseEvent event) {
        signUpButton.setStyle("-fx-background-radius: 20; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void signUpExit(MouseEvent event) {
        signUpButton.setStyle("-fx-background-radius: 20; -fx-background-color: #FFA500;");
    }

}
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class Forum {

    LoginScreen prevInstance;
    private String currentPostID = "", currentType = "", postRead = "";
    private Chatbot chatInstance = new Chatbot();
    private winurl w = winurl.getInstance();
    public ArrayList<String> postLinks = new ArrayList<String>();
    public int index = 0;

    public void callThis() {
        userName.setText(prevInstance.user);
        prevInstance = null;
    }

    @FXML
    private AnchorPane chatView;

    @FXML
    private TextArea chatArea;

    @FXML
    private Button claim;

    @FXML
    private Button viewComment;

    @FXML
    private Button commentButton;

    @FXML
    private Button createDemand;

    @FXML
    private Button createRequest;

    @FXML
    private Button nextButton;

    @FXML
    protected Label postContent;

    @FXML
    protected Label postID;

    @FXML
    protected Label postType;

    @FXML
    protected Label posterName;

    @FXML
    private Button prevButton;

    @FXML
    protected Label userName;

    @FXML
    private Button sendButton;

    @FXML
    private TextField userInput;

    @FXML
    void claimClicked(ActionEvent event) {
        if (posterName.getText().equals(userName.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Ayo, bro!");
            alert.setHeaderText("Wat'cha doing there?");
            alert.setContentText("you can't claim your own post! Come on man!");
            alert.showAndWait();
            return;
        }
        String inPostFile = "Poster: " + posterName.getText() + "\n" + postType.getText() + "\n"
                + postContent.getText();
        String what = currentType.contains("Request") ? "\nFulfilled: " : "\nClaimed: ";
        inPostFile += what + userName.getText();

        winurl.getInstance().writeRepoFile(currentPostID, inPostFile, currentPostID);
    }

    @FXML
    void claimEnter(MouseEvent event) {
        claim.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void claimExit(MouseEvent event) {
        claim.setStyle("-fx-background-radius: 10; -fx-background-color: #ffa500; -fx-text-fill: #000000;");
    }

    @FXML
    void commentClicked(ActionEvent event) {
        Optional<String> result = Optional.empty();
        while (!result.isPresent()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Input Dialog");
            dialog.setHeaderText("Enter your comment: ");
            dialog.setContentText("Comment:");

            // Show the dialog box and wait for user input
            result = dialog.showAndWait();

            // Check if the user clicked the "OK" button and entered a value
            if (result.isPresent()) {
                String comment = result.get();
                w.writeRepoFile(postID.getText() + ".post", postRead + comment, comment);
                break;
            }
        }
    }

    @FXML
    void commentEnter(MouseEvent event) {
        commentButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void commentExit(MouseEvent event) {
        commentButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ffa500; -fx-text-fill: #000000;");
    }

    @FXML
    void createDemandClicked(ActionEvent event) {
        try {
            Object o[] = winurl.getInstance().newWindow("Post.fxml");
            Poster x = (Poster) o[0];
            x.stage = (Stage) o[1];
            x.typeLabel.setText("Type: Demand");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void createDemandEnter(MouseEvent event) {
        createDemand.setStyle("-fx-background-radius: 20; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void createDemandExit(MouseEvent event) {
        createDemand.setStyle("-fx-background-radius: 20; -fx-background-color: #ffa500; -fx-text-fill: #000000;");
    }

    @FXML
    void createRequestEnter(MouseEvent event) {
        createRequest.setStyle("-fx-background-radius: 20; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void createRequestExit(MouseEvent event) {
        createRequest.setStyle("-fx-background-radius: 20; -fx-background-color: #ffA500; -fx-text-fill: #000000;");
    }

    @FXML
    void createRequestClicked(ActionEvent event) {
        try {
            Object o[] = winurl.getInstance().newWindow("Post.fxml");
            Poster x = (Poster) o[0];
            x.stage = (Stage) o[1];
            x.typeLabel.setText("Type: Request");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void nextClicked(ActionEvent event) {
        refresh(++index);
    }

    @FXML
    void nextEnter(MouseEvent event) {
        nextButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void nextExit(MouseEvent event) {
        nextButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ffA500; -fx-text-fill: #000000;");
    }

    @FXML
    void prevClicked(ActionEvent event) {
        refresh(--index);
    }

    @FXML
    void prevEnter(MouseEvent event) {
        prevButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void prevExit(MouseEvent event) {
        prevButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ffA500; -fx-text-fill: #000000;");
    }

    @FXML
    void sendClicked(ActionEvent event) {
        String userIn = userInput.getText();
        if (chatInstance.talkCount == 0)
            chatArea.setText(chatArea.getText() + chatInstance.chatPerson + ": "
                    + chatInstance.getResponse("Who are you?"));
        else {
            chatArea.setText(chatArea.getText() + "\nYou: " + userIn);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chatArea.setText(chatArea.getText() + "\n" + chatInstance.chatPerson + ": "
                    + chatInstance.getResponse(userIn));
        }
        userInput.setText("");
    }

    @FXML
    void sendEnter(MouseEvent event) {
        sendButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void sendExit(MouseEvent event) {
        sendButton.setStyle("-fx-background-radius: 10; -fx-background-color: #ff5C00; -fx-text-fill: #000000;");
    }

    @FXML
    void viewCommentClicked(ActionEvent event) {
        try {
            CommentViewer c = (CommentViewer) w.newWindow("CommentViewer.fxml")[0];
            postRead = w.readRepoFile(currentPostID + ".post");
            String commentRead[] = postRead.split("-----");
            for (int i = 0, count = 1; i < commentRead.length; i++) {
                if (!commentRead[i].isEmpty() && commentRead[i].contains("Comment: ")) {
                    String comment = commentRead[i].replace("Comment: ", "");
                    Label label = new Label(comment.trim().replace("\n", "\n\n"));
                    label.setAlignment(Pos.TOP_LEFT);
                    label.setLayoutX(10.0);
                    label.setLayoutY(3.0 + count * 10);
                    label.setPrefWidth(540.0);
                    label.setPrefHeight(95.0);
                    label.setStyle("-fx-background-color: #666666; -fx-background-radius: 10px;");
                    label.setFont(new Font("Bookman Old Style", 16));
                    label.setTextFill(Color.web("#ffffff"));
                    c.mainAnchor.getChildren().add(label);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewCommentEnter(MouseEvent event) {
        viewComment.setStyle("-fx-background-radius: 10; -fx-background-color: #ff9100; -fx-text-fill: #ffffff;");
    }

    @FXML
    void viewCommentExit(MouseEvent event) {
        viewComment.setStyle("-fx-background-radius: 10; -fx-background-color: #ffa500; -fx-text-fill: #000000;");
    }

    public void refresh(int index) {
        postLinks.clear();
        for (String s : w.readRepoFile("postsMade.txt").split("\n"))
            postLinks.add(s + ".post");

        postLinks.remove(currentPostID + ".post");

        if (postLinks.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("BIG ISSUE!");
            alert.setContentText("There are no posts!");
            alert.showAndWait();
            return;
        }

        index = Math.abs(index % postLinks.size());
        postRead = w.readRepoFile(postLinks.get(index));
        String post[] = postRead.split("\n");
        currentPostID = postLinks.get(index);
        postID.setFont(new Font("Arial", 14));
        postID.setText(currentPostID.replace(".post", ""));

        postContent.setText("");
        posterName.setText("");
        postType.setText("");

        for (String s : post) {
            if (s.contains("Poster: "))
                posterName.setText(s.replace("Poster: ", ""));
            else if (s.contains("Title: "))
                postContent.setText(s);
            else if (s.contains("Type: "))
                postType.setText(s);
            else if (s.contains("Content: ") || s.contains("Description: "))
                postContent.setText(postContent.getText() + "\n" + s.replace("Content: ", "Description: "));
        }
        if (postRead.contains("Claimed: ")) {
            claim.setDisable(true);
            postContent.setText("[THIS HAS BEEN CLAIMED]\n" + postContent.getText());
        } else
            claim.setDisable(false);
    }
}
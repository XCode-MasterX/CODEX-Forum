import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

// THIS THE CLASS WHERE IT ALL STARTS.
public class App extends Application {
    private winurl w = winurl.getInstance();

    @Override
    public void start(Stage primaryStage) {
        try {
            Object o[] = w.newWindow("loginScreen.fxml");
            LoginScreen l = (LoginScreen) o[0];
            l.stage = (Stage) o[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
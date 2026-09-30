import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmailForm extends Application {

  @Override
  public void start(Stage stage) {

    UserRegistrationPane registrationPane = new UserRegistrationPane();

    Scene scene = new Scene(registrationPane, 400, 300);


    stage.setTitle("Email Form");
    stage.setScene(scene);
    stage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}


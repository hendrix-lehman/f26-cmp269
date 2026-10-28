import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WelcomeApp extends Application {

  @Override
  public void start(Stage primaryStage) {
    Label titleLabel = new Label("Welcome to CMP 269!");
    Button actionButton = new Button("Click Me!");

    // event handler for the button
    actionButton.setOnAction(e -> {
      System.out.println("Button was clicked!" + e.getSource());
      titleLabel.setText("Button Clicked!");
    });

    HBox root = new HBox(15, titleLabel, actionButton);
    root.setStyle("-fx-padding: 20; -fx-alignment: center;");

    Scene scene = new Scene(root, 350, 200);
    primaryStage.setTitle("JavaFX Demo");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}


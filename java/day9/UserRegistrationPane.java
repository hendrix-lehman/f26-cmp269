import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Slider;

public class UserRegistrationPane extends GridPane {                      
  private final TextField nameInput = new TextField();                  
  private final TextField emailInput = new TextField();                 
  private final Label statusLabel = new Label();                        
                                                                        
  public UserRegistrationPane() {                                       
    setHgap(10);                                                      
    setVgap(10);                                                      
    setStyle("-fx-padding: 20;");                                     
                                                                      
    addRow(0, new Label("Full Name:"), nameInput);                    
    addRow(1, new Label("Email Address:"), emailInput);               
                                                                      
    Button submitBtn = new Button("Register");                        
    submitBtn.setOnAction(e -> handleRegistration());                 
    submitBtn.disableProperty().bind(nameInput.textProperty().isEmpty());
    submitBtn.disableProperty().bind(emailInput.textProperty().isEmpty());

    addRow(2, submitBtn);                                             
    addRow(3, statusLabel);                                           

    Slider volumeSlider = new Slider(0, 100, 50);
    Label volumeLabel = new Label();

    volumeLabel.textProperty().bind(volumeSlider.valueProperty().asString("Volume: %.0f%%"));

    addRow(4, volumeSlider);
    addRow(5, volumeLabel);
  }                                                                     

  private void handleRegistration() {                                   
    String email = emailInput.getText().trim();                       
    if (!email.contains("@") || !email.contains(".")) {               
      statusLabel.setText("Invalid email format!");                 
      statusLabel.setStyle("-fx-text-fill: red;");                  
      return;                                                       
    }                                                                 
    statusLabel.setText("Registered successfully: " + nameInput.getText());                                                       
    statusLabel.setStyle("-fx-text-fill: green;");                    
  }                                                                     
} 

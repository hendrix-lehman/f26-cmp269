import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
            addRow(2, submitBtn);                                             
            addRow(3, statusLabel);                                           
        }                                                                     
                                                                              
        private void handleRegistration() {                                   
            String email = emailInput.getText().trim();                       
            if (!email.contains("@") || !email.contains(".")) {               
                statusLabel.setText("Invalid email format!");                 
                statusLabel.setStyle("-fx-text-fill: red;");                  
                return;                                                       
            }                                                                 
            statusLabel.setText("Registered successfully: " +                 
  nameInput.getText());                                                       
            statusLabel.setStyle("-fx-text-fill: green;");                    
        }                                                                     
    }                                          

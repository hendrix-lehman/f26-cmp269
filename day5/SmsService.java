public class SmsService implements NotificationService {                  
  @Override 
  public void sendNotification(String msg) {                  
    System.out.println("Texting SMS: " + msg);                        
  }                                                                     
} 

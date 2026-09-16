import java.util.ArrayList;
import java.util.List;

public class AlertDispatcher {                                            
  private final List<NotificationService> services = new ArrayList<>(); 
                                                                        
  public void registerService(NotificationService service) {            
    services.add(service);                                            
  }                                                                     
                                                                        
  public void broadcast(String alertMessage) {                          
    for (NotificationService service : services) {                    
      service.sendNotification(alertMessage); // Polymorphic dispatch                                                                    
    }                                                                 
  }                                                                     
}   

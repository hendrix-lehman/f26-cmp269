class App {

//   public static void sendNotification(NotificationService service, String message) {
//     service.sendNotification(message);
//   }


  public static void main(String[] args) {

    // create dog and fish
    Dog dog = new Dog("Buddy");
    Fish fish = new Fish("Goldie");

    // demonstrate polymorphism
    dog.makeNoise();
    fish.makeNoise();

    Swimmable[] swimmers = { dog, fish };
    for (Swimmable swimmer : swimmers) {
      swimmer.swim();
    }

    dog.playWithToy();


    // create email service and send notification

    NotificationService emailService = new EmailService();
    NotificationService smsService = new SmsService();
    AlertDispatcher dispatcher = new AlertDispatcher();
    dispatcher.registerService(emailService);
    dispatcher.registerService(smsService);
    dispatcher.broadcast("This is a test alert!");

    // sendNotification(emailService, "Hello via Email!");
    // sendNotification(smsService, "Hello via SMS!");
  }
}

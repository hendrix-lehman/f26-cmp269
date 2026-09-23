class App {

  // public static void inspectDataStore(DataStore store) {
  //   if (store instanceof InMemoryDataStore mem) {
  //     System.out.println("InMemoryDataStore instance");
  //   } else if (store instanceof CloudDataStore cloud) {
  //     System.out.println("CloudDataStore instance");
  //   // } else if (store instanceof FileDataStore file) {
  //     // System.out.println("FileDataStore instance");
  //   } else {
  //     System.out.println("Unknown DataStore instance");
  //   } 
  // }

  public static void main(String[] args) {

    try {
      DataStore store = DataStore.getDataStore(DataStore.DEFAULT_DATASTORE);
      store.save("key1", "value1");

      String value = store.load("key1");
      System.out.println("Loaded value: " + value);

      boolean exists = store.exists("key1");
      System.out.println("Key exists: " + exists);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }


  }
}

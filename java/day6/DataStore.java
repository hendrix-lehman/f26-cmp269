// add package name here if needed
//
public interface DataStore {
  String DEFAULT_HOST = "localhost";
  String DEFAULT_DATASTORE = "InMemoryDataStore";
  String CLOUD_DATASTORE = "CloudDataStore";
  String FILE_DATASTORE = "FileDataStore";

  void save(String key, String value);
  String load(String key);

  default boolean exists(String key) {
    return load(key) != null;
  }

  static DataStore getDataStore(String type) throws NotImplementedException {
    switch (type) {
      case CLOUD_DATASTORE:
        return new CloudDataStore(DEFAULT_HOST);
      case FILE_DATASTORE:
        // return new FileDataStore("data.txt");
        throw new NotImplementedException("This datastore is not implemented yet");
      case DEFAULT_DATASTORE:
      default:
        return new InMemoryDataStore();
    }
  }

  // static DataStore getDefaultDataStore() {
    // return new InMemoryDataStore();
  // }

//   static DataStore getCloudDataStore() {
//     return new CloudDataStore(DEFAULT_HOST);
//   }

//   static DataStore getFileDataStore() {
//     return new FileDataStore("data.txt");
//   }
}


import java.util.HashMap;
import java.util.Map;

// implementing the DataStore
public class InMemoryDataStore implements DataStore {

  // concrete in-memory storage
  private final Map<String, String> memoryMap = new HashMap<>();

  @Override
  public void save(String key, String value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException("Key and value cannot be null");
    }
    memoryMap.put(key, value);
  }

  @Override
  public String load(String key) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    return memoryMap.get(key);
  }
}

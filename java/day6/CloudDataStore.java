public class CloudDataStore implements DataStore {
    private String cloudEndpoint;

    public CloudDataStore(String cloudEndpoint) {
        this.cloudEndpoint = cloudEndpoint;
    }

    @Override
    public void save(String key, String data) {
        // Logic to save data to the cloud
        System.out.println("Saving data to cloud at " + cloudEndpoint + ": " + data);
    }

    @Override
    public String load(String key) {
        // Logic to load data from the cloud
        System.out.println("Loading data from cloud at " + cloudEndpoint);
        return "Data from cloud";
    }
}

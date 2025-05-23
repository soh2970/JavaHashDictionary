public class Data {
    private String config;
    private int score;

    //constructor to initialize config and score as new Data object.
    public Data(String config, int score) {
        this.config = config;
        this.score = score;
    }

    // Returns the configuration stored in this Data object.
    public String getConfiguration() {
        return config;
    }

    //Returns the score in this Data.
    public int getScore() {
        return score;
    }
}

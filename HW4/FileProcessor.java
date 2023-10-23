import java.io.FileReader;
import java.util.HashSet;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class FileProcessor {
    public static JSONArray openTweetsFile(String filename) {
        JSONArray tweetArray = null;
        if (filename == null){
            System.out.println("Please provide a file name for the tweets file");
            return null; 
        }
		try {
            Object obj = new JSONParser().parse(new FileReader(filename));
			tweetArray = (JSONArray) obj;
		}
        catch (Exception e){
            return null;
        }
        return tweetArray;
    }

    public static HashSet<Tweet> compileTweetsToSet(JSONArray tweetArray) {
        HashSet<Tweet> tweetSet = new HashSet<>();
        for (Object item : tweetArray) {
            JSONObject JSONItem = (JSONObject) item;
            try {
                JSONArray locationJsonArray = (JSONArray) JSONItem.get("location");
                double[] locationArray = new double[2];
                for (int i = 0; i < 2; i++){
                    locationArray[i] = (Double) locationJsonArray.get(i);
                }
                Tweet tweet = new Tweet(locationArray, (String) JSONItem.get("text"));
                tweetSet.add(tweet);
            }
            catch (Exception e){
                return null;
            }            
        }
        return tweetSet;
    }

    public static JSONArray openStatesFile(String filename) {
        JSONArray statesArray = null;
        if (filename == null){
            System.out.println("Please provide a file name for the states file");
            return null; 
        }
		try {
            Object obj = new JSONParser().parse(new FileReader(filename));
			statesArray = (JSONArray) obj;
		}
        catch (Exception e){
            System.out.println("Error reading file");
            return null;
        }
        return statesArray;
    }

public static HashSet<State> compileStatesToSet(JSONArray statesArray) {
        HashSet<State> statesSet = new HashSet<>();
        for (Object item : statesArray) {
            try {
                JSONObject JSONItem = (JSONObject) item;
                double[] locationArray = {(double) JSONItem.get("latitude"), (double) JSONItem.get("longitude")};
                State state = new State(locationArray, (String) JSONItem.get("name"));
                statesSet.add(state);
            }
            catch (Exception e){
                return null;
            }            
        }
        return statesSet;
    }
}
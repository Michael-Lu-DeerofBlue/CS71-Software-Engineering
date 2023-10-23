import java.io.FileWriter;
import java.util.HashSet;
import java.util.TreeMap;

public class Analyzer{
    public static TreeMap<String, Integer> tweetAnalyze(HashSet<Tweet> tweetSet, HashSet<State> statesSet, FileWriter writer){
        TreeMap<String, Integer> stateFrequency = new TreeMap<>();
        for (Tweet tweet : tweetSet) {
            if (tweetFilter(tweet.getText())){
                String state = tweetDistanceCalculator(tweet.getLocation(), statesSet);
                Logger.write(writer, state + "\t\t" + tweet.getText());
                if (!stateFrequency.containsKey(state)){
                    stateFrequency.put(state, 1);
                }
                else{
                    int counter = stateFrequency.get(state);
                    counter = counter + 1;
                    stateFrequency.put(state, counter);
                }
            }
        }
        return stateFrequency;
    }

    public static boolean tweetFilter(String text){
        String[] words = text.split(" ");
        for (String word : words){        
            word = word.toLowerCase();
            if (word.equals("#flu")){
                return true;
            }
            else if (word.equals("flu")){
                return true;
            }
            else if (word.length() > 3){
                if (word.charAt(0) == 'f' && word.charAt(1) == 'l' 
                && word.charAt(2) == 'u' &&  !Character.isLetter(word.charAt(3))){
                    return true;
                }
            }            
        }
        return false;
    }

    public static String tweetDistanceCalculator(double[] tweetLocation, HashSet<State> statesSet){
        double tweetLatitude = tweetLocation[0];
        double tweetLongitude = tweetLocation[1];
        double stateLatitude = 0;
        double stateLongitude = 0;
        double distance = 0;
        TreeMap<Double, String> distanceToState = new TreeMap<>();
        for (State state : statesSet) {
            stateLatitude = state.getLocation()[0];
            stateLongitude = state.getLocation()[1];
            distance = Math.sqrt((tweetLatitude-stateLatitude) * (tweetLatitude-stateLatitude) + (tweetLongitude-stateLongitude) * (tweetLongitude-stateLongitude));
            distanceToState.put(distance, state.getName());
        }
        return distanceToState.get(distanceToState.firstKey());
    }
}
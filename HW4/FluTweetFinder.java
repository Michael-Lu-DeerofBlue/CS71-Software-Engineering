import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * This program attempts to use the JSON-Simple library to read and parse
 * a String containing JSON.
 */

public class FluTweetFinder {
	
	public static void main(String[] args) {
        //Opening the config
        String dataFile = null;
        String statesFile = null;
        String logFileName = null;
        if (args.length != 1){
            System.out.println("Please provide a name for the configuration file");
            return;
        }		
		try {
            Object obj = new JSONParser().parse(new FileReader(args[0]));
			JSONObject data = (JSONObject) obj;
            if (!data.containsKey("datafile") || !data.containsKey("statesfile") || !data.containsKey("logfile")){
                System.out.println("The configuration file is missing one of the required properties (“datafile”, “statesfile”, or “logfile”). Please check the configuration file.");
                return;
            }
            dataFile = (String)data.get("datafile");
			statesFile = (String)data.get("statesfile");
            logFileName = (String)data.get("logfile"); 
		}
		catch (FileNotFoundException e) {
			System.out.println("The configuration file is not found. Please provide a correct name.");
            return;
		}
        catch (IOException e){
            System.out.println("An error occurs when reading the configuration file. Please check the file name and content.");
            return;
        }
        catch (ParseException e){
            System.out.println("An error occurs when reading the configuration file. The file might be malformated. Please check the file content.");
            return;
        }
        try{
            File logFile = new File(logFileName);
            if (!logFile.exists()) {
                System.out.println("New");
                logFile.createNewFile();
            }
            FileWriter writer = new FileWriter(logFile,false);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Logger.write(writer, "Started logging at " + currentDateTime + "\n");
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred while creating or writing the log file.");
            return;
        }

        //Reading Files
        JSONArray tweetsData;
        tweetsData = FileProcessor.openTweetsFile(dataFile);
        if (tweetsData == null){
            System.out.println("Error reading file");
            return;
        }
        HashSet<Tweet> tweetSet;
        tweetSet = FileProcessor.compileTweetsToSet(tweetsData);
        if (tweetSet == null){
            System.out.println("Error reading file");
            return;
        }
        JSONArray statesData;
        statesData = FileProcessor.openStatesFile(statesFile);
        if (statesData == null){
            System.out.println("Error reading file");
            return;
        }
        HashSet<State> statesSet;
        statesSet = FileProcessor.compileStatesToSet(statesData);
        if (statesSet == null){
            System.out.println("Error reading file");
            return;
        }

        //Analyze and Log Data
        TreeMap<String, Integer> results = new TreeMap<>();
        try{
            File logFile = new File(logFileName);
            FileWriter writer = new FileWriter(logFile, true);
            results = Analyzer.tweetAnalyze(tweetSet, statesSet, writer);
            writer.close();
        }
         catch (IOException e){
            System.out.println("An error occurred while creating or writing the log file.");
            return;
        }

        //Outputing the results
        if (!results.isEmpty()){
            for (Map.Entry<String, Integer> entry : results.entrySet()){
                String state = entry.getKey();
                Integer frequency = entry.getValue();
                System.out.println(state + ": " + frequency);
            }
        }
        else{
            System.out.println("There is no flu tweets in any of the 50 states.");
        }
        return;
	}
}
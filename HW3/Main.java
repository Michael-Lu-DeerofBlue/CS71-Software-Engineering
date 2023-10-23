/** 
 * @author [YOUR NAME HERE!]
 *
 * This class contains the main method for the sentiment analysis program.
 */
import java.util.Set;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
public class Main {
	
	
	public static void main(String[] args) {
		/*
		 * Implement this method in Part 4
		 */
		if (args.length != 1){
			System.out.println("No input file");
			return;
		}
		Set<Sentence> sentenceSet = new HashSet<>();
		try {
			sentenceSet = Reader.readFile(args[0]);
		}
		catch(IllegalArgumentException e){
			System.out.println("Bad input file");
			return;
		}
		Map<String, Double> wordScores = new HashMap<>();
		try {
			wordScores = Analyzer.calculateWordScores(sentenceSet);
		}
		catch(IllegalArgumentException e){
			System.out.println("Bad input Set of Sentences");
			return;
		}

		
		Scanner in = new Scanner(System.in);
		Map<String, Double> results = new HashMap<String,Double>();
		while (true){
			System.out.print("Enter a sentence to calculate for: ");
			String input = in.nextLine();
			double result = 0.0;
			if (input.equals("quit")){
				break;
			}
			try {
				result = Analyzer.calculateSentenceScore(results, wordScores, input);
			} catch (IllegalArgumentException e) {
				System.out.println("Bad input of the sentence or bad input of map of words");
			}				
			System.out.println("The score is: " +result);
		}

		in.close();
		
	}

}

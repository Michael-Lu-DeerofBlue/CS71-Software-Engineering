/** 
 * @author [YOUR NAME HERE!]
 *
 * This class contains the methods used for conducting a simple sentiment analysis.
 */

import java.util.*;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average
	 * @throws IllegalArgumentException if sentences is null
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		/*
		 * Implement this method in Part 2
		 */
		Map<String, Double> wordScores = new HashMap<>();
		Map<String, Integer> wordFrequency = new HashMap<>();
		double score;
		String[] words;
		if (sentences == null){
			throw new IllegalArgumentException("The input Set of Sentences is null");
		}		
		for (Sentence lilSentence : sentences){
			score = (double) lilSentence.getScore();
			words = lilSentence.getText().split("\\s");
			for (String word : words){
				if (!word.isEmpty()){ // is not empty
					word = word.toLowerCase();
					if (Character.isLetter(word.charAt(0))){ // is a letter
						if (!wordFrequency.containsKey(word)){
							wordFrequency.put(word, 1);
							wordScores.put(word, score);
						}
						else{		
							int frequencyValue;			
							frequencyValue = wordFrequency.get(word);
							double scoreValue;	
							scoreValue = wordScores.get(word);
							frequencyValue++;
							scoreValue = (scoreValue * frequencyValue + score) / frequencyValue;
							wordFrequency.put(word, frequencyValue);
							wordScores.put(word, scoreValue);
						}
					}			
				}					
			}
			
		}
		return wordScores;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence
	 * @throws IllegalArgumentException if wordScores is null or sentence is null
	 */
	
	public static double calculateSentenceScore(Map<String, Double> results, Map<String, Double> wordScores, String sentence) {
		sentence = sentence.toLowerCase();
		if (results.containsKey(sentence)){
			return results.get(sentence);			
		}
		else{
			double result = calculate(wordScores, sentence);
			results.put(sentence, result);
			return result;
		}
	}

	private static double calculate(Map<String, Double> wordScores, String sentence) {
		/*
		 * Implement this method in Part 3
		 */
		
		if (sentence == null){
			throw new IllegalArgumentException("The input sentences is null");
		}
		if (wordScores == null){
			throw new IllegalArgumentException("The input Map of Words is null");
		}
		if (sentence.isEmpty()){
			return 0;
		}

		double score = 0;
		String[] words;
		words = sentence.split("\\s");
		for (String word : words){
			if (!word.isEmpty()){ // is not empty
					if (Character.isLetter(word.charAt(0))){
						if (wordScores.containsKey(word)){
							score = score + wordScores.get(word);
						}
					}
			}
		}
		return score;
	}

	

}

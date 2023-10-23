/** 
 * @author [YOUR NAME HERE!]
 *
 * This class contains a method for reading from a file and creating Sentence objects
 * for a sentiment analysis program.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		/*
		 * Implement this method in Part 1
		 */
		int score = 0;
		String line;
		Set<Sentence> set = new HashSet<>();
		if (filename == null){
			throw new IllegalArgumentException("Input List is null");
		}
		try (Scanner in = new Scanner(new File(filename))){
			while (in.hasNext()) {
				try {
					score = Integer.parseInt(in.next());
					line = in.nextLine();
					if (!line.isEmpty() && score >= -2 && score <= 2){ //error 1,4
						Sentence sentence = new Sentence(score, line);
						set.add(sentence);
					}					
				} catch (NumberFormatException e){ //error 2,3
					in.nextLine();
				}
				catch (NoSuchElementException e){
					continue;
				}
			}			
		}
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("The file cannot be opened for reading.");
		}
		return set;
	}

}



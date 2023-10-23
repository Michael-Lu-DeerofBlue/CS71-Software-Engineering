import java.io.*;
import java.util.*;

public class WordSearchSoln {
	
	public static Map<String, Set<String>> buildMap(String dirName) {
		File dir = new File(dirName);	// create a File object for this directory
		
		// make sure it exists and is actually a directory
		if (dir.exists() == false || dir.isDirectory() == false) {
			throw new IllegalArgumentException(dirName + " does not exist or is not a directory");
		}
		
		File[] files = dir.listFiles();		// get the Files in the specified directory
				
		// SOLUTION STARTS HERE
		
		// need to create a map that is the return value
		Map<String, Set<String>> map = new HashMap<>();
		
		// iterate over the files in the directory
		for (File f : files) {

			// create a Scanner for that file
			try (Scanner in = new Scanner(f)) {
				
				// keep reading while there's more to read
				while (in.hasNext()) {
					
					// read a String/word and convert it to lowercase
					String word = in.next().toLowerCase(); 

					// see if there's already a mapping for this word
					if (map.containsKey(word) == false) {
						// if not, create a new Set for this word
						// we're using a TreeSet to keep the file names in alphabetical order
						Set<String> set = new TreeSet<>();
						// add the file name to this Set
						set.add(f.getName());
						// now map the word to this Set
						map.put(word, set);
					}
					else {
						// if the map already has a key for this word,
						// then we can just get the Set from the Map
						Set<String> set = map.get(word);
						// now add the file name to this Set
						set.add(f.getName());
						// strictly speaking, we don't need to put the Set back in the Map
						// since it's already there and we just updated it
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// return the Map that we created
		return map; 
		
	}
	
	public static List<String> search(String[] terms, Map<String, Set<String>> map) {
		/*
		 * Note: there are many ways to do this, and this is just one solution
		 * that I think is easy to understand. There are definitely more efficient
		 * ways to solve this and you should think about those, but of course we
		 * at least want to get it to work first!
		 */
		
		// this will keep track of how many of the terms appear in each file
		Map<String, Integer> count = new TreeMap<>();
		
		// iterate over the terms
		for (String term : terms) {
			// get its Set from the Map, i.e. the names of the files in which it appears
			Set<String> files = map.get(term);
			// now iterate over all the files that it appears in
			for (String file : files) {
				// see if we already have a mapping for the number of terms it includes
				if (count.containsKey(file) == false) {
					// if we don't already have it in the map, then initialize it to a count of 1 
					count.put(file, 1);
				}
				else {
					// otherwise, get the value from the map
					int value = count.get(file);
					// increment it
					value++;
					// and then put it back in the map
					count.put(file, value);
				}
			}
		}
		
		// At this point, the Map tells us how many of the words appear in each file
		//System.out.println(count);
		
		
		/*
		 * The code below is O(n^2) because it does an O(n) iteration over the keys
		 * in the Map to find the one with the largest corresponding value, and does
		 * this n times. There are O(n log n) solutions -- I think?? -- but this 
		 * at least works!
		 */
		
		// List of our final results
		List<String> list = new LinkedList<>();
		
		// keep track of the number of elements in the Map
		int n = count.size();
		// we will do this n times, i.e. once per element in the Map
		for (int i = 0; i < n; i++) {
			// this will keep track of the max value seen so far
			int max = 0;
			// this is the key that has the max value
			String maxFile = null;
			// loop over all the entries in the Map's Set of keys
			for (String file : count.keySet()) {
				// update max if the corresponding value is greater 
				if (count.get(file) > max) {
					max = count.get(file);
					maxFile = file;
				}
			}
			// add this file name to the end of the list
			list.add(maxFile);
			// now remove this from the Map, so that we don't double-count it
			count.remove(maxFile);
		}

		return list;
	}
	
	public static void main(String[] args) {
		Map<String, Set<String>> map = buildMap(args[0]);
		//System.out.println(map);
		
		System.out.print("Enter a term to search for: ");
		
		try (Scanner in = new Scanner(System.in)) { // create a Scanner to read from stdin
			String input = in.nextLine();			// read the entire line that was entered
			String[] terms = input.split(" ");		// separate tokens based on a single whitespace
			List<String> list = search(terms, map);	// search for the tokens in the Map
			for (String file : list) {				// print the results
				System.out.println(file);
			}
		}
		catch (Exception e) {
			// oops! something went wrong
			e.printStackTrace();
		}
	}

}

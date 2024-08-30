import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CalculateSentenceScoreTest {


	@Test
	public void testNormalCorrect() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = "dogs are cute";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
		assertEquals(2, result, 0.1);
	}
	
	@Test
	public void testIngoreWord() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = "dogs are ?cute";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
		assertEquals(1.5, result, 0.1);
	}
	
	@Test
	public void testMissingWordInMap() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = "dogs are cute too";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
		assertEquals(1.5, result, 0.1);
	}
	
	@Test
	public void testCapLetters() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = "dOgs ARE cuTE";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
		assertEquals(2, result, 0.1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInputMap() {
		Map<String, Double> inputMap = null;
		String sentence = "dogs are cute";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInputSentence() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = null;
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
	}
	
	@Test
	public void testEmptyInputSentence() {
		Map<String, Double> inputMap = new HashMap<>();
		inputMap.put("dogs", 2.0);
		inputMap.put("are", 1.0);
		inputMap.put("cute", 3.0);
		String sentence = "";
		Map<String, Double> emptyMemoizationMap = new HashMap<>();
		Double result = Analyzer.calculateSentenceScore(emptyMemoizationMap, inputMap, sentence);
		assertEquals(0, result, 0.1);
	}

}

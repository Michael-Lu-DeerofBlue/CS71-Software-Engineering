import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CalculateWordScoresTest {

	//Ask Chris do we need to do a control flow graph for this one to exhaust all the cases?
	@Test
	public void testSingleSentence() {
		Sentence sentence_1 = new Sentence(2, "eat cake cake");
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		assertEquals(2, result.size());
		assertEquals(2, result.get("eat"), 0.0001);
		assertEquals(2, result.get("cake"), 0.001);
	}
	
	@Test
	public void testMultipleSentence() {
		Sentence sentence_1 = new Sentence(2, "eat cake cake");
		Sentence sentence_2 = new Sentence(1, "dog eat cake");
		Set<Sentence> sentenceSet = Set.of(sentence_1, sentence_2);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		assertEquals(3, result.size());
		assertEquals(1.0, result.get("dog"), 0.0001);
		assertEquals(1.5, result.get("eat"), 0.0001);
		assertEquals(1.667, result.get("cake"), 0.001);
	}
	
	@Test
	public void testTokenStartWithoutLetter() {
		Sentence sentence_1 = new Sentence(1, "eat ?cake cake");
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		assertEquals(2, result.size());
		assertEquals(1, result.get("eat"), 0.0001);
		assertEquals(1, result.get("cake"), 0.001);
	}
	
	@Test
	public void testTokenLowerCase() {
		Sentence sentence_1 = new Sentence(1, "Eat cAke CAKE");
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		assertEquals(2, result.size());
		assertEquals(1, result.get("eat"), 0.0001);
		assertEquals(1, result.get("cake"), 0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullSentenceSet() {
		Set<Sentence> sentenceSet = null;
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
	}
	
	@Test
	public void testEmptySentenceSet() {
		Set<Sentence> sentenceSet = Collections.<Sentence>emptySet();
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		Map<String, Double> expected = Collections.<String, Double>emptyMap();
		assertEquals(expected, result);
	}
		
	@Test
	public void testCalculateWordScoresEmptySentence() {
		Sentence sentence_1 = new Sentence(1, "");
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		Map<String, Double> expected = Collections.<String, Double>emptyMap();
		assertEquals(expected, result);
	}
	
	@Test
	public void testCalculateWordScoresNullSentence() {
		Sentence sentence_1 = new Sentence(0, null);
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		Map<String, Double> expected = Collections.<String, Double>emptyMap();
		assertEquals(expected, result);
	}
	
	@Test
	public void testCalculateWordScoresSocreOutOfBounds() {
		Sentence sentence_1 = new Sentence(-5, "cake cake eat");
		Set<Sentence> sentenceSet = Set.of(sentence_1);
		Map<String, Double> result = Analyzer.calculateWordScores(sentenceSet);
		Map<String, Double> expected = Collections.<String, Double>emptyMap();
		assertEquals(expected, result);
	}
	
}

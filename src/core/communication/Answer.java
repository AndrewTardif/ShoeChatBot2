package core.communication;

import java.util.Arrays;

/**
 * 
 * Immutable Answer object that holds an answer and its corresponding keys.
 * @author Raghav
 *
 */
public class Answer {
	
	private String answer;
	private String[] keys;
	
	public Answer(String answer, String[] keys) {
		this.answer = answer;
		this.keys = keys;
	}

	public String getAnswerString() {
		return answer;
	}

	public String[] getKeys() {
		return keys;
	}
	
	@Override
	public String toString() {
		return "Keys: " + Arrays.toString(getKeys()) + ", Answer: " + answer;
	}
}

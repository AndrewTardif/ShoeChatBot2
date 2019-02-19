package core.communication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import core.app.ChatSession;
import core.app.Executor;
import core.communication.YesNoEvaluator.YesNoResponse;
import core.ui.UI;

/**
 * 
 * This class takes in a question and generates an answer based on a local txt
 * file responses.txt
 * 
 * @author Dhruv, Raghav
 *
 */
public class AnswerGenerator {

	private static ArrayList<Answer> answers;

	/**
	 * Loads the question/answer data from responses.txt into memory.
	 */
	public static void loadAnswers() {

		answers = new ArrayList<Answer>();
		final File f = new File("responses.txt");

		try (BufferedReader in = new BufferedReader(new FileReader(f))) {

			String inputLine;
			while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
				// Split the keys into one part of the array and answer into
				// the other part (key;key;key|answer) = ["key;key;key;key;","answer"]
				String[] splitKeysAndAnswer = inputLine.split("\\|");
				System.out.println(Arrays.toString(splitKeysAndAnswer));
				// Get the keys from the array (key;key;key...) = ["key","key","key"...]
				String[] splitKeys = splitKeysAndAnswer[0].split(";");
				// Put into arrayList
				answers.add(new Answer(splitKeysAndAnswer[1], splitKeys));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("err");
		}

	}

	/**
	 * @param question
	 *            The question asked by the user
	 * @return Returns the most appropriate answer based on the analysis of the
	 *         question.
	 */
	public static String evalQuestion(String question) {
		
		question = filterQuestion(question);
		System.out.println(ChatSession.chatStage);
		if(ChatSession.chatStage > 0) {
			YesNoResponse response = YesNoEvaluator.evaluateMessage(question);
			switch(ChatSession.chatStage) {
			case 1:			
				return ChatSession.setSelectedShoe(question);
			case 2:
				if(response == YesNoResponse.yes) {
					return ChatSession.checkout();
				} else if(response == YesNoResponse.no){
					ChatSession.chatStage = 0;
					return "Sorry you didn't like our shoe. Would you like to pick another type?";
				} else {
					return "Sorry I didn't understand your response. Please try again!";
				}
			case 3:
				if(response == YesNoResponse.yes) {
					ChatSession.chatStage = 0;
					return "What type are you looking for?";
				} else if(response == YesNoResponse.no){
					return "Thank you for buying from us! See you later!";
				} else {
					return "Sorry I didn't understand your response. Please try again!";
				}
			}
		}
		//This array will hold the % matches for each answer
		//e.g. [0.01,0.55,0.8,0.65,0.22,1] in this case, 1 (100%) is the highest number, and also the best answer
		//Important: matches.length ALWAYS equals answers.size()
		double matches[] = new double[answers.size()];
		
		for(int i = 0; i < matches.length; i++) {
			matches[i] = calculateSimilarity(question, answers.get(i).getKeys());
		}

		double max = 0.0; int maxindex = 0;

		for (int i = 0; i < matches.length; i++) {
			// System.out.println(matches[i]);
			if (matches[i] > max) {
				max = matches[i];
				maxindex = i;
			}

		}
		
		String reply;
		if(max == 0.0) {
			reply = "I'm not sure what you meant. Try \"I want to buy running shoes\"";
		} else {
			reply = answers.get(maxindex).getAnswerString();
		}
		// System.out.println(maxindex);
		return Executor.execute(reply, question).replaceAll("<br>", "\n");
	}
	
	
	/**
	 * @param question The question being asked
	 * @param b the answer keys to compare the question with.
	 * @return a % value of how similar the question & keys are.
	 */
	public static double calculateSimilarity(String question, String[] keys) {
		
		double similarity = 0;
		
		String[] a = question.split(" ");
		String[] b = keys;
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < b.length; j++) {
				String[] phrase = b[j].split(" ");
				//check if its a key phrase or a key word
				if(phrase.length == 0)  {
					//If b[j] is in a[i]
					if(a[i].contains(b[j])) {
						//Add a double based on how much of a[i] is taken up by b[j]
						//If they are equal, it will result in 1.
						double wordSimilarity = b[j].length() / (double)a[i].length();
						//Add similarity in proportion to the number of words in the question's length.
						similarity += wordSimilarity/(double)a.length;
					}					
				} else {
					if(question.contains(b[j])) {
						if(question.equals(b[j])) {
							//Exact question, return 1 RIGHT AWAY.
							return 1;
						} else {
							//Add similarity based on the length of the question's length
							similarity += b[j].length() / (double)question.length();
						}
					}
				}
			}
		}
		System.out.println("Confidence: " + similarity / (a.length * b.length));
		return similarity / (a.length * b.length);
	}

	/**
	 * @param question
	 *            The question asked by the user
	 * @return Returns a fully filtered string without special chars and in lower
	 *         case.
	 */
	private static String filterQuestion(String question) {
		return question.replaceAll("[\\?\\!\\.@#\\$%\\^&\\*]", "").toLowerCase();
	}
}

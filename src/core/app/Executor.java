package core.app;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.ui.UI;

/**
 * 
 * This allows running functions within the program from the text file. If the
 * response consists of information that is retrieved from a method in the
 * program you can simply do "print(myMethod("myParam"))" and it will be
 * replaced with the correct result. A maxmimum of one param for now.
 * 
 * @author Raghav
 *
 */
public class Executor {
	/**
	 * @param answer
	 *            the answer being evaluated
	 * @return the answer with the methods replaced with their corresponding values
	 */
	public static String execute(String answer, String question) {
		Pattern printFunctions = Pattern.compile("(?<=(print\\())(.*)(?=(\\)))");// Pattern.compile("(?<=print\\()(.*)(?=\\))");
		Matcher m = printFunctions.matcher(answer);

		// A hashmap of all the functions to replace in the answer string
		HashMap<String, String> toReplace = new HashMap<>();
		// Find all the functions and evaluate them.
		while (m.find()) {
			String found = (m.group(1) + m.group(2) + m.group(3));
			// Put into hashmap for later
			toReplace.put(found, evalFunc(m.group(), question));
		}

		// Replace all the functions with their values.
		for (String key : toReplace.keySet()) {
			answer = answer.replace(key, toReplace.get(key));
		}

		return answer;
	}

	/**
	 * @param func
	 *            evaluates a methods. More functions added as time goes.
	 * @return returns the value produced by the method.
	 */
	public static String evalFunc(String func, String query) {
		if (func.startsWith("getShoesFromSearchQuery")) {
			try {
				if (func.contains("#response#")) {
					return ShoeAPI.getShoesFromSearchQuery(query);
				}
				Pattern getQuery = Pattern.compile("(?<=getShoesFromSearchQuery\\(\")(.*)(?=\"\\))");
				Matcher m = getQuery.matcher(func);
				m.find();

				return ShoeAPI.getShoesFromSearchQuery(m.group());
			} catch (MalformedURLException e) {
				System.out.println("Internal error occured @ Executor.");
			}

		} else if (func.startsWith("setSelectedShoe")) {
			if(func.contains("#response#")) {
				return ChatSession.setSelectedShoe(query);
			} else {
				Pattern getQuery = Pattern.compile("(?<=setSelectedShoe\\(\")(.*)(?=\"\\))");
				Matcher m = getQuery.matcher(func);
				m.find();	
				return ChatSession.setSelectedShoe(m.group());
			}
		}

		return "Internal error occured @ Executor.";
	}
}

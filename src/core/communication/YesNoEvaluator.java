package core.communication;

/**
 * An object that evaluates a string to determine whether it is a generic "yes" or "no".
 * Specially useful for evaluating human responses that may mean either "yes" or "no" implicitly.
 * 
 * @author Raghav Jhavar
 */
public class YesNoEvaluator {
	
	public enum YesNoResponse {
		yes {
			public String toString() { return "y"; }
		},
		no {
			public String toString() { return "n"; }
		},
		none {
			public String toString() { return "none"; }
		}
	}
	
	private static String[] yes = {
		"yes",
		"yeah",
		"yup",
		"ye",
		"ya",
		"sure",
		"yes please",
		"yes, please",
		"alright",
		"ok",
		"aye"
	};
	
	private static String[] no = {
		"no",
		"nope",
		"nah",
		"na",
		"no thanks",
		"i'm good"
	};
	
	public static YesNoResponse evaluateMessage(String message) {
		
		//Calculate which array is bigger (yes or no) for later use in for loop
		int biggerLength = yes.length > no.length ? yes.length : no.length;
		
		/*
		 * 
		 * TODO
		 * Possibly better approach: split into two for loops. Which is faster?
		 * 
		 */
		for(int i = 0; i < biggerLength; i++) {
			
			//If i falls within yes arrays length, see if message.equals(yes[i])
			if(i < yes.length) {
				
				if(message.toLowerCase().equals(yes[i])) {
					return YesNoResponse.yes;
				}
			}
			
			if(i < no.length) {
				if(message.toLowerCase().equals(no[i])) {
					return YesNoResponse.no;
				}
			}
			
		}
		
		//If not yes or no, return null.
		return YesNoResponse.none;
	}
}

package core.app;

/**
 * 
 * Holds the information that the user has received so far this chat session
 * Helps make decisions in chat / store responses.
 * 
 * @author Raghav
 *
 */
public class ChatSession {
	
	private static Shoe[] shoesPresented;
	private static Shoe selectedShoe;
	public static int chatStage = 0;

	public static void setShoesPesented(Shoe[] shoes) {
		shoesPresented = shoes;
		chatStage = 1;
	}

	private static String setSelectedShoe(int num) {
		if (num > shoesPresented.length) {
			return "That's not an invalid choice! Try again.";
		}
		selectedShoe = shoesPresented[num - 1];
		chatStage = 2;
		return "Alright, selected " + selectedShoe.getDisplayName() + " for $" + selectedShoe.getPrice() + ". Would you like to checkout?";
	}

	public static String setSelectedShoe(String name) {
		//Check if they entered a number
		try {
			return setSelectedShoe(Integer.parseInt(name));
		} catch (NumberFormatException e) {
			
			for (int i = 0; i < shoesPresented.length; i++) {
				if(isShoe(name.toLowerCase(), shoesPresented[i].getDisplayName().toLowerCase()) > 0) {
					selectedShoe = shoesPresented[i];
					break;
				}
			}
			chatStage = 2;
			return "Alright, selected " + selectedShoe.getDisplayName() + " for $" + selectedShoe.getPrice() + ". Would you like to checkout?";
		}
	}
	
	public static String checkout() {
		chatStage = 3;
		return "Successfully checked out your shoe: " + selectedShoe.getDisplayName() + ".\nWould you like to purchase another one?";
	}	
	
	private static int isShoe(String input, String comparingTo) {
		int a = input.indexOf(comparingTo);
		int b= comparingTo.indexOf(input);
		return a >= b ? a : b;
	}
}

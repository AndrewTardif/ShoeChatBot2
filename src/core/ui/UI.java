package core.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextArea;

public class UI {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	private static JTextArea chatBox;
	/**
	 * @param message The message to put into the chat box.
	 * @param who The person sending the message
	 */
	public static void addMessageToChat(String message, String who) {
		
		chatBox.append(
			String.format(
					"[%s] %s: %s\n\n",
					LocalDateTime.now().format(formatter),
					who,
					message
			)
		);
		
	}
	
	public static void setChatBox(JTextArea chatBox) {
		UI.chatBox = chatBox;
	}
}

package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import core.communication.AnswerGenerator;
import core.ui.UI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainUI {
	
	private JFrame frmShoechatbot;
	private JTextField txtMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmShoechatbot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() {
		//Load answers first thing
		AnswerGenerator.loadAnswers();
		
		frmShoechatbot = new JFrame();
		frmShoechatbot.setTitle("ShoeChatBot");
		frmShoechatbot.setBounds(100, 100, 711, 345);
		frmShoechatbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShoechatbot.getContentPane().setLayout(null);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(10, 254, 675, 20);
		frmShoechatbot.getContentPane().add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send Message");
		btnSend.setBounds(10, 276, 675, 23);
		frmShoechatbot.getContentPane().add(btnSend);
		frmShoechatbot.getRootPane().setDefaultButton(btnSend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 675, 232);
		frmShoechatbot.getContentPane().add(scrollPane);
		
		JTextArea txtChat = new JTextArea();
		scrollPane.setViewportView(txtChat);
		txtChat.setEditable(false);
		
		UI.setChatBox(txtChat);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String q;
				//Add the message the user sends to chat when button pressed
				UI.addMessageToChat((q = txtMessage.getText()), "You");
				//Reset message box
				txtMessage.setText("");
				//Generate response
				if(!q.startsWith("!")) {
					System.out.println(q);
					UI.addMessageToChat(AnswerGenerator.evalQuestion(q), "Bot");
				} else if(q.equals("!clear")) {
					txtChat.setText("");
				}
				
			}
		});		
	}
}

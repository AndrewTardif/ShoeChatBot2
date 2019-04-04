package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
//import javax.swing.JEditorPane; //changed

//import core.app.Shoe;
import core.communication.AnswerGenerator;
import core.ui.UI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainUI {

	private JFrame frmShoechatbot;
	private JTextField txtMessage;
	// private JEditorPane pane; //changed

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
	 * 
	 * @throws SocketException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public MainUI() throws SocketException, UnknownHostException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SocketException 
	 * @throws UnknownHostException 
	 * @throws IOException 
	 */
	private void initialize() throws SocketException, UnknownHostException {
		//Load answers first thing
		AnswerGenerator.loadAnswers();
		
		frmShoechatbot = new JFrame();
		
		//pane = new JEditorPane(); //changed
		//pane.setContentType("text/html"); //changed
		//frmShoechatbot.add(pane); //changed
		
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
		
		Thread serverThread = new EchoServer();//changed
		serverThread.start();//changed
		EchoClient client1 = new EchoClient();//changed
		//EchoClient client2 = new EchoClient();//changed
		
		UI.addMessageToChat("Press 1 for Normal Functionality, Press 2 for two chatbot Convo", "Bot");
			btnSend.addActionListener(new ActionListener() {
				boolean Norm = false;
				public void actionPerformed(ActionEvent e) {
					String txt = txtMessage.getText();
					if(Norm == false && !txt.isEmpty() && (Integer.parseInt(txt) == 1))
					{
						Norm = true;
						AnswerGenerator.norm = true;
					}
					if(Norm)
					{
						
						String q;	
						//Add the message the user sends to chat when button pressed
						UI.addMessageToChat((q = txtMessage.getText()), "You");
						//Reset message box
						txtMessage.setText("");	
							//Generate response
							if(!q.startsWith("!")) 
							{
								System.out.println(q);
								UI.addMessageToChat(AnswerGenerator.evalQuestion(q), "Bot");
							} 
							else if(q.equals("!clear")) 
							{
								txtChat.setText("");
							}
					}
						else if(Norm == false)
						{
							String msg = "";
							//UI.addMessageToChat(msg, "Server");
							int counter = 0;
							txtMessage.setText("");
							while(counter < 10)
							{
								counter++;
								try {
									msg = AnswerGenerator.evalQuestion(msg);
									UI.addMessageToChat(msg, "Client");
									msg = client1.sendEcho(msg);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					client1.close();
					//client2.close();
						
					}	
				});
			
			}		
		}



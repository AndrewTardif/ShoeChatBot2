package main;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import core.communication.AnswerGenerator;
public class EchoClient 
{
	private DatagramSocket socket;
	private InetAddress address;
	private byte[] buff;
	
	public EchoClient() throws SocketException, UnknownHostException 
	{
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
	}
	
	public String sendEcho(String message) throws IOException
	{
		buff = message.getBytes();
		DatagramPacket packet = new DatagramPacket(buff,buff.length,address,1111);
		socket.send(packet);
		buff = new byte[256];
		Arrays.fill(buff, (byte)0);
		packet = new DatagramPacket(buff,buff.length);
		socket.receive(packet);
		String recieved = new String(packet.getData(),0,packet.getLength());
		return recieved;
	}
	public String ansReturn(String msg)
	{
		String message = AnswerGenerator.evalQuestion(msg);
		return message;
	}
	public void close()
	{
		socket.close();
	}
	
	public static void main(String args[]) throws IOException
	{
		EchoClient client1 = new EchoClient();
		EchoClient client2 = new EchoClient();
		Scanner scan = new Scanner(System.in);
		String msg= "";
		while(msg !="end")
		{
			System.out.println(client1.sendEcho("Client 1:" + AnswerGenerator.evalQuestion(msg)));
			System.out.println(client2.sendEcho("Client 2:" + AnswerGenerator.evalQuestion(msg)));
		}
		scan.close();
		client1.close();
		client2.close();
	}
}

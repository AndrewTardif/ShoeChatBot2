package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
public class EchoServer extends Thread 
{
	private DatagramSocket socket;
	private boolean running;
	private byte[] buff = new byte[256];
	private byte[] prevBuff = new byte[256];
	
	public EchoServer() throws SocketException
	{
		socket = new DatagramSocket(1111);
	}
	
	public void run() 
	{
		running = true;
		
		while(running)
		{
			DatagramPacket packet = new DatagramPacket(buff, buff.length);
			
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			InetAddress address = packet.getAddress();
			int port =packet.getPort();
			packet = new DatagramPacket(buff, buff.length,address, port);
			String recieved = new String(packet.getData(),0,packet.getLength());
			//System.out.println(recieved); //test
			
			if(recieved.equals("end"))
					{
						running = false;
						continue;
					}
		
			try {
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			buff = new byte[256];
		}
		socket.close();
	}
	
	//public static void main(String args[]) throws SocketException
	//{
		//Thread serverThread = new EchoServer();
			
			//serverThread.start();
	//}

}

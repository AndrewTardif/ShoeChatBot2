import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
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
	
	public void close()
	{
		socket.close();
	}
	
	public static void main(String args[]) throws IOException
	{
		EchoClient client = new EchoClient();
		EchoClient client2 = new EchoClient();
		Scanner scan = new Scanner(System.in);
		String msg= "";
		while(msg !="end")
		{
			msg = scan.next();
			System.out.println(client.sendEcho("Client 1:" + msg));
			System.out.println(client2.sendEcho("Client 2:" + msg));
		}
		scan.close();
		client.close();
		client2.close();
	}
}

package udp_client;
import java.net.*;

import java.io.*;

public class UDPClient {
	public final static int port = 3000;
	public static void main(String[] args){
		String hostname;
		if(args.length>0){
			hostname=args[0];
		}else{
			hostname="localhost";
		}
		try{
			String strInput;
			DatagramPacket outPacket;

			InetAddress server = InetAddress.getByName(hostname);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

			DatagramSocket dSock = new DatagramSocket();

			while(true){
				strInput = userInput.readLine();
				if(strInput.equals("quit"))break;
				byte[] data = strInput.getBytes();
				outPacket = new DatagramPacket(data, data.length, server, port);
				
				UDPClient.viewEcho(outPacket);
				dSock.send(outPacket);
			}

		}catch(UnknownHostException e){
			System.err.println(e);
		}catch(SocketException se){
			System.err.println(se);
		}catch(IOException e){
			System.err.println(e);
		}
	}


//	public static void viewInfo(DatagramPacket packet){
//		System.out.println("패킷의 대상 주소:"+packet.getAddress().toString());
//		System.out.println("패킷의 대상 포트:"+packet.getPort());
//		System.out.println("패킷의 데이터 크기:"+packet.getLength());
//
//	}
	
	public static void viewEcho(DatagramPacket packet){
		String echo = new String(packet.getData(), 0, 0, packet.getLength());
		System.out.println(echo);
	}

}

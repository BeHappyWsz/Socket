package com.wsz.Socket.udp.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {
		serverSocket();
	}
	
	public static void serverSocket() {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		try {
			//1.创建服务器端
			socket = new DatagramSocket(8800);
			System.out.println("服务器已启动,正在监听");
			byte[] data =new byte[1024];//指定接受的数据包的大小
			while(true) {
				packet = new DatagramPacket(data, data.length);
				//接收客户端发送的数据,在接收之前将一直堵塞
				socket.receive(packet);
				
				UDPServerThread udpServerThread = new UDPServerThread(socket,packet);
				udpServerThread.start();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

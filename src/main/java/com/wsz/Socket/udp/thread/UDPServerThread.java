package com.wsz.Socket.udp.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * UDP协议服务器线程处理类
 * @author wsz
 * @date 2018年3月2日
 */
public class UDPServerThread extends Thread{
	
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	
	public UDPServerThread(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}
	
	@Override
	public void run() {
		try {
			//1.获取数据
			byte[] data = packet.getData();
			//2.读取数据
			String info = new String(data,0,packet.getLength());
			System.out.println("我是服务器,客户端说:"+info);
			
			/**
			 * 向客户端响应数据
			 */
			//1.定义客户端的地址、端口号、数据
			InetAddress address = packet.getAddress();
			int port = packet.getPort();
			byte[] data2 ="欢迎".getBytes();
			//2.创建数据报,包括响应的数据信息
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address,port);
			//3.响应客户端
			socket.send(packet2);
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

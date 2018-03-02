package com.wsz.Socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 使用UDP协议，模拟用户登录
 * 客户端
 * @author wsz
 * @date 2018年3月2日
 */
public class Client {

	public static void main(String[] args) {
		client();
	}
	
	public static void client() {
		InetAddress address = null;
		DatagramPacket packet = null;
		DatagramSocket socket = null;
		try {
			//1.定义服务器地址、端口号、数据
			address = InetAddress.getByName("localhost");
			int port =8800;
			byte[] data="用户名:abc,密码:123".getBytes();
			//2.创建数据报,包含发送的数据
			packet = new DatagramPacket(data, data.length,address,port);
			//3.创建对象
			socket = new DatagramSocket();
			//4.向服务器端发送数据报
			socket.send(packet);
			
			/**
			 * 接受服务器端响应的数据
			 */
			//1.创建数据报,用于接受服务器端响应的数据
			byte[] data2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
			//2.接受服务器响应的数据
			socket.receive(packet2);
			//3.读取数据
			String reply = new String(data2,0,packet2.getLength());
			System.out.println("我是客户端,服务器说:"+reply);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null)
				socket.close();
		}
		
	}
}

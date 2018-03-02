package com.wsz.Socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 使用UDP协议，模拟用户登录
 * 服务器端
 * @author wsz
 * @date 2018年3月2日
 */
public class ServerSocket {

	public static void main(String[] args) {
		serverSocket();
	}
	
	public static void serverSocket() {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		try {
			//1.创建服务器端
			socket = new DatagramSocket(8800);
			System.out.println("服务器正在启动...正在监听");
			//2.创建数据报,
			byte[] data =new byte[1024];//指定接受的数据包的大小
			packet = new DatagramPacket(data, data.length);
			//3.接受客户端发送的数据
			socket.receive(packet);
			//4.读取数据
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
			//4.关闭资源
			socket.close();
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

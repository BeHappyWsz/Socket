package com.wsz.Socket.tcp.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 一直监听的服务器端
 * @author wsz
 * @date 2018年3月2日
 */
public class Server {

	public static void main(String[] args) {
		server();
	}
	
	public static  void server() {
		ServerSocket serverSocket = null;
		try {
			//1.服务器端Socket，指定绑定端口
			serverSocket = new ServerSocket(8888);
			Socket socket = null;
			//记录客户端的数量
			int count = 0;
			//2.调用accept()开始监听,等待客户端的连接
			System.out.println("服务器即将启动...等待客户端的连接");
			//3.循环监听等待客户端的连接
			while(true) {
				//开始监听
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				//启动线程
				serverThread.start();
				count++;
				
				System.out.println("已连接的客户端数量为:"+count);
//				printMsg(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入客户端的相关信息
	 * @param socket
	 */
	public static void printMsg(Socket socket) {
		InetAddress address = socket.getInetAddress();
		System.out.print("当前客户端计算机名称"+address.getHostName());
		System.out.println("  ;IP地址"+address.getHostAddress());
	}
}

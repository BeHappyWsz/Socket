package com.wsz.Socket.tcp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录
 * 服务器端
 * @author wsz
 * @date 2018年3月2日
 */
public class Server {

	public static void main(String[] args) {
		server();
	}
	
	public static void server() {
		try {
			//1.服务器端Socket，指定绑定端口
			ServerSocket serverSocket = new ServerSocket(8888);
			//2.调用accept()开始监听,等待客户端的连接
			System.out.println("服务器即将启动...,等待客户端的连接");
			Socket socket = serverSocket.accept();
			//3.获取输入流,定读取客户端信息
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new  BufferedReader(isr);
			
			String info = null;
			while((info = br.readLine()) != null) {
				System.out.println("我是服务器端,客户端说:"+info);
			}
			socket.shutdownInput();//关闭输入流
			
			//4.获取输出流,响应客户端的请求
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎");
			pw.flush();//将缓冲输出
			
			//5.关闭资源
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

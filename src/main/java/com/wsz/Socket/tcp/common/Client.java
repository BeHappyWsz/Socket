package com.wsz.Socket.tcp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 基于TCP协议的Socket通信，实现用户登录
 * 客户端
 * @author wsz
 * @date 2018年3月2日
 */
public class Client {

	public static void main(String[] args) {
		client();
	}
	
	public static void client() {
		//1.创建客户端Socket,指定服务器地址和端口
		try {
			Socket socket = new Socket("localhost", 8888);
			//2.获取输出流,向服务器端发送消息
			OutputStream os = socket.getOutputStream();//字节输出流
			PrintWriter pw = new PrintWriter(os);//包装为打印流
			pw.write("用户名:admin;密码:123");
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			
			//3.获取输入流,读取服务器端的响应
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info = br.readLine())!= null) {
				System.out.println("我是客户端,服务器端说:"+info);
			}
			
			//4.关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

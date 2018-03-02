package com.wsz.Socket.tcp.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 服务器线程处理类
 * @author wsz
 * @date 2018年3月2日
 */
public class ServerThread extends Thread{

	private Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			//1.获取输入流,定读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new  BufferedReader(isr);
			
			String info = null;
			while((info = br.readLine()) != null) {
				System.out.println("我是服务器端,客户端说:"+info);
			}
			socket.shutdownInput();//关闭输入流
			
			//2.获取输出流,响应客户端的请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("欢迎");
			pw.flush();//将缓冲输出
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//关闭资源
			try {
				if(pw != null)
					pw.close();
				if(os != null)
					os.close();
				if(br != null)
					br.close();
				if(isr != null)
					isr.close();
				if(is != null)
					is.close();
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

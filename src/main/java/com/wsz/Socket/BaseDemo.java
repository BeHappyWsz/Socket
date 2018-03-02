package com.wsz.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class BaseDemo {

	public static void main(String[] args) {
		readWebMsg();
	}
	
	/**
	 * 使用InetAddress方法
	 */
	public static void inetAddress() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("计算机名称"+address.getHostName());
			System.out.println("IP地址"+address.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用URL方法
	 */
	public static void urlDemo() {
		try {
			URL ad = new URL("http://www.imooc.com");
			//?后面为参数 #后面为锚点
			URL url = new URL(ad,"/index.html?username=tom#test");
			
			//查询方案
			System.out.println("协议:"+url.getProtocol());
			System.out.println("主机:"+url.getHost());
			System.out.println("端口:"+url.getPort());//如果未指定端口号,则使用默认的端口号,此时返回-1
			System.out.println("文件路径:"+url.getPath());
			System.out.println("文件名:"+url.getFile());
			System.out.println("相对路径:"+url.getRef());
			System.out.println("查询字符串:"+url.getQuery());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用URL读取网页内容
	 */
	public static void readWebMsg() {
		try {
			URL url = new URL("http://www.baidu.com");
			//获取资源的字节输入流
			InputStream is = url.openStream();
			//字节输入流装换位字符输入流
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			//添加缓冲
			BufferedReader br = new BufferedReader(isr);
			String data;//读取的数据
			while(( data = br.readLine()) != null) {
				System.out.println(data);
			}
			br.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

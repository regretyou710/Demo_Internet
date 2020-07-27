package tw.com.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 透過InetAddress類別操作IP地址
 * 如何獲取InetAddress物件?調用InteAddress靜態方法
 *  1.getByName(String host)
 *  2.getLoaclHost()
 */
public class Demo01 {

	public static void main(String[] args) {
		try {
			InetAddress inte1 = InetAddress.getByName("192.168.10.101");
			System.out.println(inte1);

			InetAddress inte2 = InetAddress.getByName("www.google.com");
			System.out.println(inte2);

			InetAddress inte3 = InetAddress.getByName("localhost");
			System.out.println(inte3);

			InetAddress inte4 = InetAddress.getLocalHost();
			System.out.println(inte4);

			System.out.println(inte2.getHostName());
			System.out.println(inte2.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

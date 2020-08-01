package tw.com.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/*
 * 操作TCP網路程式 
 * 範例一:從用戶端發送訊息給服務端，服務端將訊息顯示在控制台中
 * 原則上，服務端會先存在、啟動然後用戶端才知道要傳數據給哪個服務端
 */
public class Demo01 {

	// 服務端
	@Test
	public void server() {

		ServerSocket serverSocket = null;
		Socket accept = null;
		InputStream is = null;
		InputStreamReader isr = null;
		ByteArrayOutputStream baos = null;
		try {
			// 1.創建服務端的物件，指名自己的埠號
			serverSocket = new ServerSocket(8899);

			// 2.調用accept方法表示接收用戶端的socket
			accept = serverSocket.accept();

			// 3.獲取輸入流
			is = accept.getInputStream();

			System.out.println("這是來自:" + accept.getInetAddress().getHostAddress() + "/"
					+ accept.getInetAddress().getHostName() + "的訊息");

			// 4.讀取輸入流中的數據
			// 方式一：直接讀取位元轉換成字串，若陣列大小不夠會有產生亂碼的可能
			// byte[] buff = new byte[5];
			// int size;
			// while ((size = is.read(buff)) != -1) {
			// String msg = new String(buff, 0, size);
			// System.out.print(msg);
			// }

			// 方式二：透過轉換流
			// isr = new InputStreamReader(is);
			// char[] buff = new char[5];
			// int size;
			// while ((size = isr.read(buff)) != -1) {
			// String msg = new String(buff, 0, size);
			// System.out.print(msg);
			// }

			// 方式三：透過ByteArrayOutputStream()方法將數據寫出
			baos = new ByteArrayOutputStream();
			byte[] buff = new byte[5];
			int size;
			while ((size = is.read(buff)) != -1) {
				baos.write(buff, 0, size);
			}
			System.out.println(baos.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.關閉資源流
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (accept != null) {
				try {
					accept.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 用戶端
	@Test
	public void client() {
		Socket socket = null;
		OutputStream os = null;
		try {
			// 1.創建Socket物件，指名服務端的地址和埠號
			// InetAddress inet = InetAddress.getByName("127.0.0.1");
			InetAddress inet = InetAddress.getByName("192.168.10.101");
			socket = new Socket(inet, 8899);

			// 2.取得輸出流，用於輸出數據
			os = socket.getOutputStream();

			// 3.寫出數據的操作
			os.write("請問有人在嗎?我需要幫忙!".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4.關閉資源流
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

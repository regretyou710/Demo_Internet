package tw.com.tcp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/*
 * 操作TCP網路程式
 * 範例三:從用戶端發送文件給服務端，服務端將文件儲存在本地，並返回"發送成功"給用戶端
 */
public class Demo03 {

	// 服務端
	@Test
	public void server() {
		// 創建服務端socket
		ServerSocket serverSocket = null;

		// 接收用戶端socket
		Socket accept = null;

		// 創建輸入流物件
		InputStream is = null;

		// 創建輸出流物件
		FileOutputStream fos = null;
		OutputStream os = null;

		try {
			serverSocket = new ServerSocket(9999);

			accept = serverSocket.accept();

			is = accept.getInputStream();

			fos = new FileOutputStream(new File("D://桌面/javaiconserver.jpg"));

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSS");
			String date = sdf.format(new Date(System.currentTimeMillis()));
			System.out.println(date + "\t這是來自:" + accept.getInetAddress().getHostAddress() + "/"
					+ accept.getInetAddress().getHostName() + "的訊息");

			// 讀取輸入流中的數據
			byte[] buff = new byte[1024];
			int size;
			while ((size = is.read(buff)) != -1) {
				fos.write(buff, 0, size);
			}

			// 服務端回覆訊息給用戶端
			os = accept.getOutputStream();
			os.write((date + "\t已經成功收到您發送的訊息").getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}

				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}

				if (accept != null) {
					accept.close();
				}

				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 用戶端
	@Test
	public void client() {
		// 創建用戶端socket
		Socket socket = null;
		// 創建輸出流物件
		OutputStream os = null;
		// 創建輸入流物件
		FileInputStream fis = null;

		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			socket = new Socket(InetAddress.getByName("192.168.10.101"), 9999);

			os = socket.getOutputStream();

			fis = new FileInputStream(new File("javaiconclient.jpg"));
			// 讀取輸入流中的數據
			byte[] buff = new byte[1024];
			int size;
			while ((size = fis.read(buff)) != -1) {
				os.write(buff, 0, size);
			}

			// 告訴服務端，用戶端的輸出已經結束
			socket.shutdownOutput();

			// 接收來自服務端的回應訊息，顯示在控制台
			is = socket.getInputStream();
			baos = new ByteArrayOutputStream();
			byte[] buffMsg = new byte[5];
			int sizeMsg;
			while ((sizeMsg = is.read(buffMsg)) != -1) {
				baos.write(buffMsg, 0, sizeMsg);
			}
			System.out.println(baos.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}

				if (is != null) {
					is.close();
				}

				if (fis != null) {
					fis.close();
				}
				if (os != null) {
					os.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

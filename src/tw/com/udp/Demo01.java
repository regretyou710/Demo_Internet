package tw.com.udp;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import org.junit.Test;

/*
 * UDP通訊協議的網路程式
 */
public class Demo01 {

	// 接收者
	@Test
	public void receiver() {
		DatagramSocket datagramSocket = null;
		try {
			datagramSocket = new DatagramSocket(9999);

			byte[] buff = new byte[100];
			DatagramPacket datagramPacket = new DatagramPacket(buff, 0, buff.length);

			datagramSocket.receive(datagramPacket);
		
			System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datagramSocket.close();
		}

	}

	// 發送者
	@Test
	public void sender() {
		DatagramSocket datagramSocket = null;

		try {
			datagramSocket = new DatagramSocket();

			// 將IP地址、埠號、訊息、封裝到DatagramPacket物件中
			String msg = "這是使用UDP方式傳送的訊息!";
			byte[] data = msg.getBytes();
			InetAddress inet = InetAddress.getByName("192.168.10.101");
			DatagramPacket datagramPacket = new DatagramPacket(data, 0, data.length, inet, 9999);

			datagramSocket.send(datagramPacket);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			datagramSocket.close();
		}
	}
}

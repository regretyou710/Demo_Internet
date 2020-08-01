package tw.com.url;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * URL程式
 */
public class Demo02 {

	public static void main(String[] args) {

		HttpURLConnection openConnection = null;
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL("http://tbike-data.tainan.gov.tw/Service/StationStatus/Json");

			// 取得與服務器的連接
			openConnection = (HttpURLConnection) url.openConnection();
			openConnection.connect();

			// 創建輸入流物件
			is = openConnection.getInputStream();
			
			// 創建輸出流物件
			fos = new FileOutputStream("D:/桌面/T-Bike臺南市公共自行車租賃站資訊.json");
			
			// 讀入操作
			byte[] buff = new byte[1024];
			int size;
			while ((size = is.read(buff)) != -1) {
				fos.write(buff, 0, size);
			}

			System.out.println("下載完成");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 關閉資源
			try {
				if (fos != null) {
					fos.close();
				}
				if (is != null) {
					is.close();
				}
				if (openConnection != null) {
					openConnection.disconnect();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}

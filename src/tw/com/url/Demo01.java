package tw.com.url;

import java.net.MalformedURLException;
import java.net.URL;

/*
 * URL程式
 */
public class Demo01 {

	public static void main(String[] args) {

		try {
			URL url = new URL("https://www.youtube.com/watch?v=795GFbq7gRk");
			System.out.println("獲取該URL的協議: "+url.getProtocol());
			System.out.println("獲取該URL的主機名: "+url.getHost());
			System.out.println("獲取該URL的埠號: "+url.getPort());
			System.out.println("獲取該URL的文件路徑: "+url.getPath());
			System.out.println("獲取該URL的文件名: "+url.getFile());
			System.out.println("獲取該URL的查詢名: "+url.getQuery());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package httprequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestHttpRequest {
	private static Logger logger 
	    = LoggerFactory.getLogger(TestHttpRequest.class);
	
	
	@Test
	public void testRequest(){
		byte[] resultBytes = doRequest();
		logger.info(new String(resultBytes));
	}
	
	/**
	 * REST请求, 返回数据流
	 * @param id
	 * @return byte[]
	 */
    public byte[] doRequest(){
        URL urlDownload;
        byte[] result = null;
		try {
			urlDownload = new URL("http://localhost:8080/WebProj/Test?action=test");
			HttpURLConnection connection = (HttpURLConnection)urlDownload.openConnection();
			connection.connect();
			
			InputStream is = connection.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] bytes = new byte[1024];
			int len = 0;
			while((len = is.read(bytes)) != -1){
				baos.write(bytes, 0, len);
			}
			result = baos.toByteArray();
			baos.flush();
			baos.close();
			is.close();
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 
		return result;
    }
    
    
    
}

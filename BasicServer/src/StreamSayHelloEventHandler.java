import java.io.InputStream;
import java.util.StringTokenizer;

public class StreamSayHelloEventHandler implements EventHandler {

	private static final int DATA_SIZE = 512;
	private static final int TOKEN_NUM = 2;
	
	public void handleEvent(InputStream inputStream) {
		try {
			byte[] buffer = new byte[DATA_SIZE];
			inputStream.read(buffer);
			String data = new String(buffer);
			
			String[] params = new String[TOKEN_NUM];
			StringTokenizer token = new StringTokenizer(data, "|");
			
			int i =0;
			while(token.hasMoreElements()) {
				params[i] = token.nextToken();
				++i;
			}
			
			sayHello(params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sayHello(String[] params) {

		System.out.println("SayHello -> name :"+ params[0] + " age : " + params[1]);
		
	}

	@Override
	public String getHandler() {
		return "0x5001";
	}
}

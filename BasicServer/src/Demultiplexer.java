import java.io.InputStream;
import java.net.Socket;

public class Demultiplexer implements Runnable{

	private static final int HEADER_SIZE = 6;
	private Socket socket;
	private HandleMap handlerMap;
	
	public Demultiplexer(Socket socket, HandleMap handlerMap) {
		super();
		this.socket = socket;
		this.handlerMap = handlerMap;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			
			byte[] buffer = new byte[HEADER_SIZE];
			inputStream.read(buffer);
			String header = new String(buffer);
			
			handlerMap.get(header).handleEvent(inputStream);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}

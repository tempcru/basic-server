import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerDispatcher implements Dispatcher {

	@Override
	public void dispatch(ServerSocket serverSocket, HandleMap handlerMap) {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				Runnable demultiplexer = new Demultiplexer(socket, handlerMap);
				Thread thread = new Thread(demultiplexer);
				thread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
 
	
}

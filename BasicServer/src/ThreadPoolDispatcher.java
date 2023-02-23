import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class ThreadPoolDispatcher implements Dispatcher{

	static final String THREADPROP = "8";
	static final String NUMTHREADS = "Threads";
	private int numThreads;
	public ThreadPoolDispatcher() {
		numThreads = Integer.parseInt(System.getProperty(THREADPROP, NUMTHREADS));
	}
	
	@Override
	public void dispatch(ServerSocket serverSocket, HandleMap handleMap) {

		for (int i = 0; i < (numThreads - 1); i++) {
			Thread thread = new Thread() {
				public void run() {
					dispatch(serverSocket, handleMap);
				}
			};
			thread.start();
			System.out.println("Created and started Thread = "+ thread.getName());
		}
		System.out.println("Iterative server starting in manin thread "+ Thread.currentThread().getName());
		
		dispatchLoop(serverSocket, handleMap);
		
	}

	private void dispatchLoop(ServerSocket serverSocket, HandleMap handleMap) {

		while(true) {
			try {
				Socket socket = serverSocket.accept();
				Runnable demultiplexer = new Demultiplexer(socket, handleMap);
				demultiplexer.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

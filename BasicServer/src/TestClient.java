import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {

	public static void main(String[] args) {

		System.out.println("Client ON");
		while(true) {
			try {
				String message;
				Socket socket = new Socket("127.0.0.1", 5000);
				OutputStream outputStream = socket.getOutputStream();
				message = "0x5001|È«±æµ¿|22";
				outputStream.write(message.getBytes());
				socket.close();
				
				Socket socket2 = new Socket("127.0.0.1", 5000);
				OutputStream outputStream2 = socket2.getOutputStream();
				message = "0x6001|hong|1234|È«±æµ¿|22|³²¼º";
				outputStream2.write(message.getBytes());
				socket2.close();
				
				Thread.sleep(100);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

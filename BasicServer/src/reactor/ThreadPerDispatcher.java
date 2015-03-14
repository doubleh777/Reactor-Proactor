package reactor;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ThreadPerDispatcher implements Dispatcher{
		
	public void dispatch(ServerSocket serverSocket, HandleMap handleMap){
		while(true){
			try{
				Socket socket = serverSocket.accept();
			
				Runnable demultiplexer = new Demultiplexer(socket, handleMap);
				Thread thread = new Thread(demultiplexer);
				thread.start();
			
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}

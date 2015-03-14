package reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPoolDispatcher implements Dispatcher{
	
	static final String NUMTHREADS = "8";
	static final String THREADPROP = "Threads";
	
	private int numThreads;
	
	public ThreadPoolDispatcher(){
		numThreads = Integer.parseInt(System.getProperty(THREADPROP, NUMTHREADS));
	}
	
	@Override
	public void dispatch(ServerSocket serverSocket, HandleMap handlers) {
		for(int i = 0 ; i < numThreads - 1 ; i++){
			Thread thread = new Thread(){
				public void run(){
					dispatchLoop(serverSocket, handlers);
				}
			};
			thread.start();
			System.out.println("Created and started Thread = " + thread.getName());
		}
		
		
		System.out.println("Interactive server starting in main thread " + Thread.currentThread().getName());
		dispatchLoop(serverSocket, handlers);
	}
	
	private void dispatchLoop(final ServerSocket serverSocket, final HandleMap handlers){
		
		while(true){
			try{
				Socket socket = serverSocket.accept();
				Runnable demultiplexer = new Demultiplexer(socket, handlers);
				demultiplexer.run();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}

package proactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerInitialize {

	private static int PORT = 500;
	private static int THREADPOOL_SIZE = 8;
	private static int INITIAL_SIZE = 4;
	private static int BACKLOG = 50;
	
	public static void main(String[] args){
		System.out.println("Server start!");
		
		NioHandleMap handleMap = new NioHandleMap();
		
		NioEventHandler sayHello = new NioSayHelloEventHandler();
		NioEventHandler update = new NioUpdateEventHandler();
		
		handleMap.put(sayHello.getHandler(), sayHello);
		handleMap.put(update.getHandler(), update);
		
		
		//고정 쓰레드풀 생성
		ExecutorService executor = Executors.newFixedThreadPool(THREADPOOL_SIZE);
		
		//케시 쓰레드풀 생성
		try{
			AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(executor, INITIAL_SIZE);
			
			//스트림 지향의 리스닝 소켓을 위한 비동기 체널
			AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open(group);
			listener.bind(new InetSocketAddress(PORT), BACKLOG);
			
			listener.accept(listener, new Dispatcher(handleMap));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

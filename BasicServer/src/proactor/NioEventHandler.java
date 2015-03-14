package proactor;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public interface NioEventHandler extends CompletionHandler<Integer, ByteBuffer>{
	
	public String getHandler();
	public int getDataSize();
	public void initialize(AsynchronousSocketChannel channel, ByteBuffer buffer);
}

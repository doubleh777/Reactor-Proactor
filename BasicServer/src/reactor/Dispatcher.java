package reactor;

import java.net.ServerSocket;

public interface Dispatcher {
	public void dispatch(ServerSocket serverSocket, HandleMap handlers);
}

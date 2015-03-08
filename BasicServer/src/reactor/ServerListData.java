package reactor;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class ServerListData {

	@ElementList(entry = "server", inline = true)
	private List<HandlerListData> server;
	
	public List<HandlerListData> getServer(){
		return server;
	}
}

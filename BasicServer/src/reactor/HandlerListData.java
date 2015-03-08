package reactor;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class HandlerListData {
	@ElementList(entry = "handler", inline = true)
	private List<HandlerData> handler;
	
	@Attribute
	private String name;
	
	public List<HandlerData> getHandler(){
		return handler; 
	}
	
	public String getName(){
		return name;
	}
}

package reactor;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class HandlerData {
	@Attribute(required = false)
	private String header;
	
	@Text
	private String text;
	
	public String getHeader(){
		return header;
	}
	
	public String getHandler(){
		return text;
	}
}

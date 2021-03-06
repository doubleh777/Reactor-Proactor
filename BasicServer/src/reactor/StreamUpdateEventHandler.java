package reactor;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;


public class StreamUpdateEventHandler implements EventHandler{
	private static final int DATA_SIZE = 512;
	private static final int TOKEN_NUM = 5;
	
	public void handleEvent(InputStream inputStream){
		
		try{
			byte[] buffer = new byte[DATA_SIZE];
			inputStream.read(buffer);
			String data = new String(buffer);
			
			String[] params = new String[TOKEN_NUM];
			StringTokenizer token = new StringTokenizer(data, "|");
			
			int i = 0;
			while(token.hasMoreTokens()){
				params[i] = token.nextToken();
				i++;
			}
			
			update(params);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void update(String[] params){
		System.out.println("UpdateProfile ->"
				+ "id -> " + params[0] 
				+ "age -> " + params[1]
				+ "name -> " + params[2]
				+ "age -> " + params[3]
				+ "gender -> " + params[4]);
	}

	@Override
	public String getHandler() {
		return "0x6001";
	}
}

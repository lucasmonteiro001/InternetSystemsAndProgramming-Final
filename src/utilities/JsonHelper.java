package utilities;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class JsonHelper {
	private String msg;
	
	private String getMsg() {
		return msg;
	}
	private void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Return the formatted json string
	 * @param param Receives either "success" or "failed" and builds the string accordingly to it.
	 * @return json formatted
	 */
	public String getJsonFormatted(boolean success, String data) {
		
		this.setMsg(data);
		
		if (success == true) {
			return " {\"success\":\"true\", \"data\":\"" + this.getMsg() + "\"}";
		}
		else {
			return " {\"success\":\"false\", \"data\":\"" + this.getMsg() + "\"}";
		}
		
	}
	
	
}

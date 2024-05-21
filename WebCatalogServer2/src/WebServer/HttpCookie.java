package WebServer;

public class HttpCookie {
	private String name;
	private String value;
	
	public HttpCookie(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	
	
	public String getName() {
		return name;
	}


	public String getValue() {
		return value;
	}
	
	public static HttpCookie parse(String cookieString) {
		String[] parts = cookieString.split("=");
		
		return new HttpCookie(parts[0], parts[1]);
	}
	
	@Override
	public String toString() {
		return getName() + "=\"" + getValue() + "\"";
	}
	
	
}

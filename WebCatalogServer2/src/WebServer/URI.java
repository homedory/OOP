package WebServer;

public class URI {
	private String scheme;
	private String host;
	private String path;
	
	
	public URI(String urlString) {
		this.path = urlString;
		
	}
	
	public URI(String scheme, String host, String path) {
		super();
		this.scheme = scheme;
		this.host = host;
		this.path = path;
	}
	
	public String getScheme() {
		return scheme;
	}
	public String getHost() {
		return host;
	}
	public String getPath() {
		return path;
	}
	
	
}

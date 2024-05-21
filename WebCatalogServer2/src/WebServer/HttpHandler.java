package WebServer;

import java.io.IOException;

public interface HttpHandler {
	
	public abstract void handle (HttpExchange httpExchange) throws IOException;
}

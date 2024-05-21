package WebServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	private HttpHandler handler;
	private String path;
	private InetSocketAddress socketAddress;
	private int backlog;
	private int port;
	
	public HttpServer(InetSocketAddress socketAddress, int backlog) {
		this.socketAddress = socketAddress;
		this.backlog = backlog;
		this.handler = null;
		this.path = null;
		this.port = socketAddress.getPort();
	}
	
	public static HttpServer create(InetSocketAddress socketAddress, int backlog) {
		
		return new HttpServer(socketAddress, backlog);
	}
	
	public void createContext(String path, HttpHandler handler) {
		this.handler = handler;
		this.path = path;
	}
	
	public void start() {
		try {
			ServerSocket welcomeSocket = new ServerSocket(port);
			
			while(true) {
				Socket connectionSocket = welcomeSocket.accept();
				
				HttpExchange httpExchange = new HttpExchange(connectionSocket.getInputStream(),
						connectionSocket.getOutputStream(), socketAddress);
				
				httpExchange.readRequest();
				
				handler.handle(httpExchange);
				
				//close socket
				connectionSocket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

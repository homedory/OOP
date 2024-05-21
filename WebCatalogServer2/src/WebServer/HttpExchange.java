package WebServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class HttpExchange {
	
	private InetSocketAddress socketAddress;
	private BufferedReader bufferedReader;
	private DataOutputStream dataOuputStream;
	private String requestLine;
	private Headers requestHeaders;
	private String requestBody;
	private String statusLine;
	private Headers responseHeaders;
	private String httpVersion;
	
	public HttpExchange(InputStream inputStream, OutputStream outputStream, 
			InetSocketAddress socketAddress) {
		this.socketAddress = socketAddress;
		
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		dataOuputStream = new DataOutputStream(outputStream);
		requestHeaders = new Headers();
		responseHeaders = new Headers();
	}
	
	public void readRequest() {
		try {
			//read request line
			requestLine = bufferedReader.readLine();
			String[] parts = requestLine.split(" ");
			httpVersion = parts[2].replaceAll("\r\n$", "");
			
			
			//read request header lines
			String headerLine;
			while((headerLine = bufferedReader.readLine()) != null && !headerLine.isEmpty()) {
				requestHeaders.add(headerLine);
			}
			//don't have to read request body since method is GET
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public URI getRequestURI() {
		String[] requestComponents = requestLine.split(" ");
		String url = requestComponents[1];
		return new URI(url);
	}
	
	public DataOutputStream getResponseBody() {
		return dataOuputStream;
	}
	
	public Headers getRequestHeaders() {
		return requestHeaders;
	}
	
	public Headers getResponseHeaders() {
		return responseHeaders;
	}
	
	void sendResponseHeaders(int rcode, long responselength) {
		try {
			//set status line (in this case assume 200 OK)
			statusLine = httpVersion + " " + rcode + " OK\r\n";
			//send status line before sending headers
			dataOuputStream.writeBytes(statusLine);
			
			//add response content-length
			responseHeaders.add("Content-Length", String.valueOf(responselength));;
			
			//send response headers
			dataOuputStream.writeBytes(responseHeaders.toString());
			
			//send '\r\n' to divide header and body
			dataOuputStream.writeBytes("\r\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InetSocketAddress getLocalAddress() {
		return socketAddress;
	}
	
}

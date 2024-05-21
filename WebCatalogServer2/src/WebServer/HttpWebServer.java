package WebServer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HttpWebServer {
	
	
	public static void main(String[] args) {
		
		InetSocketAddress defaultAddress = new InetSocketAddress(8080);
		HttpServer httpServer = HttpServer.create(defaultAddress, 0);
		HttpHandler handler = new Handler();
		httpServer.createContext("/", handler);
		httpServer.start();
		
	}
	
}

class Handler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		InetSocketAddress localAddress = exchange.getLocalAddress();
		int portNum = localAddress.getPort();
		boolean firstVisit;
		String cookieValue = "";
		
		URI uri = exchange.getRequestURI();
		
		String path = uri.getPath();
		
		HttpCookie cookie = null;
		
		DataOutputStream os = exchange.getResponseBody();

		//List<String> cookieHeaders = exchange.getRequestHeaders().get("Cookie");
		String cookieHeaders = exchange.getRequestHeaders().get("Cookie");
		//First visit (there is no cookie)
		if (cookieHeaders == null ) {
			firstVisit = true;
			cookie = new HttpCookie("Studentnumber", "2020054757");
			//add cookie to responseHeader
			exchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
			
		}
		else {
			firstVisit = false;
			//List<HttpCookie> cookies = HttpCookie.parse(cookieHeaders.get(0));
			cookie = HttpCookie.parse(cookieHeaders);
			
			//cookie = cookies.get(0);
			cookieValue = cookie.getValue();
		}	
		
		Path sendingFile = null;
		byte[] fileBytes = new byte[0];
		
		//handle HTML request
		if (path.equals("/")) {
			sendingFile = Paths.get("index.html");
			
			//print log
			System.out.println("Listening on port: " + portNum);
			System.out.println("Index page requested");
			printCookie(firstVisit, cookieValue);
		}
		else if (path.equals("/chair")) {
			htmlEdit("Chair");
			sendingFile = Paths.get("detail.html");
			
			//print log
			System.out.println("Listening on port: " + portNum);
			System.out.println("Detail page requested");
			printCookie(firstVisit, cookieValue);
		}
		else if (path.equals("/closet")) {
			htmlEdit("Closet");
			sendingFile = Paths.get("detail.html");
			
			//print log
			System.out.println("Listening on port: " + portNum);
			System.out.println("Detail page requested");
			printCookie(firstVisit, cookieValue);
		}
		else if (path.equals("/table")) {
			htmlEdit("Table");
			sendingFile = Paths.get("detail.html");
			
			//print log
			System.out.println("Listening on port: " + portNum);
			System.out.println("Detail page requested");
			printCookie(firstVisit, cookieValue);
		}
		
		//handle image request
		else if (path.equals("/chair.png")) {
			sendingFile = Paths.get("chair.png");
		}
		else if (path.equals("/closet.png")) {
			sendingFile = Paths.get("closet.png");
		}
		else if (path.equals("/table.png")) {
			sendingFile = Paths.get("table.png");
		}
		else {
			return;
		}
		
		fileBytes = Files.readAllBytes(sendingFile);
		
		//send response header
		exchange.sendResponseHeaders(200, fileBytes.length);
		
		//send fileBytes through stream
		os.write(fileBytes);
		os.close();
	}
	
	private void htmlEdit(String furnitureName) {
		String name, price, description, imageLocation;
		name = price = description = imageLocation = "";
		
		try {
			FileReader jsonFR = new FileReader("furniture.json");
			Gson gson = new Gson();
			
			//parsing JSON
			JsonObject json = gson.fromJson(jsonFR, JsonObject.class);
			JsonArray furnitureArray = (JsonArray) json.get("Furniture");
			
			//find right furniture
			for (int i = 0; i < furnitureArray.size(); i++) {
				JsonObject furniture = (JsonObject) furnitureArray.get(i);
				name = furniture.get("Name").getAsString();
				price = furniture.get("Price").getAsString();
				description = furniture.get("Description").getAsString();
				imageLocation = furniture.get("ImageLocation").getAsString();
				
				//check name
				if (name.equals(furnitureName)) {
					break;
				}
			}
			
			//Edit HTML
			
			Path basePath = Paths.get("base.html");     //file for base structure
			Path detailPath = Paths.get("detail.html"); //file to send
			Charset charset = StandardCharsets.UTF_8;
			
			//insert information into HTML
			String content = new String(Files.readAllBytes(basePath), charset);
			content = content.replace("OBJECT TITLE", name);
			content = content.replace("OBJECT PRICE", price);
			content = content.replace("OBJECT DESCRIPTION", description);
			content = content.replace("src=\"\"", "src=\"" + imageLocation + "\"" );
			Files.write(detailPath, content.getBytes(charset));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void printCookie(boolean first, String value) {
		if (first) {
			System.out.println("New user requested page, cookie will be set.");
		}
		else {
			System.out.println("Returning user, welcome " + value);
		}
	}
}

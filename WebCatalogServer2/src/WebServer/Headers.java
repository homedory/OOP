package WebServer;

import java.util.ArrayList;
import java.util.List;

public class Headers {
	private List<String> fieldNames;
	private List<String> values;
	
	public Headers() {
		fieldNames = new ArrayList<>();
		values = new ArrayList<>();
	}
	
	public void add(String headerlne) {
		String[] parts = headerlne.split(":");
		add(parts[0], parts[1].trim());
	}
	
	public void add(String fieldName, String value) {
		int idx = fieldNames.indexOf(fieldName);
		if (idx != -1) {
			values.set(idx, value);
		}
		else {
			fieldNames.add(fieldName);
			values.add(value);
		}
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < fieldNames.size(); i++) {
			String str = fieldNames.get(i) + ": " + values.get(i) + "\r\n";
			stringBuilder.append(str);
		}
		
		return stringBuilder.toString();
	}
	
	
	//return value when the filed name is given
	public String get(String field) {
		int idx = fieldNames.indexOf(field);
		
		if (idx != -1) {
			return values.get(idx);
		}
		else {
			return null;
		}
	}
}

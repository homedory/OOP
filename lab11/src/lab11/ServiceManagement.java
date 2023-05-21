package lab11;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ServiceManagement {
	public static <T extends Hub> int findIndexByNum(ArrayList<T> t, int num) {
		for(T box: t) {
			if(box.getNumber() == num) {
				return t.indexOf(box);
			}
		}
		return -1;
	}
	
	public static <T extends Hub> T raisePerBox(T t, double rate) {
		t.setPrice_per_box(t.getPrice_per_box() * rate);
		return t;
	}
	
	public static <T extends Hub> ArrayList<T> raiseAll(Class<T> c, ArrayList<T> tList, double rate) {
		for(T elem:tList) {
			elem.setPrice_per_box(rate*elem.getPrice_per_box());
		}
		
		try {
			Field f = c.getField("init_price_per_box");
			double value = f.getDouble(null);
			f.setDouble(null, value * rate);
		}
		catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return tList;
	}
	
	public static <T extends Hub> void packageBoxes(String[] descriptions, Class<T> classtype, ArrayList<T> tList) {
		try {
			for(int i = 0; i < descriptions.length; i++) {
				tList.add(classtype.getConstructor(String.class).newInstance(descriptions[i]));
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	public static <T extends Hub, U extends Hub> void changeHub(
		ArrayList<T> tList, int index, Class<U> classtype, ArrayList<U> uList
	) {
		try {
			String description = tList.get(index).getDescription();
			U u = classtype.getConstructor(String.class).newInstance(description);
			tList.set(index, null);
			raisePerBox(u, 0.9);
			uList.add(u);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
		}
	}
}

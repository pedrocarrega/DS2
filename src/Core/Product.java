package Core;

import java.util.ArrayList;
import java.util.List;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;

public class Product {
	
	private static Bezirk bezirk;
	private static List <Class<? extends Event>> listEvents = new ArrayList<>();
	private static Listen listen = null;

	public static void main(String[] args) {

		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Template Name");
		
		if(!listEvents.isEmpty()) {
			listen = new Listen(getAlerts(listEvents), bezirk);
			listen.start();
		}



	}


	@SuppressWarnings("unchecked")
	private static Class<? extends Event>[] getAlerts(List<Class<? extends Event>> test) {
		Class<? extends Event>[] result = new Class[test.size()];
		for(int i = 0; i < test.size() ; i++) {
			result[i] = test.get(i);
		}
		return result;
	}

	public static void addEvent(Class<? extends Event> class1) {
		listEvents.add(class1);
		
	}

}

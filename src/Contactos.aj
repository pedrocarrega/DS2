import java.util.ArrayList;
import java.util.List;

import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public aspect Contactos {
	
	before(List<String> contacts) : execution(List.new()) && target(contacts) {
		contacts = new ArrayList<>();
	}
}
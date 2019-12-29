package Events;
import com.bezirk.middleware.messages.Event;

public class MovimentoUpdateEvent extends Event{
	
	private static final long serialVersionUID = 1L;
	
	private String location;
	
	public MovimentoUpdateEvent(String location) {
		this.location = location;
	}

	public String getLoc() {
		return location;
	}


	public String toString() {
		return location;
	}

}

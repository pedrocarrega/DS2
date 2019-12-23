import com.bezirk.middleware.messages.Event;

public class AlertEvent extends Event{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	private String num;
	
	public AlertEvent(String msg, String num) {
		this.msg = msg;
		this.num = num;
	}
	
	public String toString() {
		return "ALERT!!! " + msg + " to " + num;
	}

}

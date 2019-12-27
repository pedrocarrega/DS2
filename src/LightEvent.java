import com.bezirk.middleware.messages.Event;

public class LightEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LightEvent() {}
	
	public String toString() {

		return I18N.getString(Messages.LIGHT);
	}
}

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class Hub {

	public static void main(String[] args) {
		new Hub();
	}
	
	public Hub() {
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Hub");
		System.err.println(Messages.START_HUB);
		
		final EventSet setEvents = new EventSet(AlertEvent.class,  MovimentoUpdateEvent.class);//TODO
		
		setEvents.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				//TODO
				if(event instanceof AlertEvent) {
					AlertEvent alert = (AlertEvent) event;
					System.err.println(alert.toString());
				}
				
			}
		});
		
		bezirk.subscribe(setEvents);
	}

}

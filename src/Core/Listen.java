package Core;
import java.time.LocalDateTime;
import java.util.Map;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import Events.*;

public class Listen extends Thread implements Runnable{

	private final Class<? extends Event>[] events;
	private final Bezirk bezirk;
	
	public Listen(Class<? extends Event>[] alerts, Bezirk bezirk) {
		this.events = alerts;
		this.bezirk = bezirk;
	}
	
	public void start() {
		
		final EventSet setEvents = new EventSet(events);
		
		setEvents.setEventReceiver(new EventSet.EventReceiver() {

			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				//in case we need to do specific stuff
				if (event instanceof AlertEvent) {
					AlertEvent alert = (AlertEvent) event;
					foward(alert);
				}if (event instanceof MovimentoUpdateEvent) {
					//MovimentoUpdateEvent move = (MovimentoUpdateEvent) event;
					bezirk.sendEvent(new LightEvent());
				}

				//print event message
				System.out.println(event.toString());
			}
		});

		bezirk.subscribe(setEvents);
	}

	
	protected void foward(AlertEvent alert) {
		// TODO Auto-generated method stub
	}
	
	
	
	

}

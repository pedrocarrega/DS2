import java.util.ArrayList;
import java.util.List;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

public class Hub {
	
	private static Reminders reminders;
	//private static List<String> contacts;
	private static List <Class<? extends Event>> test = new ArrayList<>();
	
	public static void main(String[] args) {
		
		reminders = new Reminders();
		reminders.start();
		new Hub();
		System.out.println("test: " + test.size());
	}
	
	

	public Hub() {
		
		BezirkMiddleware.initialize();
		final Bezirk bezirk = BezirkMiddleware.registerZirk("Hub");
		System.err.println(I18N.getString(Messages.START_HUB));
		
		final Class<? extends Event>[] events = getAlerts(test);
		
		final EventSet setEvents = new EventSet(events);
		
		//final EventSet setEvents = new EventSet(AlertEvent.class,  MovimentoUpdateEvent.class);//TODO
		
		setEvents.setEventReceiver(new EventSet.EventReceiver() {
			
			@Override
			public void receiveEvent(Event event, ZirkEndPoint sender) {
				System.out.println(event.toString());
				/*
				//TODO
				if(event instanceof AlertEvent) {
					AlertEvent alert = (AlertEvent) event;
					System.err.println(alert.toString());
				}else if(event instanceof MovimentoUpdateEvent) {
					MovimentoUpdateEvent movimento = (MovimentoUpdateEvent) event;
					System.err.println(movimento.toString());
				}else if(event instanceof ReminderEvent) {
					ReminderEvent reminder = (ReminderEvent) event;
					reminders.addReminder(reminder);
				}*/
				
			}
		});
		
		bezirk.subscribe(setEvents);
	}

	public void addEvent(Class<? extends Event> class1) {
		test.add(class1);
		
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends Event>[] getAlerts(List<Class<? extends Event>> test) {
		Class<? extends Event>[] result = new Class[test.size()];
		for(int i = 0; i < test.size() ; i++) {
			result[i] = test.get(i);
		}
		return result;
	}

}

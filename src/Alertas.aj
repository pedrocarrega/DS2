import com.bezirk.middleware.messages.Event;

public aspect Alertas {
	// TODO Auto-generated aspect
	before(Hub hub): initialization(Hub.new()) && target(hub) {
        hub.addEvent(AlertEvent.class);
    }
	
	/*
	pointcut alert(Event event) : target(event) && call (void receiveEvent(Event, ..));
	after(Event event): alert() {
		if (event instanceof AlertEvent) {
			AlertEvent alert = (AlertEvent) event;
			alert.toString();
			
		}
	        System.out.println(I18N.getString(Messages.SENT_SMS));
	}*/
	
	pointcut alert() : call (void receiveEvent(AlertEvent, ..));
	after(): alert() {
		if (event instanceof AlertEvent) {
			AlertEvent alert = (AlertEvent) event;
			alert.toString();
			
		}
	        System.out.println(I18N.getString(Messages.SENT_SMS));
	}
}
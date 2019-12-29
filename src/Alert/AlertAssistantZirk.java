package Alert;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import Events.AlertEvent;

public class AlertAssistantZirk{
    public AlertAssistantZirk() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Asthma Assistant Zirk");

        final EventSet airQualityEvents = new EventSet(AlertEvent.class);

        airQualityEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                //Check if this event is of interest
                if (event instanceof AlertEvent) {
                    final AlertEvent aqUpdate = (AlertEvent) event;
                    
                    //do something in response to this event
                    System.out.println(aqUpdate);
                }
            }
        });
        bezirk.subscribe(airQualityEvents);
    }
}

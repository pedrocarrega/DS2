package Pulsacao;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import Events.PulsacaoEvent;
import i18n.I18N;
import i18n.Messages;

public class PulsacaoAssistantZirk{
    public PulsacaoAssistantZirk() {
        BezirkMiddleware.initialize();
        final Bezirk bezirk = BezirkMiddleware.registerZirk("Asthma Assistant Zirk");

        final EventSet airQualityEvents = new EventSet(PulsacaoEvent.class);

        airQualityEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                //Check if this event is of interest
                if (event instanceof PulsacaoEvent) {
                    final PulsacaoEvent aqUpdate = (PulsacaoEvent) event;
                    
                    //do something in response to this event
                    if(aqUpdate.getHeartRate() > 120) {
                    	System.out.println(I18N.getString(Messages.HIGH_HTR));
                    }else if(aqUpdate.getHeartRate() < 50) {
                    	System.out.println(I18N.getString(Messages.LOW_HTR));
                    }
                }
            }
        });
        bezirk.subscribe(airQualityEvents);
    }
}
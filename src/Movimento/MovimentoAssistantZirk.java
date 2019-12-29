package Movimento;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import Events.AlertEvent;
import Events.MovimentoUpdateEvent;
import i18n.I18N;
import i18n.Messages;

public class MovimentoAssistantZirk{
	

	private LocalDateTime lastMvmTime;
	private MovimentoUpdateEvent lastMvmEvt;
	private final Bezirk bezirk;
	private boolean killTrigger = true;
	private boolean flagActivity = true;
	
    public MovimentoAssistantZirk() {
    	lastMvmTime = LocalDateTime.now();
    	lastMvmEvt = new MovimentoUpdateEvent(I18N.getString(Messages.LOC_UNDEFINED));
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Asthma Assistant Zirk");

        final EventSet airQualityEvents = new EventSet(MovimentoUpdateEvent.class);

        airQualityEvents.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint sender) {
                //Check if this event is of interest
                if (event instanceof MovimentoUpdateEvent) {
                    final MovimentoUpdateEvent aqUpdate = (MovimentoUpdateEvent) event;
                    lastMvmEvt = aqUpdate;
                    flagActivity = true;
                    lastMvmTime = LocalDateTime.now();
                }
            }
        });
        bezirk.subscribe(airQualityEvents);
    }
    
    public void checkInactivity(int inactivityTime, int startHourAct, int endHourAct, int startNoMov, int endNoMov) {
		
		Thread thread = new Thread(){
			public void run(){
				while(killTrigger) {
					LocalDateTime now = LocalDateTime.now();
					int minutesPassed = (now.getHour()*60+now.getMinute()) - (lastMvmTime.getHour()*60+lastMvmTime.getMinute());
					if(minutesPassed >= inactivityTime && lastMvmTime.getHour() >= startHourAct && lastMvmTime.getHour() <= endHourAct) { //SEttar o periodo pela qual isto é despoletado
						/*for(Entry<String, String> cont : contactos.entrySet()) {
					    		  bezirk.sendEvent(new AlertEvent("Inatividade durante " + inactivityTime + "mn no periodo", cont.getValue()));
					    	  }*/
						//Por agr so manda para o 112
						bezirk.sendEvent(new AlertEvent("Inatividade durante " + inactivityTime + "mn no periodo", "112"));
						lastMvmTime = LocalDateTime.now();
					}else if(flagActivity && lastMvmTime.getHour() >= startNoMov && lastMvmTime.getHour() <= endNoMov) {
						/*for(Entry<String, String> cont : contactos.entrySet()) {
					    		  bezirk.sendEvent(new AlertEvent("Deteção de actividade - " + lastMovEvt + " - " + lastMvm.getHour() + ":" + lastMvmEvt.getMinute(), cont.getValue()));
					    	  }*/
						//Por agr so manda para o 112
						bezirk.sendEvent(new AlertEvent("Deteção de actividade - " + lastMvmEvt + " - " + lastMvmTime.getHour() + ":" + lastMvmTime.getMinute(), "112"));
						lastMvmTime = LocalDateTime.now();
						flagActivity = false;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}//dorme 1min
				}
			}
		};
		 thread.start();
	}
    
    public void changePatterns(Scanner sc) {
    	
    	int inactivityTime; int startHourAct; int endHourAct; int startNoMov; int endNoMov;
    	System.out.println(I18N.getString(Messages.INACT_TIME));
    	inactivityTime = Integer.parseInt(sc.nextLine());
    	System.out.println(I18N.getString(Messages.START_HOUR_ACT));
    	startHourAct = Integer.parseInt(sc.nextLine());
    	System.out.println(I18N.getString(Messages.END_HOUR_ACT));
    	endHourAct = Integer.parseInt(sc.nextLine());
    	System.out.println(I18N.getString(Messages.START_NO_MOVE));
    	startNoMov = Integer.parseInt(sc.nextLine());
    	System.out.println(I18N.getString(Messages.END_NO_MOVE));
    	endNoMov = Integer.parseInt(sc.nextLine());
    	
    	killTrigger = false;
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}//Para ter a certeza que a thread parou
    	killTrigger = true;
    	checkInactivity(inactivityTime, startHourAct, endHourAct, startNoMov, endNoMov);// Assim incializa de novo com os novos padroes
    }
}

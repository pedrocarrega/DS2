import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public aspect Alertas {
	// TODO Auto-generated aspect
	before(Hub hub): execution(* *.initialize()) && target(hub) {
        hub.addEvent(AlertEvent.class);
    }
	
}
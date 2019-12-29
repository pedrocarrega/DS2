package Core;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

import Events.*;

public class Botao15s {

	private static Bezirk bezirk;

	public static void main(String[] args) throws InterruptedException {

		
		
		BezirkMiddleware.initialize();
		bezirk = BezirkMiddleware.registerZirk("Test");
		
		bezirk.sendEvent(new MovimentoUpdateEvent("Damaia"));
		
		/*while(true) {
			AlertEvent alert = new AlertEvent("Test", "112");
			bezirk.sendEvent(alert);
			System.out.println("Enviei1");
			//MovimentoUpdateEvent move = new MovimentoUpdateEvent(0, 0, 0);
			//bezirk.sendEvent(move);
			//System.out.println("Enviei2");
			Thread.sleep(30000);
		}*/
		
		//BezirkMiddleware.stop();
	}

}

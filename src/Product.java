import java.util.List;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class Product {
	
	private static Bezirk bezirk;
	private static List<String> contacts;

	public static void main(String[] args) throws InterruptedException {
		 System.err.println(I18N.getString(Messages.OPTION_MSG));
		 
		 BezirkMiddleware.initialize();
	     bezirk = BezirkMiddleware.registerZirk("Test");
	     while(true) {
	     AlertEvent alert = new AlertEvent("Test", "112");
	     bezirk.sendEvent(alert);
	     System.out.println("Enviei");
	     Thread.sleep(500);
	     }

	}

}

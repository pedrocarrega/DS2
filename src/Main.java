import java.util.List;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class Main {
	
	private static Bezirk bezirk;
	private static List<String> contacts;

	public static void main(String[] args) {
		 System.err.println(I18N.getString(Messages.OPTION_MSG));
		 
		 BezirkMiddleware.initialize();
	     bezirk = BezirkMiddleware.registerZirk("Test");

	}

}

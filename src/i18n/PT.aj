package i18n;

public aspect PT {
	before() : execution(public static void main(String[])) {
		I18N.setInstance(new I18N("pt","PT"));
	}
}

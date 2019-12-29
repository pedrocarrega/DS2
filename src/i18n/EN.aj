package i18n;

public aspect EN {
	before() : execution(public static void main(String[])) {
		I18N.setInstance(new I18N("en","US"));
	}
}

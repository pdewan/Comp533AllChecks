package gradingTools;

public class Comp524Driver {
	public static void main (String[] args) {
		Driver.main(args);
//		System.exit(0);
		for(String s : args) {
			if (s.matches("-.*?[hH][eE][aA][dD][lL][eE][sS]{2}.*")) { // any variety of -.*headless.* with any capitalization
				System.out.println("Exiting driver");
				System.exit(0);
			}
		}
	}
}

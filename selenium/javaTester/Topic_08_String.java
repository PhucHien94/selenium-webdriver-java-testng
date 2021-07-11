package javaTester;

import com.sun.jna.platform.win32.WinDef.HFONT;

public class Topic_08_String {

	public static void main(String[] args) {
		String actualText = " 	Text with Tab 	";
		System.out.println(actualText);
		System.out.println(actualText.trim());

		String user = "admin";
		String pass = "admin";
		String href = "http://the-internet.herokuapp.com/basic_auth";

		String[] hrefValue = href.split("//");

		href = hrefValue[0] + "//" + user + ":" + pass + "@" + hrefValue[1];

	}
}

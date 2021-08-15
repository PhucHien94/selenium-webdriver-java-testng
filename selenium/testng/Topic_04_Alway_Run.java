package testng;

import org.testng.annotations.Test;

public class Topic_04_Alway_Run {
	
	@Test(groups = "user")
	public void TC_01_Create_User() {
		
	}
	
	@Test(groups = {"user","admin"})
	public void TC_02_View_User() {
		
	}
	
	@Test(groups = {"user","admin"})
	public void TC_03_Edit_User() {
		
	}
	
	@Test(groups = {"user","admin"})
	public void TC_04_Delete_User() {
		
	}

}

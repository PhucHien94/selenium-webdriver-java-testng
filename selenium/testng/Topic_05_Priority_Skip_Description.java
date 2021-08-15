package testng;

import org.testng.annotations.Test;

public class Topic_05_Priority_Skip_Description {
	
	@Test(description="Create user > description JIRA#4525")
	public void Create_User() {
		
	}
	
	@Test
	public void View_User() {
		
	}
	
	@Test
	public void Edit_User() {
		
	}
	
	@Test
	public void Delete_User() {
		
	}

	//@Test(priority=1)
	//Skip: @Test(enable=true/false)
	
}

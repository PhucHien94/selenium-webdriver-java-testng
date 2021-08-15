package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test
	public void TC_01() {
		String employeeName = "Tran van a";
		Object address = null;
		
		// dung de kiem tra 1 dk mong muon tra ve la dung (true)
		Assert.assertTrue(employeeName.equals("Tran van a"));
		//Assert.assertTrue(employeeName.equals("Tran van b"),"Employee is not equal");
		
		//Dung de kiem tra 1 dk mong muon tra ve la sai (false)
		Assert.assertFalse(employeeName.equals("tran van c"));
		
		//Dung de kiem tra 2 dieu kien bang nhau
		Assert.assertEquals(employeeName, "Tran van a");
		
		Assert.assertNull(address);
		
		address = "test";
		
		//Assert.assertNull(address);
	}
}

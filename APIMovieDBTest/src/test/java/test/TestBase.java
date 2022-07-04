package test;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class TestBase 
{
	
	@BeforeTest 
	  void startDockerGrid() throws InterruptedException, IOException 
	  {
	   
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
	    Thread.sleep(180000); 
	 
       	  
	  }
	
	@AfterTest
	  
	  void stopDockerGrid() throws InterruptedException, IOException {
	  Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
	  Thread.sleep(20000); //Closes prompt
	//  Runtime.getRuntime().exec("taskkill /f /im cmd.exe"); 
	  }

}

package api.test;

import api.utilities.ExtentReportManager;

import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;

public class testinkolang {
	//static userTest tes;
	
	public static void main(String[] args) {
		TestNG testNG = new TestNG();
        List<Class<?>> testClasses = Arrays.asList(
        		 userTest.class,
        		 storeTest.class
               
                // Add more test classes as needed
            );
        // Set the test classes
        testNG.setTestClasses(testClasses.toArray(new Class[0]));
        
     // Add a TestNG listener programmatically
        testNG.addListener(new ExtentReportManager()); // Replace with the actual listener class

        // Run the tests
        testNG.run();

	}

}

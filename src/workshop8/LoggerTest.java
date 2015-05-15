package workshop8;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void test() {
		Logger logger = Logger.getInstance();
		assertEquals(logger.getRecord(0), "10/20/2014 there was a problem loading data in ChicagoPizzaFactory");
		assertEquals(logger.getRecord(1), "10/22/2014 there was a problem with data in Decorator pattern run");
		assertEquals(logger.getRecord(2), "computer to shut down");
		assertEquals(logger.getRecord(3), "computer back up");
		assertEquals(logger.getRecord(4), "10/31/2014 1:16 pm singleton pattern project crashed badly");
		logger.addRecord("hello");
		logger.close();
	}

}

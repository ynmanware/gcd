package com.ynm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwentyUserConcurrencyTest {

	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Test
	public void test() {
		UserController uc = new UserController();
		for (int i = 0; i < 25; i++) {
			uc.waitAndGo("" + i);
			logger.info("" + i);
			uc.waitAndGo("" + (i - 1));
			logger.info("" + (i - 1));
		}
	}
}

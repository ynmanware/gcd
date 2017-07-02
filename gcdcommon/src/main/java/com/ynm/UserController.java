package com.ynm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Yogesh.Manware
 *
 */
@Component
@Scope("singleton")
public class UserController {
	static final long DURATION = TimeUnit.MINUTES.toMillis(3); // time for each
	private static final Semaphore semaphore = new Semaphore(2);
	static Map<String, Long> account = new HashMap<>();
	private static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	/**
	 * @param key
	 * @throws InterruptedException
	 */
	public void waitAndGo(String key) {

		synchronized (account) {
			for (Iterator<Map.Entry<String, Long>> it = account.entrySet()
					.iterator(); it.hasNext();) {
				Entry<String, Long> entry = it.next();
				if (entry.getKey().equals(key)) {
					return;
				}
			}
		}

		// hold on until you get the lock
		try {
			logger.info("Trying to get Lock.class..............");
			semaphore.acquire();
			logger.info("Got the lock..............");
		} catch (InterruptedException e) {
			logger.error("Something wrong in sharing resurces");

		}
		synchronized (account) {
			account.put(key, System.currentTimeMillis());
		}

		Timer time = new Timer();
		ReleaseLockTask st = new ReleaseLockTask();
		time.schedule(st, 0, 5000);
	}

	/**
	 * runs every 3 minutes to kick the idle users out
	 * 
	 * @author Yogesh.Manware
	 *
	 */
	static class ReleaseLockTask extends TimerTask {
		public void run() {
			synchronized (account) {
				for (Iterator<Map.Entry<String, Long>> it = account.entrySet()
						.iterator(); it.hasNext();) {
					Entry<String, Long> entry = it.next();
					if ((System.currentTimeMillis() - entry.getValue()) > DURATION) {
						it.remove();
						semaphore.release();
					}
				}
			}

		}
	}
}

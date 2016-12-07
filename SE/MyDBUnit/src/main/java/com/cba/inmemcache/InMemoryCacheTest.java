package com.cba.inmemcache;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test class for InMemoryCache
 * 
 * @author Kristoffer
 *
 */
public class InMemoryCacheTest {
	private static final Logger logger = Logger.getLogger(InMemoryCacheTest.class.getName());

	// Would have preferred to use JUnit, but since third party APIs cannot be
	// used, using Java to test.

	public void testBad() throws InterruptedException {
		try {
			long timeToLiveSeconds = 0L;
			new InMemoryCache<String, String>(timeToLiveSeconds);
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE,
					"expected: timeToLiveSeconds must be greater than 0. ; actual: "
							+ e.getMessage());
		}

		try {
			long timeToLiveSeconds = -11L;
			new InMemoryCache<String, String>(timeToLiveSeconds);
		} catch (IllegalArgumentException e) {
			logger.log(Level.SEVERE,
					"expected: timeToLiveSeconds must be greater than 0. ; actual: "
							+ e.getMessage());
		}

	}

	public void testOne() throws InterruptedException {
		long timeToLiveSeconds = 10L;
		InMemoryCache<String, String> cache = new InMemoryCache<String, String>(
				timeToLiveSeconds);

		cache.put("Dili", "East Timor");
		cache.put("Canberra", "Australia");
		cache.put("Montevideo", "Uruguay");
		String value = cache.get("Dili");
		logger.log(Level.INFO, "expected: East Timor ; actual: " + value);
		Thread.sleep(timeToLiveSeconds * 1000 - 2000);
		value = cache.get("Dili");
		logger.log(Level.INFO, "expected: East Timor ; actual: " + value);
		Thread.sleep(timeToLiveSeconds * 1000 - 2000);
		value = cache.get("Dili");
		logger.log(Level.INFO, "expected: East Timor ; actual: " + value);

		Thread.sleep(timeToLiveSeconds * 1000 + 2000);
		value = cache.get("Dili");
		logger.log(Level.INFO, "expected: null ; actual: " + value);
	}

	public void testConcurrent() throws InterruptedException {
		final long timeToLiveSeconds = 10L;
		final InMemoryCache<String, String> cache = new InMemoryCache<String, String>(
				timeToLiveSeconds);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				cache.put("Dili", "East Timor");
				cache.put("Canberra", "Australia");
				cache.put("Montevideo", "Uruguay");
				String value = cache.get("Dili");
				logger.log(Level.INFO, "expected: East Timor ; actual: " + value);

				try {
					Thread.sleep(timeToLiveSeconds * 1000 - 2000);
				} catch (InterruptedException e) {
				}
				value = cache.get("Dili");
				logger.log(Level.INFO, "expected: East Timor ; actual: " + value);

				try {
					Thread.sleep(timeToLiveSeconds * 1000 - 2000);
				} catch (InterruptedException e) {
				}
				value = cache.get("Dili");
				logger.log(Level.INFO, "expected: East Timor ; actual: " + value);

				try {
					Thread.sleep(timeToLiveSeconds * 1000 + 2000);
				} catch (InterruptedException e) {
				}
				value = cache.get("Dili");
				logger.log(Level.INFO, "expected: null ; actual: " + value);
			}

		};

		for (int i = 0; i < 10; i++) {
			new Thread(r).start();

		}
	}

	public static void main(String[] args) throws InterruptedException {
		InMemoryCacheTest test = new InMemoryCacheTest();
		test.testBad();
		test.testOne();
		test.testConcurrent();
	}
}

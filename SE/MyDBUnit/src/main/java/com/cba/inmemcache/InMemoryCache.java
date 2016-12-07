package com.cba.inmemcache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a simple in memory cache which accepts timeToLiveSeconds as a
 * parameter, and cleans up cache when timeToLiveSeconds passes. The timeout is
 * reset when the object is accessed via get method.
 * 
 * @author Kristoffer
 *
 * @param <K>
 *            key
 * @param <V>
 *            value
 */
public class InMemoryCache<K, V> {
	private static final Logger logger = Logger.getLogger(InMemoryCache.class
			.getName());
	private long timeToLiveMillis;

	// Assumption: memory will be enough to accommodate load
	// Using ConcurrentHashMap so map is thread safe.
	private Map<K, CacheObject> map = new ConcurrentHashMap<K, CacheObject>();

	// Constructor
	public InMemoryCache(final long timeToLiveSeconds) {
		// Validate params
		if (timeToLiveSeconds <= 0) {
			throw new IllegalArgumentException(
					"timeToLiveSeconds must be greater than 0.");
		}

		// Assign params
		this.timeToLiveMillis = timeToLiveSeconds * 1000;

		Thread t = new Thread() {
			public void run() {
				while (true) {
					try {
						// Check every quarter of the time to live
						Thread.sleep(timeToLiveMillis / 4);
					} catch (InterruptedException e) {
						// Ignore
					}
					cleanupCache();
				}
			}
		};
		// Since this is an infinite loop thread, set daemon to true
		// so that it doesn't prevent JVM from exiting when program is done
		t.setDaemon(true);
		t.start();
	}

	public void put(K key, V value) {
		map.put(key, new CacheObject(value));
		// Ideall
		logger.log(Level.INFO, "Added key: " + key);
	}

	public V get(K key) {
		CacheObject co = map.get(key);
		if (co != null) {
			// Reset the last accessed field to current time everytime it is
			// accessed
			co.setLastAccessed(System.currentTimeMillis());
			logger.log(Level.INFO, "Got value: " + co.getValue() + " for key: "
					+ key);
			return co.getValue();
		}
		logger.log(Level.INFO, "No value for key: " + key);
		return null;
	}

	public void remove(K key) {
		map.remove(key);
		logger.log(Level.INFO, "Removed key: " + key);
	}

	public int size() {
		return map.size();
	}

	private void cleanupCache() {
		long now = System.currentTimeMillis();

		for (Map.Entry<K, CacheObject> entry : map.entrySet()) {
			CacheObject co = entry.getValue();
			if (now > timeToLiveMillis + co.getLastAccessed()) {
				// Expired
				// ConcurrentHashmap does not throw
				// ConcurrentModificationException, so ok to do this
				map.remove(entry.getKey());
				logger.log(Level.INFO, "Removed key: " + entry.getKey());
			}
			// This loop may take a while, so be polite to other Threads that
			// may want to put or get from map.
			Thread.yield();
		}
	}

	/**
	 * This is an inner class that encapsulates the value, and adds a
	 * lastAccessed field to keep track when it was last accessed.
	 * 
	 * @author Kristoffer
	 *
	 */
	class CacheObject {
		private long lastAccessed = System.currentTimeMillis();
		private V value;

		public CacheObject(V value) {
			this.value = value;
		}

		V getValue() {
			return value;
		}

		long getLastAccessed() {
			return lastAccessed;
		}

		// Need to expose this field for update
		void setLastAccessed(long lastAccessed) {
			this.lastAccessed = lastAccessed;
		}

	}
}

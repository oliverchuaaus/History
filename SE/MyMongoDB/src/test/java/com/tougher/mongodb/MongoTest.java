package com.tougher.mongodb;

import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * Unit test for simple App.
 */
public class MongoTest extends TestCase {

	public void testGetDBs() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		List<String> dbs = mongo.getDatabaseNames();
		System.out.println("dbs:");
		for (String db : dbs) {
			System.out.println(db);
		}
		System.out.println("\n");
	}

	public void testGetColls() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mongodb-in-action");
		Set<String> colls = db.getCollectionNames();
		System.out.println("colls:");
		for (String coll : colls) {
			System.out.println(coll);
		}
		System.out.println("\n");

	}

	public void testGetDocuments() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mongodb-in-action");
		DBCollection coll = db.getCollection("users");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "Kylie");
		DBCursor cursor = coll.find(searchQuery);
		System.out.println("documents:");
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		System.out.println("\n");
	}

	public void testCreate() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("mongo-test");
		DBCollection coll = db.getCollection("user");
		BasicDBObject document = new BasicDBObject();
		document.put("name", "tougher");
		document.put("age", 30);
		document.put("createDate", new Date(77, 5, 21));
		coll.insert(document);

		coll = db.getCollection("user");
		BasicDBObject searchQuery = new BasicDBObject();
		DBCursor cursor = coll.find(searchQuery);
		System.out.println("documents:");
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			assertEquals("tougher", doc.get("name"));
			assertEquals(30, doc.get("age"));
			assertEquals(new Date(77, 5, 21), doc.get("createDate"));
		}
		System.out.println("\n");

	}
}

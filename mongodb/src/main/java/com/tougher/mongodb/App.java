package com.tougher.mongodb;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UnknownHostException
    {
        System.out.println( "DBS: " );
    	MongoClient mongo = new MongoClient( "localhost" , 27017 );
    	List<String> dbs = mongo.getDatabaseNames();
    	for(String db : dbs){
    		System.out.println(db);
    	}
    	
    	
        System.out.println( "Tables in mongodb-in-action: " );
    	DB db = mongo.getDB("mongodb-in-action");
    	Set<String> colls = db.getCollectionNames();
    	for(String coll : colls){
    		System.out.println(coll);
    	}
    	
        System.out.println( "Records in users: " );
    	DBCollection coll = db.getCollection("users");
    	BasicDBObject searchQuery = new BasicDBObject();
    	searchQuery.put("username", "kylie");
    	DBCursor cursor = coll.find(searchQuery);
    	while (cursor.hasNext()) {
    		System.out.println(cursor.next());
    	}
    }
}

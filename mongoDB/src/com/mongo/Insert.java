package com.mongo;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Insert {

	public static void main(String[] args) {
		
		MongoClient mongo;
		try {
			mongo = new MongoClient( "127.0.0.1" , 27017 );
			DB db = mongo.getDB("wiremockdb");
			DBCollection table = db.getCollection("services");
			
			BasicDBObject document = new BasicDBObject();
			document.put("name", "name");
			document.put("path", "path");
			document.put("apikey","apikey");
			document.put("userid", "userid");
			document.put("method", "method");
			document.put("consumes","consumes");
			Date now = new Date();
			document.put("date", now);
		 
			BasicDBObject documentResponse = new BasicDBObject();
			documentResponse.put("status", "status");
			documentResponse.put("produces", "produces");
			documentResponse.put("body", "body");
			document.put("response", documentResponse);
			
			BasicDBObject documentDetails = new BasicDBObject();
			documentDetails.put("description", "description");
			documentDetails.put("attributes", "attributes");
			document.put("details", documentDetails);
				 
			table.insert(document);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  

	}

}

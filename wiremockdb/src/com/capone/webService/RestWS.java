package com.capone.webService;

import java.util.Date;
import java.util.Properties;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Path("/mongodb")
public class RestWS {
	String MONGO_HOST_IP = null;
	int MONGO_HOST_PORT = 0;
	String MONGO_DB_NAME = null;
	String MONGO_COLLECTION_NAME = null;
	static MongoClient mongo = null;
	static DB db = null;
	static DBCollection table = null;

	public RestWS() {
		try {

			Properties prop = new Properties();

			// property file is in classpath
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties"));
			MONGO_HOST_IP = prop.getProperty("MONGO_HOST_IP");
			MONGO_HOST_PORT = Integer.parseInt(prop
					.getProperty("MONGO_HOST_PORT"));
			MONGO_DB_NAME = prop.getProperty("MONGO_DB_NAME");
			MONGO_COLLECTION_NAME = prop.getProperty("MONGO_COLLECTION_NAME");
			mongo = new MongoClient(MONGO_HOST_IP, MONGO_HOST_PORT);
			db = mongo.getDB(MONGO_DB_NAME);
			table = db.getCollection(MONGO_COLLECTION_NAME);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	// This method will retrieve all record in mongoDB
	@SuppressWarnings("finally")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllDetails() {
		String result = "";
		try {

			DBCursor cursor = table.find();
			while (cursor.hasNext()) {
				result = result + "\n" + cursor.next().toString();
			}
		} catch (Exception e) {
			result = "";
		} finally {
			if (result.equals("")) {
				return Response.ok("{}", MediaType.APPLICATION_JSON).build();
			} else {
				return Response.ok(result, MediaType.APPLICATION_JSON).build();
			}
		}
	}

	// This method will retrieve record for particular id provided

	@SuppressWarnings("finally")
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetailsWithParam(@PathParam("id") String id) {
		DBObject dbObj = null;
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(id));
			dbObj = table.findOne(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbObj == null || dbObj.toString().length() == 0) {
				return Response.ok("{}", MediaType.APPLICATION_JSON).build();
			} else {
				return Response
						.ok(dbObj.toString(), MediaType.APPLICATION_JSON)
						.build();
			}
		}
	}

	// This method is called if POST is requested
	@SuppressWarnings("finally")
	@POST
	public Response post(MultivaluedMap<String, String> formParams) {
		String status = "";
		boolean responseFlag = false;
		boolean detailsFlag = false;
		try {
			if (!formParams.containsKey("name")
					|| formParams.getFirst("name").trim().length() == 0) {
				throw new Exception("Name field canot be null");
			}
			BasicDBObject document = new BasicDBObject();
			if (formParams.containsKey("name")) {
				document.put("name", formParams.getFirst("name"));
			}
			if (formParams.containsKey("path")) {
				document.put("path", formParams.getFirst("path"));
			}
			if (formParams.containsKey("apikey")) {
				document.put("apikey", formParams.getFirst("apikey"));
			}
			if (formParams.containsKey("userid")) {
				document.put("userid", formParams.getFirst("userid"));
			}
			if (formParams.containsKey("method")) {
				document.put("method", formParams.getFirst("method"));
			}
			if (formParams.containsKey("consumes")) {
				document.put("consumes", formParams.getFirst("consumes"));
			}
			Date now = new Date();
			document.put("date", now);

			BasicDBObject documentResponse = new BasicDBObject();
			if (formParams.containsKey("status")) {
				documentResponse.put("status", formParams.getFirst("status"));
				responseFlag = true;
			}
			if (formParams.containsKey("produces")) {
				documentResponse.put("produces",
						formParams.getFirst("produces"));
				responseFlag = true;
			}
			if (formParams.containsKey("body")) {
				documentResponse.put("body", formParams.getFirst("body"));
				responseFlag = true;
			}
			if (responseFlag == true) {
				document.put("response", documentResponse);
			}

			BasicDBObject documentDetails = new BasicDBObject();
			if (formParams.containsKey("description")) {
				documentDetails.put("description",
						formParams.getFirst("description"));
				detailsFlag = true;
			}
			if (formParams.containsKey("attributes")) {
				documentDetails.put("attributes",
						formParams.getFirst("attributes"));
				detailsFlag = true;
			}
			if (detailsFlag == true) {
				document.put("details", documentDetails);
			}

			table.insert(document);
			status = "Inserted Successfully";
		} catch (Exception E) {

			status = E.getMessage().toString();
		} finally {
			if (status.equals("Inserted Successfully")) {
				return Response.status(201).entity(status).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(status).build();
			}
		}

	}

	// This method is called if PUT request
	@SuppressWarnings("finally")
	@PUT
	@Path("{id}")
	public Response putUpdate(@PathParam("id") String id,
			MultivaluedMap<String, String> formParams) {

		String status = "";
		try {
			if (id.trim().length() == 0) {
				throw new Exception("Id not provided");
			}
			if (formParams.isEmpty()) {
				throw new Exception("No parameter passed");
			}
			if (formParams.containsKey("name")) {
				if (formParams.getFirst("name").trim().length() == 0) {
					throw new Exception("Name field canot be null");
				}
			}
			BasicDBObject newDocument = new BasicDBObject();

			if (formParams.containsKey("name")) {
				newDocument.append("name", formParams.getFirst("name"));
			}
			if (formParams.containsKey("path")) {
				newDocument.append("path", formParams.getFirst("path"));
			}
			if (formParams.containsKey("apikey")) {
				newDocument.append("apikey", formParams.getFirst("apikey"));
			}
			if (formParams.containsKey("userid")) {
				newDocument.append("userid", formParams.getFirst("userid"));
			}
			if (formParams.containsKey("method")) {
				newDocument.append("method", formParams.getFirst("method"));
			}
			if (formParams.containsKey("consumes")) {
				newDocument.append("consumes", formParams.getFirst("consumes"));
			}
			if (formParams.containsKey("status")) {
				newDocument.append("response.status",
						formParams.getFirst("status"));
			}
			if (formParams.containsKey("produces")) {
				newDocument.append("response.produces",
						formParams.getFirst("produces"));
			}
			if (formParams.containsKey("body")) {
				newDocument
						.append("response.body", formParams.getFirst("body"));
			}
			if (formParams.containsKey("description")) {
				newDocument.append("details.description",
						formParams.getFirst("description"));
			}
			if (formParams.containsKey("attributes")) {
				newDocument.append("details.attributes",
						formParams.getFirst("attributes"));
			}

			DBObject update = new BasicDBObject("$set", newDocument);

			BasicDBObject query = new BasicDBObject();
			query.append("_id", new ObjectId(id));
			table.update(query, update);
			status = "Updated Successfully";
		} catch (Exception E) {
			status = E.getMessage();
		} finally {
			if (status.equals("Updated Successfully")) {
				return Response.status(201).entity(status).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(status).build();
			}
		}

	}

	// This method is called when deleting record....
	@SuppressWarnings("finally")
	@DELETE
	@Path("{id}")
	public Response deleteRec(@PathParam("id") String id) {

		String status = "";
		try {
			if (id.trim().length() == 0) {
				throw new Exception("Id not provided");
			}

			BasicDBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(id));
			table.remove(query);

			status = "Deleted Successfully";
		} catch (Exception E) {
			status = E.getMessage();
		} finally {
			if (status.equals("Deleted Successfully")) {
				return Response.status(201).entity(status).build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(status).build();
			}
		}

	}

}
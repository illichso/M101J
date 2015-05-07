package com.tengen.week2;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class StudentsAssignment {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");

//        collection.drop();

        // see scratch method
        Bson projection = fields(include("student_id", "score"), excludeId());
//        Bson sort = new Document("student", 1).append("score", 1) ;
        Bson sort = orderBy(ascending("student_id"), ascending("score"));

        List<Document> all = collection.find()
                .projection(projection)
                .sort(sort).into(new ArrayList<Document>());

        List<Document> docs = new ArrayList<>();
        for(int i=0; i<all.size();i+=4){
            docs.add(all.get(i));
//            ids.add(String.valueOf(all.get(i).get("_id")));
        }
        int h=7;

        for(Document doc: docs){
            collection.deleteOne(doc);
        }

        List<Document> left = collection.find().into(new ArrayList<Document>());

        printCollection(left);
    }

    private static void printCollection(List<Document> all) {
        for(int i=0; i<all.size();i+=4){
            System.out.println(all.get(i));
        }
    }

    // these are all the statement I used throughout the lecture.
    private static void scratch(DBCollection collection) {
        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject("age", 24));

        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject("$set", new BasicDBObject("age", 24)));

        collection.update(new BasicDBObject("_id", "alice"),
                new BasicDBObject(new BasicDBObject("gender", "F")));

        collection.update(new BasicDBObject("_id", "frank"),
                new BasicDBObject("$set", new BasicDBObject("age", 24)), true, false);

        collection.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("title", "Dr")), false, true);

        collection.remove(new BasicDBObject("_id", "frank"));
    }

    private static DBCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("UpdateRemoveTest");
        collection.drop();
        return collection;
    }

    private static void printCollection(final DBCollection collection) {
        DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1));
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }

    }
}

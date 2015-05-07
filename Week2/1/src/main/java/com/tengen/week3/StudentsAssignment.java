package com.tengen.week3;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

public class StudentsAssignment {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");


        List<Document> all = collection.find()
                .into(new ArrayList<Document>());

        for (Document anAll : all) {
            List<Document>  newScores = findMinHomeWork(anAll.get("scores"));
            collection.updateOne(new BasicDBObject("_id", anAll.get("_id")),
                    new BasicDBObject("$set", new BasicDBObject("scores", newScores)));
            System.out.println(anAll);
        }


        printCollection(all);
    }

    private static void printCollection(List<Document> all) {
        for(int i=0; i<all.size();i+=4){
            System.out.println(all.get(i));
        }
    }

    private static List<Document> findMinHomeWork(Object scores) {
        List<Document> scoresDoc = (List<Document>)scores;
        List<Document> newScoresDoc = new ArrayList<Document>();
        TreeMap<Double, Document> homework = new TreeMap<>();
        for(Document doc: scoresDoc){
            if(!doc.get("type").equals("homework")){
                newScoresDoc.add(new Document(doc));
            }else{
                homework.put(Double.parseDouble(String.valueOf(doc.get("score"))), doc);
            }
        }
        newScoresDoc.add(homework.lastEntry().getValue());
        return newScoresDoc;
    }

}
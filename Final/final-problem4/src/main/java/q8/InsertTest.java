package q8;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class InsertTest {
    public static void main(String[] args) {
        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("q7");
        MongoCollection<Document> animals = db.getCollection("animals");

        Document animal = new Document("angit aimal", "monkey");

        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "cat");
        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "lion");
        animals.insertOne(animal);
    }
}

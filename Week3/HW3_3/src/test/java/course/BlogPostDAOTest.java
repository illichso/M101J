package course;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import junit.framework.TestCase;
import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class BlogPostDAOTest extends TestCase {
    private BlogPostDAO blogPostDAO;

//    @BeforeClass
//    public void init(){
//        final MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost"));
//        final MongoDatabase blogDatabase = mongoClient.getDatabase("blog");
//
//        blogPostDAO = new BlogPostDAO(blogDatabase);
//    }
//
//    @Test
//    public void testAddPostComment() {
//        blogPostDAO.addPostComment("t11", "post body", "","username");
//    }
}

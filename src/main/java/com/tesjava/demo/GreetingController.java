package com.tesjava.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pojo.zip.Zips;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/api/get/fetch-city-zips")
    public List fetchCity(@RequestParam(value = "name", defaultValue = "") String name){

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("sample_training");

        MongoCollection<Document> coll = database.getCollection("zips");

        FindIterable<Document> iterDoc = coll.find(eq("city",name.toUpperCase()));

        List<String> results = new ArrayList<>();
        for (Document each:iterDoc ){
            results.add(each.toJson());
        }

        return results;
    }

    @PutMapping("/api/put/put-city")
    public String insertCity(){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("sample_training");

        MongoCollection<Document> coll = database.getCollection("zips");

        Zips z = new Zips();
        z.setState("TAMIL NADU");
        z.setCity("COIMBATORE");
        z.setPop("500000");
        coll.insertOne(z);

        return "successful";
    }
}

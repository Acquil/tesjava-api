package com.tesjava.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.pojo.zip.Zips;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.web.bind.annotation.*;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
    public List<String> fetchCity(@RequestParam(value = "name", defaultValue = "") String name){

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

    @PostMapping("/api/post/insert-city")
    public String insertCity(@RequestParam(value="name") String city, @RequestParam(value="state") String state, @RequestParam(value="pop") String pop, @RequestParam(value="zip") String zip){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);


        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase database = mongoClient.getDatabase("sample_training");
        database = database.withCodecRegistry(pojoCodecRegistry);

        MongoCollection<Zips> coll = database.getCollection("zips",Zips.class);

        Zips z = new Zips();
        z.setState(state.toUpperCase());
        z.setCity(city.toUpperCase());
        z.setPop(pop);
        z.setZip(zip);
        coll.insertOne(z);

        return "successful";
    }

    @PutMapping("/api/put/person/")
    public String putPerson(@RequestParam(value="name") String name, @RequestParam(value="address") String address, @RequestParam(value="salary") Integer salary){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);


        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase database = mongoClient.getDatabase("test");
        database = database.withCodecRegistry(pojoCodecRegistry);

        MongoCollection<Person> coll = database.getCollection("users",Person.class);

        Person p = new Person();
        p.setAddress(address);
        p.setName(name);
        p.setSalary(salary);

        coll.deleteOne(eq("name",name.toUpperCase()));
        coll.insertOne(p);
        return "successful";
    }

    @PostMapping("/api/put/person/")
    public String postPerson(@RequestParam(value="name") String name, @RequestParam(value="address") String address, @RequestParam(value="salary") Integer salary){
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);


        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoDatabase database = mongoClient.getDatabase("test");
        database = database.withCodecRegistry(pojoCodecRegistry);

        MongoCollection<Person> coll = database.getCollection("users",Person.class);

        Person p = new Person();
        p.setAddress(address);
        p.setName(name);
        p.setSalary(salary);
        coll.insertOne(p);
        return "successful";
    }

    @GetMapping("/api/get/person")
    public List<String> fetchPerson(@RequestParam(value = "name", defaultValue = "") String name){

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = database.getCollection("users");

        FindIterable<Document> iterDoc = coll.find(eq("name",name.toUpperCase()));

        List<String> results = new ArrayList<>();
        for (Document each:iterDoc ){
            results.add(each.toJson());
        }

        return results;
    }

    @DeleteMapping("/api/get/person")
    public String deletePerson(@RequestParam(value = "name", defaultValue = "") String name){

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://user01:bl4ck4dd3r@cluster0-kooqx.mongodb.net/test?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> coll = database.getCollection("users");

        coll.deleteMany(eq("name",name.toUpperCase()));


        return "Deleted";
    }
}

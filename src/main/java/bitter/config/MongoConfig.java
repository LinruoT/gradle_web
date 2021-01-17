package bitter.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

//注意！！！ billyvps.cf的mongoDB
@Configuration
@EnableMongoRepositories(basePackages = "bitter.mongo.db")
public class MongoConfig extends AbstractMongoConfiguration {
    @Autowired
    Environment env;

    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }

    @Override
    public Mongo mongo() throws Exception {
        ServerAddress serverAddress = new ServerAddress(env.getProperty("mongo.host"));
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        MongoCredential mongoCredential1 = MongoCredential.createMongoCRCredential(env.getProperty("mongo.credential1.username"), env.getProperty("mongo.credential1.database"), env.getProperty("mongo.credential1.password").toCharArray());
        MongoCredential mongoCredential2 = MongoCredential.createMongoCRCredential(env.getProperty("mongo.credential2.username"), env.getProperty("mongo.credential2.database"), env.getProperty("mongo.credential2.password").toCharArray());
        mongoCredentialList.add(mongoCredential1);
        mongoCredentialList.add(mongoCredential2);
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredentialList);
        System.out.println("mongo server address: "+mongoClient.getAddress());
        return mongoClient;
    }



}

package taxcalc;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class ApplicationMongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    protected String getDatabaseName() {
        return "tax-calc";
    }

    @Override
    public MongoClient mongoClient() {
        return activeProfile.equals("dev") ?
                new MongoClient("localhost", 27017) :
                new MongoClient("host.docker.internal", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.baeldung";
    }
}

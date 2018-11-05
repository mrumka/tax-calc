package taxcalc.repositories;

import taxcalc.models.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaxCategoryRepository extends MongoRepository<TaxCategory, String> {

    public TaxCategory findByName(String name);

}

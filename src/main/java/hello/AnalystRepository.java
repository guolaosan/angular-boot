package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalystRepository extends MongoRepository<Analyst, String>{

}

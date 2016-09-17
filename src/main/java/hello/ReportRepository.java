package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String>{
	List<Report> findBySecuFullCodeLike(String code);
	List<Report> findByAuthorLike(String code);
	List<Report> findByDatetimeLike(String datetime);
}

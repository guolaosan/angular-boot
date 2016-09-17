package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GreetingController {

    @Autowired
    private ReportRepository reportRepo;
    
    @RequestMapping("/author/{auth}")
    public List<Report> getReports(@PathVariable("auth") String auth){
    	return reportRepo.findByAuthorLike(auth);
    }
    
    @RequestMapping("/code/{code}")
    public List<Report> getReportsHasCode(@PathVariable("code") String code){
    	return reportRepo.findBySecuFullCodeLike(code);
    }
    @RequestMapping("/date/{date}")
    public List<Report> getReportsInDate(@PathVariable("date") String date){
    	return reportRepo.findByDatetimeLike(date);
    }
}

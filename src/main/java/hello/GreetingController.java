package hello;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Autowired
	private ReportRepository reportRepo;

	@Autowired
	private AnalystRepository anaRepo;

	@Autowired
	private Nine925Repository nine925Repo;

	@RequestMapping("/author/{auth}")
	@CrossOrigin
	public List<Report> getReports(@PathVariable("auth") String auth) {
		return reportRepo.findByAuthorLike(auth);
	}

	@CrossOrigin
	@RequestMapping("/code/{code}")
	public List<Report> getReportsHasCode(@PathVariable("code") String code) {
		return reportRepo.findBySecuFullCodeLike(code);
	}

	@RequestMapping("/date/{date}")
	public List<Report> getReportsInDate(@PathVariable("date") String date) {
		return reportRepo.findByDatetimeLike(date);
	}

	@CrossOrigin
	@RequestMapping("/ana")
	public List<Analyst> getAllAnas() {
		return anaRepo.findAll();
	}

	@CrossOrigin
	@RequestMapping("/925")
	public List<Nine25> getNine25() {
	
		return nine925Repo.findAll(new Sort(new Order(Direction.ASC, "date")));
	}
	
	@CrossOrigin
	@RequestMapping(value = "/925/{date}/{shvol}/{szvol}/", method = RequestMethod.POST)
	public String addNine25(@PathVariable long date,@PathVariable float shvol,@PathVariable float szvol) {
		Nine25 n25 = new Nine25();
		n25.setDate(new Date(date));
		n25.setShvol(shvol);
		n25.setSzvol(szvol);
		nine925Repo.save(n25);
		return "done";
	}
}

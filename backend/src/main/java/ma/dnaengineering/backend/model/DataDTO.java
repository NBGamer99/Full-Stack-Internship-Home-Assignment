package ma.dnaengineering.backend.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class DataDTO {
	List<Employee> employees;
	List<JobSummary> jobSummaries;

	public void clear() {
		employees.clear();
		jobSummaries.clear();
	}
}

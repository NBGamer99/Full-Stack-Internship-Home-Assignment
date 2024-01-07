package ma.dnaengineering.backend.service.data;


import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataProcessingServiceImpl implements DataProcessing {
	@Override
	public List<JobSummary> calculateAverageSalaryByJobTitle(List<Employee> employees) {

		Map<String, List<Employee>> employeesByJobTitle = employees.stream().collect(Collectors.groupingBy(Employee::getJobTitle));
		List<JobSummary> jobSummaries = new ArrayList<>();

		for (Map.Entry<String, List<Employee>> entry : employeesByJobTitle.entrySet()) {
			double averageSalary = entry.getValue().stream().mapToDouble(Employee::getSalary).average().orElse(0);

			JobSummary jobSummary = JobSummary.builder()
					.jobTitle(entry.getKey())
					.averageSalary(averageSalary)
					.build();


			jobSummaries.add(jobSummary);
		}
		return jobSummaries;
	}
}

package ma.dnaengineering.backend.service.data;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;

import java.util.List;

public interface DataProcessing {
	List<JobSummary> calculateAverageSalaryByJobTitle(List<Employee> employees);
}

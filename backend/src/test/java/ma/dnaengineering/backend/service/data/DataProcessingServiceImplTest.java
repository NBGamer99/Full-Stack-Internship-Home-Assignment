package ma.dnaengineering.backend.service.data;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataProcessingServiceImplTest {

	@InjectMocks
	private DataProcessingServiceImpl dataProcessingService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCalculateAverageSalaryByJobTitle() {

		List<Employee> employees = Arrays.asList(
				new Employee(1D, "John Doe", "Software Engineer", 100000D),
				new Employee(2D, "Jane Doe", "Software Engineer", 120000D),
				new Employee(3D, "Bob Smith", "Product Manager", 150000D)
		);
		List<JobSummary> jobSummaries = dataProcessingService.calculateAverageSalaryByJobTitle(employees);
		assertEquals(2, jobSummaries.size());
	}

	@Test
	public void testCalculateAverageSalaryByJobTitle_WithEmptyList() {

		List<Employee> employees = new ArrayList<>();
		List<JobSummary> jobSummaries = dataProcessingService.calculateAverageSalaryByJobTitle(employees);
		assertEquals(0, jobSummaries.size());
	}

	@Test
	public void testCalculateAverageSalaryByJobTitle_WithSingleJobTitle() {
		List<Employee> employees = Arrays.asList(
				new Employee(1D, "John Doe", "Software Engineer", 100000D),
				new Employee(2D, "Jane Doe", "Software Engineer", 120000D)
		);
		List<JobSummary> jobSummaries = dataProcessingService.calculateAverageSalaryByJobTitle(employees);
		assertEquals(1, jobSummaries.size());
		assertEquals("Software Engineer", jobSummaries.get(0).getJobTitle());
		assertEquals(110000, jobSummaries.get(0).getAverageSalary());
	}

	@Test
	public void testCalculateAverageSalaryByJobTitle_WithMultipleJobTitles() {

		List<Employee> employees = Arrays.asList(
				new Employee(1D, "John Doe", "Software Engineer", 100000D),
				new Employee(2D, "Jane Doe", "Software Engineer", 120000D),
				new Employee(3D, "Bob Smith", "Product Manager", 150000D)
		);
		List<JobSummary> jobSummaries = dataProcessingService.calculateAverageSalaryByJobTitle(employees);
		assertEquals(2, jobSummaries.size());
	}
}
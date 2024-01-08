package ma.dnaengineering.backend.service.parser;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.utils.CSVFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVParserServiceImplTest {

	@InjectMocks
	private CSVParserServiceImpl csvParserService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testParseFile_ValidCSV() {
		String content = "iid,employee_name,job_title,salary\n1,John Doe,Software Engineer,100000";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		List<Employee> employees = csvParserService.parseFile(file);

		assertEquals(1, employees.size());
		Employee employee = employees.get(0);
		assertEquals(1, employee.getId());
		assertEquals("John Doe", employee.getName());
		assertEquals("Software Engineer", employee.getJobTitle());
		assertEquals(100000, employee.getSalary());
	}

	@Test
	public void testParseFile_InvalidCSV() {
		String content = "id,name,jobTitle,salary\n1,John Doe,Software Engineer";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		assertThrows(CSVFileException.class, () -> csvParserService.parseFile(file));
	}

	@Test
	public void testParseFile_WithEmptyLine() {
		String content = "id,employee_name,job_title,salary\n\n1,John Doe,Software Engineer,100000";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		List<Employee> employees = csvParserService.parseFile(file);

		assertEquals(1, employees.size());
	}

	@Test
	public void testParseFile_WithInvalidData() {

		String content = "id,employee_name,job_title,salary\n1,John Doe,Software Engineer,invalid";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		assertThrows(CSVFileException.class, () -> csvParserService.parseFile(file));
	}

	@Test
	public void testParseFile_WithMissingData() {
		String content = "id,employee_name,job_title,salary\n1,John Doe,,100000";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		assertThrows(CSVFileException.class, () -> csvParserService.parseFile(file));
	}
}
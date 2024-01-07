package ma.dnaengineering.backend.service.parser;

import lombok.extern.slf4j.Slf4j;
import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.utils.CSVFileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CSVParserServiceImpl implements Parser<Employee> {

	private static final int EXPECTED_FIELD_COUNT = 4;
	@Override
	public List<Employee> parseFile(MultipartFile file) {
		List<Employee> employees = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (lineNumber == 1) {
					continue;
				}
				Employee employee = getEmployee(line, lineNumber);
				employees.add(employee);
			}
		} catch (Exception e) {
			log.error("Failed to parse CSV file: ",e);
			throw new CSVFileException("Failed to parse CSV file: " + e.getMessage(),e);
		}
		return employees;
	}

	private Employee getEmployee(String line, int lineNumber) {
		String[] fields = line.split(",");
		if (fields.length != EXPECTED_FIELD_COUNT) {
			throw new CSVFileException("Invalid CSV file format at line " + lineNumber);
		}

		Double id =  Double.parseDouble(fields[0]);
		String name = fields[1];
		String jobTitle = fields[2];
		Double salary = Double.parseDouble(fields[3]);

		if (id.isNaN() || name.isEmpty() || jobTitle.isEmpty() || salary.isNaN()) {
			throw new CSVFileException("Invalid data at line " + lineNumber);
		}

		return Employee.builder().id(id).name(name).jobTitle(jobTitle).salary(salary).build();
	}
}

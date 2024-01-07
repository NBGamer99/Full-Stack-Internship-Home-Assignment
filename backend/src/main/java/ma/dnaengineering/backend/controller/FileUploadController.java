package ma.dnaengineering.backend.controller;

import lombok.extern.slf4j.Slf4j;
import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import ma.dnaengineering.backend.model.UploadResponse;
import ma.dnaengineering.backend.service.data.DataProcessingServiceImpl;
import ma.dnaengineering.backend.service.parser.CSVParserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
@Slf4j
public class FileUploadController {

	private final CSVParserServiceImpl csvParserService;

	private final DataProcessingServiceImpl dataProcessingService;

	@Autowired
	public FileUploadController(CSVParserServiceImpl csvParserService, DataProcessingServiceImpl dataProcessingService) {
		this.csvParserService = csvParserService;
		this.dataProcessingService = dataProcessingService;
	}

	@GetMapping("/")
	public ResponseEntity<Map<String, String>> helloWorldFromAPI() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "Hello World from API");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			List<Employee> employees = csvParserService.parseFile(file);
			List<JobSummary> jobSummaries = dataProcessingService.calculateAverageSalaryByJobTitle(employees);
			UploadResponse uploadResponse = UploadResponse.builder().employees(employees).jobSummaries(jobSummaries).build();
			log.info("File uploaded successfully : {}", file.getOriginalFilename());
			return new ResponseEntity<>(uploadResponse, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Failed to upload file: ", e);
			UploadResponse uploadResponse = UploadResponse.builder().message("Failed to process file,  " + file.getOriginalFilename()).build();
			return new ResponseEntity<>(uploadResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
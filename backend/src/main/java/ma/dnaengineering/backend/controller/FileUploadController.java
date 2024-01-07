package ma.dnaengineering.backend.controller;

import lombok.extern.slf4j.Slf4j;
import ma.dnaengineering.backend.model.DataDTO;
import ma.dnaengineering.backend.model.UploadResponse;
import ma.dnaengineering.backend.service.data.DataProcessingServiceImpl;
import ma.dnaengineering.backend.service.parser.CSVParserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
@Slf4j
public class FileUploadController {
	private final CSVParserServiceImpl csvParserService;
	private final DataProcessingServiceImpl dataProcessingService;
	private final DataDTO dataDTO;

	@Autowired
	public FileUploadController(CSVParserServiceImpl csvParserService, DataProcessingServiceImpl dataProcessingService, DataDTO dataDTO) {
		this.csvParserService = csvParserService;
		this.dataProcessingService = dataProcessingService;
		this.dataDTO = dataDTO;
	}

	@GetMapping("/")
	public ResponseEntity<Map<String, String>> helloWorldFromAPI() {
		Map<String, String> response = new HashMap<>();
		response.put("message", "Hello World from Backend API");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			if (dataDTO.getEmployees() != null || dataDTO.getJobSummaries() != null)
				dataDTO.clear();

			dataDTO.setEmployees(csvParserService.parseFile(file));
			dataDTO.setJobSummaries(dataProcessingService.calculateAverageSalaryByJobTitle(dataDTO.getEmployees()));
			UploadResponse uploadResponse = UploadResponse.builder().message("File Uploaded Successfully").build();
			log.info("File uploaded successfully : {}", file.getOriginalFilename());
			return new ResponseEntity<>(uploadResponse, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Failed to upload file: ", e);
			UploadResponse uploadResponse = UploadResponse.builder().message("Failed to process file,  " + file.getOriginalFilename()).build();
			return new ResponseEntity<>(uploadResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<UploadResponse> getEmployeesData() {
		UploadResponse uploadResponse = UploadResponse.builder().employees(dataDTO.getEmployees()).build();
		return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
	}

	@GetMapping("/jobs")
	public ResponseEntity<UploadResponse> getJobsData() {
		UploadResponse uploadResponse = UploadResponse.builder().jobSummaries(dataDTO.getJobSummaries()).build();
		return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
	}

	@GetMapping("/data")
	public ResponseEntity<UploadResponse> getProcessedData() {
		UploadResponse uploadResponse = UploadResponse.builder().jobSummaries(dataDTO.getJobSummaries()).employees(dataDTO.getEmployees()).build();
		return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
	}


}
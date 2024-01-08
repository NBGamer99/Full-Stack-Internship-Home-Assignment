package ma.dnaengineering.backend.controller;

import ma.dnaengineering.backend.service.data.DataProcessingServiceImpl;
import ma.dnaengineering.backend.service.parser.CSVParserServiceImpl;
import ma.dnaengineering.backend.utils.CSVFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileUploadControllerTest {

	@InjectMocks
	private FileUploadController fileUploadController;

	@Mock
	private CSVParserServiceImpl csvParserService;

	@Mock
	private DataProcessingServiceImpl dataProcessingService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(fileUploadController).build();
	}

	@Test
	public void testUploadFile_ValidCSV() throws Exception {
		// Arrange
		String content = "id,name,jobTitle,salary\n1,John Doe,Software Engineer,100000";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		// Act & Assert
		mockMvc.perform(multipart("/api/v1/upload").file(file).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk());
	}

	@Test
	public void testUploadFile_InvalidCSV() throws Exception {

		String content = "id,name,jobTitle,salary\n1,John Doe,Software Engineer";
		MockMultipartFile file = new MockMultipartFile("file", "test.csv", "text/csv", content.getBytes());

		mockMvc.perform(multipart("/api/v1/upload").file(file).contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isInternalServerError());
	}
}
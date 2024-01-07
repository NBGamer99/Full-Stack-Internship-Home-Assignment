package ma.dnaengineering.backend.service.parser;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Parser<T> {
	List<T> parseFile(MultipartFile file);
}

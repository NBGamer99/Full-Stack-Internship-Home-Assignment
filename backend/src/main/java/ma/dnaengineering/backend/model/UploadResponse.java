package ma.dnaengineering.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadResponse {
    private List<Employee> employees;
    private List<JobSummary> jobSummaries;
    private String message;
}

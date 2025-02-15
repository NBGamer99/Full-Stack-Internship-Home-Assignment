package ma.dnaengineering.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSummary {
	private String jobTitle;
	private Double averageSalary;
}

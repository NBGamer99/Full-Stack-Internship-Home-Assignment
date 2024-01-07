lspackage ma.dnaengineering.backend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	private Double id;
	private String name;
	private String jobTitle;
	private Double salary;
}

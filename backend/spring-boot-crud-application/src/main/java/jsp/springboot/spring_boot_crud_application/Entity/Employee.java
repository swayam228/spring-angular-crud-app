package jsp.springboot.spring_boot_crud_application.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Long contact;
	private Double salary;
	private String role;
	private String department;
}

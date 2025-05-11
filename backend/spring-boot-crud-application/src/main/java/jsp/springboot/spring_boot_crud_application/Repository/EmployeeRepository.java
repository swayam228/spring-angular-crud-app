package jsp.springboot.spring_boot_crud_application.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.springboot.spring_boot_crud_application.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	Page<Employee> findByNameContainingIgnoreCase(String name, Pageable pageable);
	
	List<Employee> findByRole(String role);
	
	Optional<Employee> findByNameAndContact(String name,Long contact);
	
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	@Query("select e from Employee e where e.contact=8855229966")
	Optional<Employee> getEmployeeByContact();
	
	@Query("select e from Employee e where e.department=:department")
	List<Employee> getEmployeeByDepartment(String department);
	
	@Query("select e from Employee e where e.name=?1 and e.salary=?2")
	List<Employee> getEmployeeByNameAndSalary(String name,Double salary);
}

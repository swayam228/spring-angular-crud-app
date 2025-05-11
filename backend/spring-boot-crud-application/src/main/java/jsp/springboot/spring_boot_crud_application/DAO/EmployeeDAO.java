package jsp.springboot.spring_boot_crud_application.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.springboot.spring_boot_crud_application.Entity.Employee;
import jsp.springboot.spring_boot_crud_application.Repository.EmployeeRepository;

@Repository
public class EmployeeDAO {
	@Autowired
	private EmployeeRepository employeeRepository;
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> getEmployeeById(int id) {
	    return employeeRepository.findById(id);
	}
	
	public Page<Employee> getEmployeeByName(String name, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return employeeRepository.findByNameContainingIgnoreCase(name, pageable);
	}


	public Employee updateEmployee(Employee employee) {
	    return employeeRepository.save(employee);
	}

	public void deleteEmployee(Employee employee) {
	    employeeRepository.delete(employee);
	}

	public List<Employee> getEmployeeByRole(String role) {
	    return employeeRepository.findByRole(role);
	}

	public Optional<Employee> getEmployeeByNameAndContact(String name, Long contact) {
	    return employeeRepository.findByNameAndContact(name, contact);
	}

	public List<Employee> getEmployeeBySalaryGreaterThan(Double salary) {
	    return employeeRepository.findBySalaryGreaterThan(salary);
	}

	public Optional<Employee> getEmployeeByContact() {
	    return employeeRepository.getEmployeeByContact();
	}

	public List<Employee> getEmployeeByDepartment(String department) {
	    return employeeRepository.getEmployeeByDepartment(department);
	}

	public List<Employee> getEmployeeByNameAndSalary(String name, Double salary) {
	    return employeeRepository.getEmployeeByNameAndSalary(name, salary);
	}
	public Page<Employee> getEmployeeWithPagination(int pageNumber,int pageSize){
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	public List<Employee> getEmployeeWithSorting(String field){
		return employeeRepository.findAll(Sort.by(field).ascending());
	}
	public Page<Employee> getEmployeeWithPaginationAndSorting(int pageNumber,int pageSize,String field){
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	}
}

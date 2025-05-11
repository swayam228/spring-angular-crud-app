package jsp.springboot.spring_boot_crud_application.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.springboot.spring_boot_crud_application.DAO.EmployeeDAO;
import jsp.springboot.spring_boot_crud_application.DTO.ResponseStructure;
import jsp.springboot.spring_boot_crud_application.Entity.Employee;
import jsp.springboot.spring_boot_crud_application.Exception.IdNotFoundException;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDAO employeeDao;
	
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee){
		Employee savedEmployee=employeeDao.saveEmployee(employee);
		ResponseStructure<Employee> structure=new ResponseStructure<Employee>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Employee Created");
		structure.setData(savedEmployee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee(){
		List<Employee> employees=employeeDao.getAllEmployee();
		ResponseStructure<List<Employee>> structure=new ResponseStructure<List<Employee>>();
		if(!employees.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Employee Found");
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.OK);
		}else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Employees Not Found");
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(int id) {
	    Optional<Employee> opt = employeeDao.getEmployeeById(id);
	    ResponseStructure<Employee> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        throw new IdNotFoundException();
	    }
	}
	
	public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByName(String name, int page, int size) {
	    Page<Employee> employees = employeeDao.getEmployeeByName(name, page, size);
	    ResponseStructure<Page<Employee>> structure = new ResponseStructure<>();

	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employees Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("No Employees Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}


	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) {
	    Optional<Employee> opt = employeeDao.getEmployeeById(employee.getId());
	    ResponseStructure<Employee> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        Employee updated = employeeDao.updateEmployee(employee);
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Updated Successfully");
	        structure.setData(updated);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found with Id: " + employee.getId());
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<String>> deleteEmployee(int id) {
	    Optional<Employee> opt = employeeDao.getEmployeeById(id);
	    ResponseStructure<String> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        employeeDao.deleteEmployee(opt.get());
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Deleted Successfully");
	        structure.setData("Employee with Id " + id + " has been deleted.");
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        throw new IdNotFoundException();
	    }
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByRole(String role) {
	    List<Employee> employees = employeeDao.getEmployeeByRole(role);
	    ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<Employee>> getEmployeeByNameAndContact(String name, Long contact) {
	    Optional<Employee> opt = employeeDao.getEmployeeByNameAndContact(name, contact);
	    ResponseStructure<Employee> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeBySalaryGreaterThan(Double salary) {
	    List<Employee> employees = employeeDao.getEmployeeBySalaryGreaterThan(salary);
	    ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<Employee>> getEmployeeByContact() {
	    Optional<Employee> opt = employeeDao.getEmployeeByContact();
	    ResponseStructure<Employee> structure = new ResponseStructure<>();
	    if (opt.isPresent()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(opt.get());
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByDepartment(String department) {
	    List<Employee> employees = employeeDao.getEmployeeByDepartment(department);
	    ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByNameAndSalary(String name, Double salary) {
	    List<Employee> employees = employeeDao.getEmployeeByNameAndSalary(name, salary);
	    ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	
	public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeWithPagination(int pageNumber,int pageSize){
		Page<Employee> employeesPage=employeeDao.getEmployeeWithPagination(pageNumber-1, pageSize);
		ResponseStructure<Page<Employee>> structure=new ResponseStructure<Page<Employee>>();
		if (!employeesPage.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employeesPage);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeWithSorting(String field) {
	    List<Employee> employees = employeeDao.getEmployeeWithSorting(field);
	    ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
	    if (!employees.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employees);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
	public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeWithPaginationAndSorting(int pageNumber,int pageSize, String field){
		Page<Employee> employeesPageSorted=employeeDao.getEmployeeWithPaginationAndSorting(pageNumber-1, pageSize, field);
		ResponseStructure<Page<Employee>> structure=new ResponseStructure<Page<Employee>>();
		if (!employeesPageSorted.isEmpty()) {
	        structure.setStatusCode(HttpStatus.OK.value());
	        structure.setMessage("Employee Found");
	        structure.setData(employeesPageSorted);
	        return new ResponseEntity<>(structure, HttpStatus.OK);
	    } else {
	        structure.setStatusCode(HttpStatus.NOT_FOUND.value());
	        structure.setMessage("Employee Not Found");
	        return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
	    }
	}
}

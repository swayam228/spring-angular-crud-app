package jsp.springboot.spring_boot_crud_application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jsp.springboot.spring_boot_crud_application.DTO.ResponseStructure;
import jsp.springboot.spring_boot_crud_application.Entity.Employee;
import jsp.springboot.spring_boot_crud_application.Service.EmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }
    
    @GetMapping("/name")
    public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return employeeService.getEmployeeByName(name, page, size);
    }

    
    @PutMapping
    public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByRole(@PathVariable String role) {
        return employeeService.getEmployeeByRole(role);
    }

    @GetMapping("/name&contact/{name}/{contact}")
    public ResponseEntity<ResponseStructure<Employee>> getEmployeeByNameAndContact(@PathVariable String name,@PathVariable Long contact) {
        return employeeService.getEmployeeByNameAndContact(name, contact);
    }

    @GetMapping("/sal/{salary}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeBySalaryGreaterThan(@PathVariable Double salary) {
        return employeeService.getEmployeeBySalaryGreaterThan(salary);
    }

    @GetMapping("/contact")
    public ResponseEntity<ResponseStructure<Employee>> getEmployeeByContact() {
        return employeeService.getEmployeeByContact();
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByDepartment(@PathVariable String department) {
        return employeeService.getEmployeeByDepartment(department);
    }

    @GetMapping("/name&salary/{name}/{salary}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByNameAndSalary(@PathVariable String name,@PathVariable Double salary) {
        return employeeService.getEmployeeByNameAndSalary(name, salary);
    }
    @GetMapping("/page/{pageNumber}/{pageSize}")
    public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeWithPagination(@PathVariable int pageNumber,@PathVariable int pageSize){
    	return employeeService.getEmployeeWithPagination(pageNumber, pageSize);
    }
    @GetMapping("/sort/{field}")
    public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeWithSorting(@PathVariable String field) {
        return employeeService.getEmployeeWithSorting(field);
    }
    @GetMapping("/page&sort/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Employee>>> getEmployeeWithPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
    	return employeeService.getEmployeeWithPaginationAndSorting(pageNumber, pageSize,field);
    }
}

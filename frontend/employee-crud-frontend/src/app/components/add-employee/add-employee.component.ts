import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeesService } from '../../service/employees.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-employee',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {
  employeeForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private employeeService: EmployeesService,
    private router: Router
  ) {
    this.employeeForm = this.fb.group({
      name: ['', Validators.required],
      contact: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      salary: ['', [Validators.required, Validators.min(0)]],
      role: ['', Validators.required],
      department: ['', Validators.required]
    });
  }

  onSubmit() {
  if (this.employeeForm.valid) {
    this.employeeService.addEmployee(this.employeeForm.value).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Employee Added',
          text: 'The employee has been added successfully!',
          confirmButtonColor: '#3085d6'
        }).then(() => {
          this.router.navigate(['/employees']);
        });
      },
      error: (err) => {
        console.error('Error adding employee:', err);
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Something went wrong while adding the employee!',
          confirmButtonColor: '#d33'
        });
      }
    });
  }
}
}
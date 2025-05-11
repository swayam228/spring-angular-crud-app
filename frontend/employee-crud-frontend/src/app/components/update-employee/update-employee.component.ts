import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeesService } from '../../service/employees.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule],  // Add ReactiveFormsModule here
})
export class UpdateEmployeeComponent implements OnInit {
  employeeForm!: FormGroup;
  employeeId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeesService
  ) {}

ngOnInit() {
  this.employeeId = +this.route.snapshot.paramMap.get('id')!;

  this.employeeForm = this.fb.group({
    name: [''],
    contact: [''],
    salary: [''],
    role: [''],
    department: ['']
  });

  this.employeeService.getEmployeeById(this.employeeId).subscribe((employee: any) => {
    console.log('Fetched employee:', employee); // optional debug log
    this.employeeForm.patchValue(employee.data); // use .data here
  });
}

  onSubmit() {
  if (this.employeeForm.valid) {
    const updatedEmployee = {
      id: this.employeeId,
      ...this.employeeForm.value
    };

    this.employeeService.updateEmployee(updatedEmployee).subscribe({
      next: () => {
        Swal.fire({
          icon: 'success',
          title: 'Employee Updated',
          text: 'The employee details were updated successfully!',
          confirmButtonColor: '#3085d6'
        }).then(() => {
          this.router.navigate(['/employees']);
        });
      },
      error: (error) => {
        console.error('Update failed', error);
        Swal.fire({
          icon: 'error',
          title: 'Update Failed',
          text: 'There was a problem updating the employee.',
          confirmButtonColor: '#d33'
        });
      }
    });
  }
}
}

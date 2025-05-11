import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { EmployeesService } from '../../service/employees.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule], // Add FormsModule here
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {
  employees: any[] = [];
  currentPage: number = 1;
  pageSize: number = 5;
  totalPages: number = 0;
  searchTerm: string = '';

  // Array for pagination
  totalPagesArray: number[] = [];

  constructor(private employeeService: EmployeesService) {}

  ngOnInit() {
    this.fetchEmployees();
  }

  fetchEmployees(): void {
    if (this.searchTerm.trim() === '') {
      // Regular pagination for employee list
      this.employeeService.getEmployee(this.currentPage, this.pageSize).subscribe((response: any) => {
        this.employees = response.data.content;
        this.totalPages = response.data.totalPages;
        this.updateTotalPagesArray();
      });
    } else {
      // Search pagination
      this.employeeService.searchEmployeesByName(this.searchTerm, this.currentPage - 1, this.pageSize).subscribe((response: any) => {
        this.employees = response.data.content;
        this.totalPages = response.data.totalPages;
        this.updateTotalPagesArray();
      });
    }
  }

  // Update the totalPagesArray based on totalPages
  updateTotalPagesArray(): void {
    this.totalPagesArray = Array(this.totalPages).fill(0).map((_, i) => i + 1);
  }

  trackById(index: number, employee: any): number {
    return employee.id;
  }

  onDelete(id: number): void {
  Swal.fire({
    title: 'Are you sure?',
    text: 'This action cannot be undone!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Yes, delete it!'
  }).then((result) => {
    if (result.isConfirmed) {
      this.employeeService.deleteEmployee(id).subscribe(() => {
        this.employees = this.employees.filter(emp => emp.id !== id);

        Swal.fire({
          icon: 'success',
          title: 'Deleted!',
          text: 'The employee has been deleted.',
          timer: 1500,
          showConfirmButton: false
        });
      });
    }
  });
}

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.fetchEmployees();
    }
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.fetchEmployees();
    }
  }

  // Method to handle page change
  goToPage(page: number): void {
    this.currentPage = page;
    this.fetchEmployees();
  }
}
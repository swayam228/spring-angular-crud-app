import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EmployeesService {

  private baseUrl = 'http://localhost:8080/employee';

  constructor(private http: HttpClient) {}

  getEmployee(pageNumber: number = 1, pageSize: number = 10): Observable<any> {
  return this.http.get(`${this.baseUrl}/page/${pageNumber}/${pageSize}`);
}

searchEmployeesByName(name: string, page: number, size: number): Observable<any> {
  return this.http.get(`${this.baseUrl}/name?name=${name}&page=${page}&size=${size}`);
}


  addEmployee(employee: any) {
    return this.http.post(this.baseUrl, employee);
  }
  getEmployeeById(id: number) {
  return this.http.get(`${this.baseUrl}/${id}`);
  }
  updateEmployee(employee: any): Observable<any> {
    return this.http.put(this.baseUrl, employee);
  }  
  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
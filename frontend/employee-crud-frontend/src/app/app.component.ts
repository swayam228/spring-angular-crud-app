import { Component } from '@angular/core';
import { RouterOutlet,RouterModule } from '@angular/router'; // ✅ Required for routing
import { CommonModule } from '@angular/common'; // ✅ Required for ngIf, ngFor, etc.

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RouterModule], // ✅ Add RouterOutlet here
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'employee-crud-frontend';
}
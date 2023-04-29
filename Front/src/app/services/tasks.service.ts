import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class TasksService {
  private byUserUrl = 'http://localhost:8080/api/tasks?email';
  private byIdUrl = 'http://localhost:8080/api/task';

  constructor(private http: HttpClient) { }

  getTaskById(id:number, user: Usuario): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Token': user.token
    });

    return this.http.get(`${this.byIdUrl}/${id}`, { headers });
  }

  getTasksByUser(user:Usuario): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Token': user.token
    });

    return this.http.get(`${this.byUserUrl}=${user.email}`, { headers });
  }
}
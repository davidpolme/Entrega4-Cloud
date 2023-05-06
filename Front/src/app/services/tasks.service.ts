import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import * as FileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class TasksService {
  private byUserUrl = 'http://34.125.146.78:8080/api/tasks?email';
  private byIdUrl = 'http://34.125.146.78:8080/api/task';
  private deleteUrl = 'http://34.125.146.78:8080/api/task?id';
  private addUrl = 'http://34.125.146.78:8080/api/upload';
  private downloadUri = 'http://34.125.146.78:8080/api/download?file'

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

  deleteTask(id: number, user: Usuario){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Token': user.token
    });
    return this.http.delete(`${this.deleteUrl}=${id}`, { headers });
  }

  addTask(formData: FormData){
    return this.http.post(this.addUrl, formData);
  }

  downloadFile(filename: string) {
    this.http.get(`${this.downloadUri}=${filename}`, { responseType: 'blob' })
      .subscribe((data: Blob) => {
        FileSaver.saveAs(data, filename);
      });
  }
}

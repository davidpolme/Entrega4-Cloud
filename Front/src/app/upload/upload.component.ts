import { Component, OnInit } from '@angular/core';
import { TasksService } from '../services/tasks.service';
import { Router } from '@angular/router';
import { Usuario } from '../services/usuario';
import { Archivos } from '../services/task';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  format : string = '';
  selectedFile!: File;
  dateDay = new Date()
  

  constructor(private service: TasksService, private route : Router) { }

  ngOnInit(): void {
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(){
    const usuarioJson = localStorage.getItem('usuario');
    if (usuarioJson !== null) {
      const usuario: Usuario = JSON.parse(usuarioJson);
      const formData: FormData = new FormData();
      formData.append('file', this.selectedFile);
      formData.append('format', this.format);
      formData.append('email', usuario.email);
      this.service.addTask(formData)
      .subscribe(data => {
        alert('Tarea creada')
        this.route.navigate(['crud'])
      });
    }
  }
}

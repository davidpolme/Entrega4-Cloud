import { Component, OnInit } from '@angular/core';
import { TasksService } from '../services/tasks.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../services/usuario';
import { Tasks } from '../services/task';

@Component({
  selector: 'app-crud',
  templateUrl: './crud.component.html',
  styleUrls: ['./crud.component.css']
})
export class CrudComponent implements OnInit {

  constructor(private service : TasksService, private router: Router, private route: ActivatedRoute) { }

  task : any;

  tasks: Array<Tasks> = [];

  ngOnInit(): void {
    this.getTasksByUser();
  }

  getTaskById() {
    const id = this.route.snapshot.params['id'];
    const usuarioJson = localStorage.getItem('usuario');
    if (usuarioJson !== null) {
      const usuario: Usuario = JSON.parse(usuarioJson);
      this.service.getTaskById(id, usuario)
      .subscribe(data => {
        this.task = data;
      });
    }
    
  }

  getTasksByUser() {
    
    const usuarioJson = localStorage.getItem('usuario');
    if (usuarioJson !== null) {
      const usuario: Usuario = JSON.parse(usuarioJson);
      this.service.getTasksByUser(usuario)
      .subscribe(data => {
        this.tasks = data;
      });
    }
  }
}

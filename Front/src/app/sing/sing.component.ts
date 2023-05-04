import { Component, OnInit } from '@angular/core';
import { LogInService } from '../services/login.service';
import { Router } from '@angular/router';
import { Usuario } from '../services/usuario';

@Component({
  selector: 'app-sing',
  templateUrl: './sing.component.html',
  styleUrls: ['./sing.component.css']
})
export class SingComponent implements OnInit {

  constructor(private service: LogInService, private route : Router) { }

  username: string = '';
  password1: string = '';
  password2: string = '';
  errorMessage: string = '';
  email: string = '';

  ngOnInit(): void {
  }

  onRegistrar(){
    this.service.singIn(new Usuario(0,this.username, this.email, this.password1, this.password2, '')).subscribe(res =>{
      this.route.navigate([''])
    }, error => {
      this.errorMessage = error;
      alert(this.errorMessage)
    });

  }

}

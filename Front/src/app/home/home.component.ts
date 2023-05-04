import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LogInService } from '../services/login.service';
import { Usuario } from '../services/usuario';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  username: string = '';
  password1: string = '';
  password2: string = '';
  errorMessage: string = '';
  email: string = '';

  constructor(private login : LogInService, private route : Router) { }

  ngOnInit(): void {
  }

  onLogin() {
    this.login.logIn(new Usuario(0,this.username, this.email, this.password1, '',''))
    .subscribe(res =>{
          const usuario: any = res;
          // Almacenar la información del usuario en el almacenamiento local
          this.login.saveUserInformation(usuario);
          this.route.navigate(['crud']);
    }, error => {
      this.errorMessage = error;
      alert(this.errorMessage)
    }); 
  }

  onSingIn() {
    this.route.navigate(['registro']);
  }

}

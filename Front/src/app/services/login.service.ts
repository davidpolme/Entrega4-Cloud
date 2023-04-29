import { Injectable } from '@angular/core'
import { HttpClient, HttpErrorResponse} from '@angular/common/http'
import { Usuario } from '../services/usuario'
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Observable } from 'rxjs'

@Injectable({
    providedIn: 'root'
})

export class LogInService {

    constructor (private http: HttpClient) {}

    contrasenaCache = ''
    nombreCache = ''
    idCache = 0;

    public getCon2(){
      return this.contrasenaCache;
    }

    public getId2(){
      return this.idCache;
    }

    public getNombre2(){
      return this.nombreCache;
    }

    public logIn(usuario: Usuario) {
      this.nombreCache = usuario.username;
      this.contrasenaCache = usuario.password1;
        return this.http.post('http://localhost:8080/api/auth/login', usuario)
        .pipe(
            catchError(this.handleErrorLogin)
          );
    }

    public singIn(usuario: Usuario)    {
        return this.http.post('http://localhost:8080/api/auth/signup', usuario)
        .pipe(
            catchError(this.handleErrorSingin)
          );
    }

    private handleErrorLogin(error: HttpErrorResponse) {
        if (error.status === 400) {
          return throwError(JSON.stringify(error.error.mensaje));
        }
        return throwError('Something went wrong');
    }

    private handleErrorSingin(error: HttpErrorResponse) {
        if (error.status === 400) {
          return throwError(JSON.stringify(error.error.mensaje));
        }
        return throwError('Hay errores en su registro!');
      }

}
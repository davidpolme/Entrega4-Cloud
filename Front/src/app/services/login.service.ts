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

    saveUserInformation(usuario: Usuario) {
      localStorage.setItem('usuario', JSON.stringify(usuario));
    }

    public logIn(usuario: Usuario) {
        return this.http.post('http://34.125.146.78:8080/api/auth/login', usuario)
        .pipe(
            catchError(this.handleErrorLogin)
          );
    }

    public singIn(usuario: Usuario)    {
        return this.http.post('http://34.125.146.78:8080/api/auth/signup', usuario)
        .pipe(
            catchError(this.handleErrorSingin)
          );
    }

    private handleErrorLogin(error: HttpErrorResponse) {
        if (error.status === 400) {
          return throwError(JSON.stringify(error.error.mensaje));
        }
        return throwError('Las credenciales no son correctas');
    }

    private handleErrorSingin(error: HttpErrorResponse) {
        if (error.status === 400) {
          return throwError(JSON.stringify(error.error.mensaje));
        }
        return throwError('Hay errores en su registro!');
      }

}
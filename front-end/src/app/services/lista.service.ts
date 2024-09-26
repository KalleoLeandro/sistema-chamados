import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ListaService {

  private url: string = environment.apiUrl;
  
  constructor(private http: HttpClient) {}

  public httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  public listaUsuarios(token:string):Observable<any>{
    this.httpOptions.headers = this.httpOptions.headers.set('authorization', `${token}`);
    return this.http.get<string>(`${this.url}listar-usuarios`, this.httpOptions);
  }

  public buscarUsuarioPorId(id:number, token:string):Observable<any>{
    this.httpOptions.headers = this.httpOptions.headers.set('authorization', `${token}`);
    return this.http.get<string>(`${this.url}buscar-por-id/${id}`, this.httpOptions);
  }
}

import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  constructor(private http:HttpService) { }

  getAll(){
    return this.http.get('/faculty/all');
  }

  getById(id:number){
    return this.http.get('/faculty/'+id);
  }

  create(faculty:any){
    return this.http.post('/faculty',faculty);
  }

  update(faculty:any, id:number){
    return this.http.put('/faculty/'+id,faculty);
  }

  delete(id:number){
    return this.http.delete('/faculty/'+id);
  }


}

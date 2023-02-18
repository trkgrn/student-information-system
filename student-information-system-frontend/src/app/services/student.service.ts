import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  path: string = '/student';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  getByUserId(userId: number) {
    return this.http.get(this.path + '/user/' + userId);
  }

  create(student: any) {
    return this.http.post(this.path, student);
  }

  update(student: any, id: number) {
    return this.http.put(this.path + '/' + id, student);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  getAuthenticatedStudent() {
    return this.http.get(this.path + '/findByJwt');
  }
}

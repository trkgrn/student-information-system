import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class TeacherService {

  path: string = '/teacher';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(teacher: any) {
    return this.http.post(this.path, teacher);
  }

  update(teacher: any, id: number) {
    return this.http.put(this.path + '/' + id, teacher);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  getAuthenticatedTeacher() {
    return this.http.get(this.path + '/findByJwt');
  }
}

import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  path: string = '/faculty';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(faculty: any) {
    return this.http.post(this.path, faculty);
  }

  update(faculty: any, id: number) {
    return this.http.put(this.path + '/' + id, faculty);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

}

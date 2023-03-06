import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  path: string = '/class';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(newClass: any) {
    return this.http.post(this.path, newClass);
  }

  update(updClass: any, id: number) {
    return this.http.put(this.path + '/' + id, updClass);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

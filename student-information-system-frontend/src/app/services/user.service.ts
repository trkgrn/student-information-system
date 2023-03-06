import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  path: string = '/user';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }


  create(user: any) {
    return this.http.post(this.path, user);
  }

  update(user: any, id: number) {
    return this.http.put(this.path + '/' + id, user);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

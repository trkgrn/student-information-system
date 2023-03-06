import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class BranchService {
  path: string = '/branch';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(branch: any) {
    return this.http.post(this.path, branch);
  }

  update(branch: any, id: number) {
    return this.http.put(this.path + '/' + id, branch);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

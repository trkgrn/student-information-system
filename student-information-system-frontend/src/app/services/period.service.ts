import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class PeriodService {

  path: string = '/period';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(period: any) {
    return this.http.post(this.path, period);
  }

  update(period: any, id: number) {
    return this.http.put(this.path + '/' + id, period);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

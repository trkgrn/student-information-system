import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class EducationSeasonService {
  path: string = '/education-season';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(educationSeason: any) {
    return this.http.post(this.path, educationSeason);
  }

  update(educationSeason: any, id: number) {
    return this.http.put(this.path + '/' + id, educationSeason);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

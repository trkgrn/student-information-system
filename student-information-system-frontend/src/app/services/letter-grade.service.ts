import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class LetterGradeService {
  path: string = '/letter-grade';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(grade: any) {
    return this.http.post(this.path, grade);
  }

  update(grade: any, id: number) {
    return this.http.put(this.path + '/' + id, grade);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

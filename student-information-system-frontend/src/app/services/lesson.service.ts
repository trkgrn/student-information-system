import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  path: string = '/lesson';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(lesson: any) {
    return this.http.post(this.path, lesson);
  }

  update(lesson: any, id: number) {
    return this.http.put(this.path + '/' + id, lesson);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

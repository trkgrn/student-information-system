import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class LiveLessonService {
  path: string = '/live-lesson';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(liveLesson: any) {
    return this.http.post(this.path, liveLesson);
  }

  update(liveLesson: any, id: number) {
    return this.http.put(this.path + '/' + id, liveLesson);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }
}

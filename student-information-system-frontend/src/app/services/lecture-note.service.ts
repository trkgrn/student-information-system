import { Injectable } from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class LectureNoteService {
  path: string = '/lecture-note';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(newLectureNote: any) {
    return this.http.post(this.path, newLectureNote);
  }

  update(upd: any, id: number) {
    return this.http.put(this.path + '/' + id, upd);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  getByLiveLessonId(id: number) {
    return this.http.get(this.path + '/live-lesson/' + id);
  }
}

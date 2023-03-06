import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class LessonRequestService {
  path: string = '/lesson-request';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(lessonRequest: any) {
    return this.http.post(this.path, lessonRequest);
  }

  update(lessonRequest: any, id: number) {
    return this.http.put(this.path + '/' + id, lessonRequest);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  getLessonRequestsByStudentId(id: number) {
    return this.http.get(this.path + '/student/' + id);
  }

  getLessonRequestsByWaiting() {
    return this.http.get(this.path + '/waiting');
  }

  approve(id: number) {
    return this.http.put(this.path + '/approve/' + id, null);
  }

  reject(id: number) {
    return this.http.put(this.path + '/reject/' + id, null);
  }
}

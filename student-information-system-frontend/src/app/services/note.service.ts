import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  path: string = '/note';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(note: any) {
    return this.http.post(this.path, note);
  }

  update(note: any, id: number) {
    return this.http.put(this.path + '/' + id, note);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  getNotesByStudentId(studentId: number) {
    return this.http.get(this.path + '/student/' + studentId + '/livelesson/active');
  }

  getNotesByStudentIdAndEducationSeasonId(studentId: number, educationSeasonId: number) {
    return this.http.get(this.path + '/student/' + studentId + '/livelesson/educationseason/' + educationSeasonId);
  }
}

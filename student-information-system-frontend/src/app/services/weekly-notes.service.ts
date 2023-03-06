import {Injectable} from '@angular/core';
import {HttpService} from "./http.service";
import {File} from "../models/entity/File";

@Injectable({
  providedIn: 'root'
})
export class WeeklyNotesService {
  path: string = '/weekly-notes';

  constructor(private http: HttpService) {
  }

  getAll() {
    return this.http.get(this.path + '/all');
  }

  getById(id: number) {
    return this.http.get(this.path + '/' + id);
  }

  create(newWeeklyNotes: any) {
    return this.http.post(this.path, newWeeklyNotes);
  }

  update(upd: any, id: number) {
    return this.http.put(this.path + '/' + id, upd);
  }

  delete(id: number) {
    return this.http.delete(this.path + '/' + id);
  }

  uploadFile(file: File, weeklyNotesId: number) {
    return this.http.put(this.path + '/upload-file/' + weeklyNotesId, file)
  }

  deleteFile(file: File, weeklyNotesId: number) {
    return this.http.put(this.path + '/delete-file/' + weeklyNotesId, file)
  }
}

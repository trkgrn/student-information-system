import {Injectable} from '@angular/core';
import {FileUpload} from "../models/FileUpload";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {finalize, Observable} from "rxjs";
import {v4 as uuidv4} from 'uuid';
import {WeeklyNotesService} from "./weekly-notes.service";
import {File} from "../models/entity/File";
import {FileUtil} from "../modules/resource/file-util";

declare module 'uuid';

@Injectable({
  providedIn: 'root'
})
export class CloudStorageService {
  private basePath = '/public/uploads';

  constructor( private storage: AngularFireStorage,
              private weeklyNotesService: WeeklyNotesService,
               private fileUtil:FileUtil) {
  }

  pushFileToStorage(fileUpload: FileUpload, weeklyNotesId:number): Observable<number> {
    const filePath = `${this.basePath}/${uuidv4() + '.' + this.fileUtil.getFileExtension(fileUpload.file.name)}`;
    const storageRef = this.storage.ref(filePath);
    const uploadTask = this.storage.upload(filePath, fileUpload.file);

    uploadTask.snapshotChanges().pipe(
      finalize( () => {
        storageRef.getDownloadURL().subscribe(async (downloadURL) => {
          fileUpload.url = downloadURL;
          fileUpload.name = fileUpload.file.name;
          fileUpload.filePath = filePath;
         await this.saveFileDatabase(fileUpload,weeklyNotesId);
        });
      })
    ).subscribe();

    return uploadTask.percentageChanges() as Observable<number>;
  }

  private async saveFileDatabase(fileUpload: FileUpload,weeklyNotesId:number) {
    const file = new File(fileUpload.file.name,
      this.fileUtil.getFileExtension(fileUpload.file.name)!,
      fileUpload.file.type,
      fileUpload.file.size,
      fileUpload.filePath!,
      fileUpload.url!);
    await this.weeklyNotesService.uploadFile(file, weeklyNotesId);
  }

   deleteFileStorage(cloudPath: string) {
   return  this.storage.ref(cloudPath).delete().toPromise();
  }

}

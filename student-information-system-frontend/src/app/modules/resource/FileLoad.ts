export class FileLoad{
  file?: File;
  percentage?: number = 0;
  isUploaded?: boolean = false;

  constructor(file: File){
    this.file = file;
  }
}

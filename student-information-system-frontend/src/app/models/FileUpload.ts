export class FileUpload {
  key?: string;
  name?: string;
  url?: string;
  filePath?: string;
  file: File;

  constructor(file: File) {
    this.file = file;
  }
}

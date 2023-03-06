export class File {
  fileName?: string;
  fileExtension?: string;
  contentType?: string;
  fileSize?: number;
  cloudPath?: string;
  downloadUrl?: string;

  constructor(fileName: string, fileExtension: string, contentType: string, fileSize: number, cloudPath: string, downloadUrl: string) {
    this.fileName = fileName;
    this.fileExtension = fileExtension;
    this.contentType = contentType;
    this.fileSize = fileSize;
    this.cloudPath = cloudPath;
    this.downloadUrl = downloadUrl;
  }
}

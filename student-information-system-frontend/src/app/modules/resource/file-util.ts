import {Injectable} from "@angular/core";

@Injectable()
export class FileUtil{

  getFileExtension(filename: string) {
    return filename.split('.').pop();
  }
  getFileSizeByByte(byte: number) {
    const sizes = ['Byte', 'KB', 'MB', 'GB', 'TB'];
    if (byte === 0) return '0 Byte';
    const i = parseInt(Math.floor(Math.log(byte) / Math.log(1024)).toString());
    return Math.round(byte / Math.pow(1024, i)) + ' ' + sizes[i];
  }

}

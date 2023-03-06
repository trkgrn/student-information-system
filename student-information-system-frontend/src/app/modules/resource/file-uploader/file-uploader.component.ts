import {Component, Input} from '@angular/core';
import {FileUpload} from "../../../models/FileUpload";
import {CloudStorageService} from "../../../services/cloud-storage.service";
import {FileLoad} from "../FileLoad";
import {OnModalInit} from "../../modal/abstracts/on-modal-init";
import {ModalService} from "../../modal/modal.service";
import {FileUtil} from "../file-util";

declare var $: any;

@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.scss']
})
export class FileUploaderComponent implements OnModalInit {

  @Input() title?: string;
  @Input() weeklyNoteId?: number;

  public files: FileLoad[] = [];
  currentFileUpload?: FileUpload;
  percentage?: number = 20;
  change: boolean = false;

  constructor(private cloudStorageService: CloudStorageService,private modalService: ModalService,public fileUtil: FileUtil) {
  }


  onModalInit(): void {
  }

  closeModal() {
    this.modalService.close({
      change: this.change
    });
  }


  onFileChangeWithDrop(pFileList: File[]) {
    if (!this.currentFileUpload) {
      this.files = Object.keys(pFileList).map((key: any) => new FileLoad(pFileList[key]))
      console.log(this.files)
    }
  }
  onFileChangeWithManual(event: any) {
    if (!this.currentFileUpload) {
      const files = event.target.files;
      this.files = Object.keys(files).map((key: any) => new FileLoad(files[key]))
      console.log(this.files)
    }
  }



  upload(fileLoad: FileLoad): void {
    fileLoad.isUploaded = true;
    this.currentFileUpload = new FileUpload(fileLoad.file!);
    this.cloudStorageService.pushFileToStorage(this.currentFileUpload,this.weeklyNoteId!)
      .subscribe(async (percentage:number) => {
          fileLoad.percentage = Math.round(percentage);
          if (fileLoad.percentage === 100) {
            this.currentFileUpload = undefined;
            this.change = true;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

  deleteFile(file: File) {
    this.files = Object.values(this.files).filter((f: FileLoad) => f.file?.name !== file.name)
    console.log(this.files)
  }


}

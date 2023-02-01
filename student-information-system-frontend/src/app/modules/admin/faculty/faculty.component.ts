import { Component, OnInit } from '@angular/core';
import {FacultyService} from "../../../services/faculty.service";
import {Faculty} from "../../../models/entity/Faculty";

declare var $: any
@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.scss']
})
export class FacultyComponent implements OnInit {

  faculties:Faculty[] = [];

  constructor(private facultyService:FacultyService) { }

  async ngOnInit() {
    this.faculties = await this.facultyService.getAll();
    setTimeout(()=>{
      this.initJQuery();
    });
  }

  initJQuery(){
    $("#example1").DataTable({
      "responsive": true, "lengthChange": false, "autoWidth": false,
      "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
  }

}

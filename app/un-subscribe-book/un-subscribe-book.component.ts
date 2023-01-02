import { Component, OnInit } from '@angular/core';
import { UnSubscribeBookService } from '../_services/un-subscribe-book.service';

@Component({
  selector: 'app-un-subscribe-book',
  templateUrl: './un-subscribe-book.component.html',
  styleUrls: ['./un-subscribe-book.component.css']
})
export class UnSubscribeBookComponent implements OnInit {
  info : any = {
    subscribedid:null
  }
  success = false;
  errorMessage = "";

  constructor(private subscribeBookService:UnSubscribeBookService) { }

  ngOnInit(): void {
  }

  onUnSubscribeBook(){
    const{subscribedid} = this.info;
    this.subscribeBookService.unSubscribeBook(this.info).subscribe(data=> {
      this.success = true;
    },
    error=> {
      this.errorMessage = error.error;
      this.success = false;
    })
    }
  

}

import { Component, OnInit } from '@angular/core';
import { SubscribeBookService } from '../_services/subscribe-book.service';

@Component({
  selector: 'app-subscribe-book',
  templateUrl: './subscribe-book.component.html',
  styleUrls: ['./subscribe-book.component.css']
})
export class SubscribeBookComponent implements OnInit {
  info : any = {
    bookId: null,
    authorId: null,
    subscriberName:null
  }
  success = false;
  errorMessage = "";
  constructor(private subscribeBookService:SubscribeBookService) { }

  ngOnInit(): void {
  }

  onSubscribeBook(){
  const{bookId, authorId} = this.info;
  this.info.subscriberName=this.subscribeBookService.readerName;
  this.subscribeBookService.subscribeBook(this.info).subscribe(data=> {
    this.success = true;
  },
  error=> {
    this.errorMessage = error.error;
    this.success = false;
  })
  }
}

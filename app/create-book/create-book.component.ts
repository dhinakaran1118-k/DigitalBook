import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../_services/author.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {
  book : any = {
    title: null,
    publisher: null,
    category: null,
    code:null,
    isActive:1,
    price: null,
    authorId:null,
    authorName:null
  }
  success = false;
  errorMessage = "";
  constructor(private authorService: AuthorService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    const{title, publisher,category,price} = this.book;
    this.book.authorId=this.authorService.authorId;
    this.book.authorName=this.authorService.authorName;
    console.log(this.book.authorName);
    this.authorService.createBook(this.book).subscribe(data=> {
      this.success = true;
    },
    error=> {
      this.errorMessage = error.error;
      this.success = false;
    })
  }

}

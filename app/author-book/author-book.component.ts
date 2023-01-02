import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../_services/author.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-author-book',
  templateUrl: './author-book.component.html',
  styleUrls: ['./author-book.component.css']
})
export class AuthorBookComponent implements OnInit {

  constructor(private authorService:AuthorService,private tokenService: TokenStorageService) { }

  ngOnInit(): void {
  }

  isSearchSuccess: any;
  isSearchFailed = false;
  errorMessage = "";
  isUserLoggedIn = this.tokenService.getUser() !== null;
  showSubscribe:any;
  showSuccess=false;
  successMessage="";
  showWarningMessage=false;
  warningMessage="";
  
  searchForm : any = {
    title:"",
    authorName:"",
    category:"",
    publisher:""
  }; 

  books : any[] = []
  
  book : any = {
    bookId:null,
    title: null,
    code:null,
    authorId:null,
    authorName: null,
    publisher: null,
    category: null,
    price: null
  }

  onSearch(){
    //const {title, authorName,category,publisher} = this.searchForm;
    this.authorService.getbooks().subscribe(
      data => {
        this.books = []
        this.isSearchSuccess = true;
        this.isSearchFailed = false;
        for ( let b of data){
          this.book = b;
          this.books.push(this.book);
        }
      },
      error => {
        //console.error(error);
        this.isSearchSuccess = false;
        this.isSearchFailed = true;
        if(error instanceof HttpErrorResponse){
          //console.error(error.error.message);
          this.errorMessage = error.error.message

        }
        
      }
    );

  }

  onClick(book:any){
    if(!this.isUserLoggedIn){
      this.showWarningMessage= true;
      this.warningMessage="Please Signup/signin into your account!"; 
    }
  }

}

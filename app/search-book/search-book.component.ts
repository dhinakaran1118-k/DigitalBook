import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-search-book',
  templateUrl: './search-book.component.html',
  styleUrls: ['./search-book.component.css']
})
export class SearchBookComponent {

  constructor(private userService: UserService,private tokenService: TokenStorageService) { }
  
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
    title: null,
    authorName: null,
    publisher: null,
    category: null,
    price: null
  }

  onSearch(){
    const {title, authorName,category,publisher} = this.searchForm;
    this.userService.search(title,authorName,category,publisher).subscribe(
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
        console.error(error);
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

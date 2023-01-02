import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SubscribeBookService } from '../_services/subscribe-book.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-subcription-details',
  templateUrl: './subcription-details.component.html',
  styleUrls: ['./subcription-details.component.css']
})
export class SubcriptionDetailsComponent implements OnInit {

  constructor(private subscribeBookService:SubscribeBookService,private tokenService: TokenStorageService) { }

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
    id:null,
    bookId:null,
    subscriberName: null
  }

  onSearch(){
    //const {title, authorName,category,publisher} = this.searchForm;
    this.subscribeBookService.getDetails().subscribe(
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
          console.error(error.error.message);
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

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

//const BOOK_API = 'http://localhost:8091/api/auth/';
const BOOK_API = "https://cmapfzknj3.execute-api.ap-northeast-1.amazonaws.com/UAT/";

//const AUTHOR_BOOK_API = "http://localhost:8091/api/auth/";
const AUTHOR_BOOK_API = "https://cmapfzknj3.execute-api.ap-northeast-1.amazonaws.com/UAT/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 
  'Access-Control-Allow-Origin':"*" })
};

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private http: HttpClient,private tokenStorageService: TokenStorageService) { }

   user = this.tokenStorageService.getUser();
   authorId=this.user.id;
   authorName=this.user.username;

   createBook(book: any) : Observable<any> {
    //return this.http.post(BOOK_API +"/author/createbook",book,httpOptions);
    //return this.http.post(BOOK_API +"/author/"+this.user.id+"/createbook",book,httpOptions);
    return this.http.post(BOOK_API +"createbook",book,httpOptions);
  }

  getbooks() : Observable<any> {
    //return this.http.get(API_URL +"reader/getallbooks");
    return this.http.get(AUTHOR_BOOK_API +"author/"+this.user.username+"/getAuthorBooks");
  }
}



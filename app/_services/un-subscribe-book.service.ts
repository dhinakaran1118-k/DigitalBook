import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

//const BOOK_API = 'http://localhost:8091/api/auth/';
const BOOK_API = "https://cmapfzknj3.execute-api.ap-northeast-1.amazonaws.com/UAT/";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 
  'Access-Control-Allow-Origin':"*" })
};

@Injectable({
  providedIn: 'root'
})
export class UnSubscribeBookService {

  constructor(private http: HttpClient,private tokenStorageService: TokenStorageService) { }

  user = this.tokenStorageService.getUser();
   readerId=this.user.id;
   unSubscribeBook(info: any) : Observable<any> {
    return this.http.post(BOOK_API +"readers/"+this.user.id+"/books/"+info.subscribedid+"/unsubscribe",httpOptions);
    //return this.http.post(BOOK_API +"createbook",book,httpOptions);
  }
}

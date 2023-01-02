import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { BoardUserComponent } from './board-user/board-user.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { BoardAuthorComponent } from './board-author/board-author.component';
import { BoardGuestComponent } from './board-guest/board-guest.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { SearchBookComponent } from './search-book/search-book.component';
import { SubscribeBookComponent } from './subscribe-book/subscribe-book.component';
import { UnSubscribeBookComponent } from './un-subscribe-book/un-subscribe-book.component';
import { AllBooksComponent } from './all-books/all-books.component';
import { AuthorBookComponent } from './author-book/author-book.component';
import { SubcriptionDetailsComponent } from './subcription-details/subcription-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    BoardUserComponent,
    BoardAuthorComponent,
    BoardGuestComponent,
    CreateBookComponent,
    SearchBookComponent,
    SubscribeBookComponent,
    UnSubscribeBookComponent,
    AllBooksComponent,
    AuthorBookComponent,
    SubcriptionDetailsComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }

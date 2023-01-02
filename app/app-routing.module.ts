import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllBooksComponent } from './all-books/all-books.component';
import { AuthorBookComponent } from './author-book/author-book.component';


import { BoardAuthorComponent } from './board-author/board-author.component';
import { BoardGuestComponent } from './board-guest/board-guest.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { CreateBookComponent } from './create-book/create-book.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { SearchBookComponent } from './search-book/search-book.component';
import { SubcriptionDetailsComponent } from './subcription-details/subcription-details.component';
import { SubscribeBookComponent } from './subscribe-book/subscribe-book.component';
import { UnSubscribeBookComponent } from './un-subscribe-book/un-subscribe-book.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'guest', component: BoardGuestComponent },
  { path: 'author', component: BoardAuthorComponent },
  { path: 'createbook', component: CreateBookComponent },
  { path: 'author/createbook', component: CreateBookComponent },
  { path: 'author/authorbook', component: AuthorBookComponent },
  { path: 'searchbook', component: SearchBookComponent },
  { path: 'user/subscribebook', component: SubscribeBookComponent },
  { path: 'user/unsubscribebook', component: UnSubscribeBookComponent },
  { path: 'user/subscriberdetails', component: SubcriptionDetailsComponent },
  { path: 'allbooks', component: AllBooksComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Loan } from './model/Loan';
import { LoanPage } from './model/LoanPage';
//import { LOAN_DATA } from './model/mock-loans';
//import { LOAN_DATA_LIST } from './model/mock-loans-list';

@Injectable({
  providedIn: 'root'
})
export class LoanService {

  constructor(
    private http: HttpClient
  ) { }

  getLoans(pageable:Pageable,  gameId?: number, clientId?:number, date?: Date): Observable<LoanPage> {
    
    return this.http.post<LoanPage>('http://localhost:8080/loan', {pageable: pageable, gameId: gameId,
    clientId: clientId, date: date } );
  }

  saveLoan(loan: Loan): Observable<void> {
    let url = 'http://localhost:8080/loan';
    if (loan.id != null) url += '/'+loan.id;

    return this.http.put<void>(url, loan);
  }

  deleteLoan(idLoan : number): Observable<void> {
      return this.http.delete<void>('http://localhost:8080/loan/'+idLoan);

  }    

  getAllLoans(): Observable<Loan[]> {
    return this.http.get<Loan[]>('http://localhost:8080/loan');
}}

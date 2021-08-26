import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { InvestAccountService } from 'src/services/invest-account.service';
import {InvestAccount} from 'src/app/invest-account';

@Component({
  selector: 'app-invest-account',
  templateUrl: './invest-account.component.html',
  styleUrls: ['./invest-account.component.css']
})
export class InvestAccountComponent implements OnInit {
  public accounts: InvestAccount[];
  public editEmployee: InvestAccount;
  public deleteEmployee: InvestAccount;


  
  constructor(private router: Router, private service: InvestAccountService,) { }
  
  public getAccounts(id:number): void {
    this.service.getInvestmentAccounts(id).subscribe(
      (response: InvestAccount[]) => {
        this.accounts = response;
        console.log(this.accounts);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddEmloyee(addForm: NgForm): void {
    document.getElementById('add-employee-form').click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateEmloyee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getAccounts();
    });
  }



}

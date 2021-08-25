import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType } from 'chart.js';
import { Label, SingleDataSet } from 'ng2-charts';
import { CashFlowService } from 'src/services/cash-flow.service';

class Account {
  transactionList: [];
  constructor() {
    this.transactionList = [];
  }
}

class Transaction {
  value: number;
  date: Date;
  spentOn: string;
  cameFrom: string;
  constructor() {
    this.value = 0;
    this.date = new Date();
    this.spentOn = '';
    this.cameFrom = '';
  }
}

@Component({
  selector: 'app-cash-flow',
  templateUrl: './cash-flow.component.html',
  styleUrls: ['./cash-flow.component.css'],
})
export class CashFlowComponent implements OnInit {
  cashAccounts: Account[] = [];
  time: string = 'week';
  chartSpent: SingleDataSet = [];
  chartSpentLabels: Label[] = [];
  chartSpentMap = new Map();
  chartEarned: SingleDataSet = [];
  chartEarnedLabels: Label[] = [];
  chartEarnedMap = new Map();
  chartType: ChartType = 'doughnut';

  chartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    }
  };
  constructor(private cashFlowService: CashFlowService) {}

  ngOnInit(): void {
    this.getCashAccounts();
  }

  getCashAccounts() {
    this.cashFlowService.getTransactions(1).subscribe(
      (data: any) => {
        this.cashAccounts = data;
      },
      () => {},
      () => {
        this.cashAccounts.forEach((account) => {
          account.transactionList.forEach((transaction: Transaction) => {
            if (transaction.value > 0) {
              if (this.chartEarnedMap.has(transaction.cameFrom))
                this.chartEarnedMap.set(
                  transaction.cameFrom,
                  this.chartEarnedMap.get(transaction.cameFrom) +
                    transaction.value
                );
              else
                this.chartEarnedMap.set(
                  transaction.cameFrom,
                  transaction.value
                );
            } else {
              if(this.chartSpentMap.has(transaction.spentOn))
              this.chartSpentMap.set(transaction.spentOn,this.chartSpentMap.get(transaction.spentOn)+Math.abs(transaction.value))
            else
              this.chartSpentMap.set(transaction.spentOn,Math.abs(transaction.value));
              
            }
          }
        );
        this.chartEarnedLabels=Array.from(this.chartEarnedMap.keys());
        this.chartSpentLabels=Array.from(this.chartSpentMap.keys());
        this.chartEarned=Array.from(this.chartEarnedMap.values());
        this.chartSpent=Array.from(this.chartSpentMap.values());
      }
    );
  })}
}

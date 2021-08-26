import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, plugins } from 'chart.js';
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
  pieChartColors = [
    {
      backgroundColor: ['#0d5fbc',
        '#0074ce',
        '#0088dd',
        '#009ce9',
        '#00b0f2',
        '#00c3f8',
        '#00d6fc',
        '#00e9fe',
        '#00fbff']
    },
  ];
  cashAccounts: Account[] = [];
  time: string = 'week';
  chartSpent: SingleDataSet = [];
  chartSpentLabels: Label[] = [];
  chartSpentMap = new Map();
  chartEarned: SingleDataSet = [];
  chartEarnedLabels: Label[] = [];
  chartEarnedMap = new Map();
  income:number=0;
  spent:number=0;
  chartType: ChartType = 'doughnut';
  chartOptionsEarn: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    },
   
   animation:{
     onProgress:(chart)=> {
      let width = chart.chart.width,
        height = chart.chart.height,
        ctx = chart.chart.ctx;

      ctx.restore();
      let fontSize = 1.5
      ctx.font = fontSize + "em sans-serif";
      ctx.textBaseline = "middle";
      ctx.fillStyle = 'grey';
      let sum =0;
      this.chartSpent.forEach((element:any)=>{
        sum+=element;
      })
      this.income=sum;
      let text = "$" + sum,
        textX = Math.round((width - ctx.measureText(text).width) / 2),
        textY = height / 2 + 15;

      ctx.fillText(text, textX, textY);
      text = "Income",
      textX = Math.round((width - ctx.measureText(text).width) / 2),
      textY = height / 2 - 15;
      ctx.fillText(text, textX, textY);
      ctx.save();
    }
   }
  };

  chartOptionsSpent: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    },
   animation:{
     onProgress:(chart)=> {
      let width = chart.chart.width,
        height = chart.chart.height,
        ctx = chart.chart.ctx;

      ctx.restore();
      let fontSize = 1.5
      ctx.font = fontSize + "em sans-serif";
      ctx.textBaseline = "middle";
      ctx.fillStyle = 'grey';
      let sum =0;
      this.chartEarned.forEach((element:any)=>{
        sum+=element;
      })
      this.spent=sum;
      let text = "$" + sum,
        textX = Math.round((width - ctx.measureText(text).width) / 2),
        textY = height / 2 + 15;

      ctx.fillText(text, textX, textY);
       text = "Spending",
      textX = Math.round((width - ctx.measureText(text).width) / 2),
      textY = height / 2 - 15;
      ctx.fillText(text, textX, textY);
      ctx.save();
    }
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

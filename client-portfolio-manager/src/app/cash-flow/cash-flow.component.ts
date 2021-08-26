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
  timeFrames = ['LAST 7 DAYS', 'LAST 30 DAYS', 'LAST 3 MONTHS', 'YEAR TO DATE'];
  timeFrame = 0;
  pieChartColors = [
    {
      backgroundColor: [
        '#0d5fbc',
'#0095e5',
'#00c9f9',
'#00fbff'
      ],
    },
  ];
  pieChartColors2 = [
    {
      backgroundColor: [
        '#fa890f',
'#ec6a00',
'#dd4800',
'#cc1900',
      ],
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
  income: number = 0;
  spent: number = 0;
  chartType: ChartType = 'doughnut';
  chartOptionsEarn: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    },

    animation: {
      onProgress: (chart) => {
        let width = chart.chart.width,
          height = chart.chart.height,
          ctx = chart.chart.ctx;

        ctx.restore();
        let fontSize = 1.5;
        ctx.font = fontSize + 'em Calibri';
        ctx.textBaseline = 'middle';
        ctx.fillStyle = 'grey';
        let sum = 0;
        this.chartEarned.forEach((element: any) => {
          sum += element;
        });
        this.income = sum;
        let text = '$' + sum,
          textX = Math.round((width - ctx.measureText(text).width) / 2),
          textY = height / 2 + 15;

        ctx.fillText(text, textX, textY);
        (text = 'Income'),
          (textX = Math.round((width - ctx.measureText(text).width) / 2)),
          (textY = height / 2 - 15);
        ctx.fillText(text, textX, textY);
        ctx.save();
      },
    },
  };

  chartOptionsSpent: ChartOptions = {
    responsive: true,
    legend: {
      display: false,
    },
    animation: {
      onProgress: (chart) => {
        let width = chart.chart.width,
          height = chart.chart.height,
          ctx = chart.chart.ctx;

        ctx.restore();
        let fontSize = 1.5;
        ctx.font = fontSize + 'em Calibri';
        ctx.textBaseline = 'middle';
        ctx.fillStyle = 'grey';
        let sum = 0;
        this.chartSpent.forEach((element: any) => {
          sum += element;
        });
        this.spent = sum;
        let text = '$' + sum,
          textX = Math.round((width - ctx.measureText(text).width) / 2),
          textY = height / 2 + 15;

        ctx.fillText(text, textX, textY);
        (text = 'Spending'),
          (textX = Math.round((width - ctx.measureText(text).width) / 2)),
          (textY = height / 2 - 15);
        ctx.fillText(text, textX, textY);
        ctx.save();
      },
    },
  };
  constructor(private cashFlowService: CashFlowService) {}
  daysToString(days: number) {
    let date: Date = new Date();
    date.setDate(date.getDate() - days - 1);

    let month = '' + (date.getMonth() + 1);
    let day = '' + date.getDate();
    let year = date.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }
  ngOnInit(): void {
    this.getCashAccounts(this.daysToString(7));
  }
  changeDate() {
    this.income = 0;
    this.spent = 0;
    this.chartSpent = [];
    this.chartSpentLabels = [];
    this.chartSpentMap = new Map();
    this.chartEarned = [];
    this.chartEarnedLabels = [];
    this.chartEarnedMap = new Map();
    switch (this.timeFrame) {
      case 0:
        this.getCashAccounts(this.daysToString(28));
        this.timeFrame++;
        break;
      case 1:
        this.getCashAccounts(this.daysToString(28 * 3));
        this.timeFrame++;
        break;
      case 2:
        let today: Date = new Date();
        let date2: Date = new Date('01/04/2021');
        let diff = today.getTime() - date2.getTime();
        let diffDays = Math.floor(diff / (1000 * 60 * 60 * 24));
        this.getCashAccounts(this.daysToString(diffDays));
        this.timeFrame++;
        break;
      case 3:
        this.getCashAccounts(this.daysToString(7));
        this.timeFrame = 0;
        break;
    }
  }
  getCashAccounts(time: string) {
    this.cashFlowService.getTransactions(1, time).subscribe(
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
              if (this.chartSpentMap.has(transaction.spentOn))
                this.chartSpentMap.set(
                  transaction.spentOn,
                  this.chartSpentMap.get(transaction.spentOn) +
                    Math.abs(transaction.value)
                );
              else
                this.chartSpentMap.set(
                  transaction.spentOn,
                  Math.abs(transaction.value)
                );
            }
          });
          this.chartEarnedLabels = Array.from(this.chartEarnedMap.keys());
          this.chartSpentLabels = Array.from(this.chartSpentMap.keys());
          this.chartEarned = Array.from(this.chartEarnedMap.values());
          this.chartSpent = Array.from(this.chartSpentMap.values());
        });
      }
    );
  }
}

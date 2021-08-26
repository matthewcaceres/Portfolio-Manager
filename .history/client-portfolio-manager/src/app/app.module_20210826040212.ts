import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InsightsComponent } from './insights/insights.component';
import { NetWorthComponent } from './net-worth/net-worth.component';
import { MarketMoversComponent } from './market-movers/market-movers.component';
import { CashFlowComponent } from './cash-flow/cash-flow.component';
import { CashAccountComponent } from './cash-account/cash-account.component';
import { InvestAccountComponent } from './invest-account/invest-account.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms'
import { ChartsModule } from 'ng2-charts';
import { InvestAccountService } from 'src/services/invest-account.service';
import { CashAccountService } from 'src/services/cash-account.service';



@NgModule({
  declarations: [
    AppComponent,
    InsightsComponent,
    NetWorthComponent,
    MarketMoversComponent,
    CashFlowComponent,
    CashAccountComponent,
    InvestAccountComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ChartsModule,
    NgbModule

  ],
  providers: [InvestAccountService,CashAccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }

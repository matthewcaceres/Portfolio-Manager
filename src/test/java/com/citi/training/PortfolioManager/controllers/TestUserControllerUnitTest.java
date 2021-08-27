package com.citi.training.PortfolioManager.controllers;

import com.citi.training.PortfolioManager.entities.CashAccount;
import com.citi.training.PortfolioManager.entities.Transaction;
import com.citi.training.PortfolioManager.entities.User;
import com.citi.training.PortfolioManager.services.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TestUserControllerUnitTest {
    @InjectMocks
    private  UserController controller = new UserController();
    @Mock
    private  UserService userService;



  @Before
  public void setUp(){
      List<User> users= new ArrayList<>();
      User u = new User();
      u.setName("bill");
      List<CashAccount> ca = new ArrayList<>();
      CashAccount c = new CashAccount();
      Transaction t = new Transaction();
      t.setDate(LocalDate.parse("2021-08-01"));
      List<Transaction> tt= new ArrayList<>();
      tt.add(t);
      c.setTransactionList(tt);
      ca.add(c);
      u.setCashAccountList(ca);
      users.add(u);

      when(userService.getAll()).thenReturn(users);
      when(userService.getUserById(1)).thenReturn(u);
      when(userService.getByName("bill")).thenReturn(u);
      when(userService.getCashAccounts(1)).thenReturn(u.getCashAccountList());
      when(userService.getCashFlow(1,LocalDate.parse("2021-08-25"))).thenReturn(u.getCashAccountList());
      try {
          when(userService.getNetWorthTime(1,"week")).thenReturn(new ArrayList<Double>());
          when(userService.getIndicesWeek()).thenReturn(new HashMap<>());
          when(userService.getMoversLosers(1)).thenReturn(new HashMap<>());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
    @Test
    public void testFindAll() {
        List<User> cds = controller.getUsers();
        Assertions.assertEquals(cds.size(),1);
    }

    @Test
    public void testFindById() {
        User u = controller.getUser(1);
        Assertions.assertNotNull(u);
    }

    @Test
    public void testFindByName() {
        User u = controller.getUser("bill");
        Assertions.assertEquals(u.getName(),"bill");
    }

    @Test
    public void testCashAccounts() {
        List<CashAccount> u = controller.getCashAccounts(1,null);
        Assertions.assertEquals(u.size(),1);
    }

    @Test
    public void testCashAccountsDate() {
        List<CashAccount> u = controller.getCashAccounts(1,"2021-08-25");
        Assertions.assertEquals(u.size(),1);
    }

    @Test
    public void testNetWorth() {
        List<Double> u = controller.getNetworthWeek(1,"week");
        Assertions.assertEquals(u.size(),0);
    }

    @Test
    public void testMovers() {
        HashMap u = controller.getMovers(1);
        Assertions.assertEquals(u.size(),0);
    }

    @Test
    public void testIndices() {
        HashMap u = controller.getIndices("week");
        Assertions.assertEquals(u.size(),0);
    }


}

package pl.wszib.edu.controllers;

import org.springframework.web.bind.annotation.*;
import pl.wszib.edu.DAO.AccountDAO;
import pl.wszib.edu.model.Account;
import pl.wszib.edu.services.AccountService;

import java.util.UUID;

@RestController
public class AccountController {

    AccountDAO accountDAO;
    AccountService accountService;

    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
        this.accountService = new AccountService(accountDAO);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAccount(@RequestBody Account account){
        accountService.createAccount(account);
        System.out.println(account.getName()+" "+account.getSurname()+" "+account.getAccount_balance());
        return "Account added : " + account.getName() + " " + account.getSurname() + " " + account.getId();
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteAccount(@RequestBody Account account){
        accountService.deleteAccount(account);
        return "Account deleted : " + account.getName() + " " + account.getSurname();
    }
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public void changeBallance(@RequestParam(value = "id", required = false) String id){
        System.out.println(id);
        accountService.changeBallance(id);
    }
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public void getAccounts(){
        accountService.getAccounts();
    }
}

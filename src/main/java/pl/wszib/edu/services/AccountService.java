package pl.wszib.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wszib.edu.DAO.AccountDAO;
import pl.wszib.edu.model.Account;

import java.util.UUID;

@Service
public class AccountService{

//    @Bean
//    CommandLineRunner commandLineRunner(AccountDAO accountDAO){
//        return args -> {
//          accountDAO.save(new Account("Pawel", "dziwisz", 0));
//        };
//    }
    AccountDAO accountDAO;
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }



    public void createAccount(Account account) {
        accountDAO.save(account);
    }

    public void deleteAccount(Account account) {
        accountDAO.delete(account);
    }
    public void changeBallance(String id) {
        try{
            Account accoundFinded = findAccount(id,accountDAO);
            MoneyService moneyService = new MoneyService(accoundFinded.getAccount_balance());
            moneyService.run();
            accoundFinded.setAccount_balance(moneyService.getMoney());
            accountDAO.save(accoundFinded);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void getAccounts() {
        for (Account a : accountDAO.findAll()) {
            System.out.println(a.getName() + " " + a.getSurname() + " Money = " + MoneyService.roundTo2DecimalPlace(a.getAccount_balance()) + "--- UUID = " + a.getId());
        }
    }
    public Account findAccount(String uuid, AccountDAO accountDAO){
        for(Account temp : accountDAO.findAll()){
            if(uuid.equals(temp.getId().toString()))
                return temp;
        }
        return null;
    }
}

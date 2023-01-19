package pl.wszib.edu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wszib.edu.DAO.AccountDAO;
import pl.wszib.edu.model.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AccountService{

//    @Bean
//    CommandLineRunner commandLineRunner(AccountDAO accountDAO){
//        return args -> {
//          accountDAO.save(new Account("Pawel", "dziwisz", 0));
//        };
//    }
    private final AccountDAO accountDAO;
    private final Object getLock = new Object();

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void createAccount(Account account) {
        accountDAO.save(account);
    }

    public void deleteAccount(Account account) {
        accountDAO.delete(account);
    }
    public void changeBalance(String id) {
        Account accoundFinded = findAccount(id);
        MoneyService moneyService = new MoneyService(accoundFinded.getAccount_balance());
        synchronized(getLock) {
            try{
                //Here you can add more Thread's
                new Thread(moneyService).start();
                new Thread(moneyService).start();
                Thread.sleep(200);

                System.out.println("Wait for total sum");
                accoundFinded.setAccount_balance(moneyService.getMoney());
                accoundFinded.setLogs(moneyService.getLogs());
                accountDAO.save(accoundFinded);

                Thread.sleep(1500);
                System.out.println("Now total balance for " + accoundFinded.getName() + " is equal " + MoneyService.roundTo2DecimalPlace(accoundFinded.getAccount_balance()));

            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public void getAccounts() {
        for (Account a : accountDAO.findAll()) {
            System.out.println(a.getName() + " " + a.getSurname() + " Money = " + MoneyService.roundTo2DecimalPlace(a.getAccount_balance()) + "--- UUID = " + a.getId());
        }
    }
    public Account findAccount(String uuid){
        for(Account temp : accountDAO.findAll()){
            if(uuid.equals(temp.getId().toString()))
                return temp;
        }
        return null;
    }

    public List<Double> getLogsOfChangingMoneyBalance(String id) {
        return findAccount(id).getLogs();
    }
}

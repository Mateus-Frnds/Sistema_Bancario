import java.util.Map;
import java.util.HashMap;

public class Bank {
    private Map<Integer, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized void addAccount(int id, double initialBalance) {
        accounts.put(id, new Account(id, initialBalance));
    }

    public Account getAccount(int id) {
        return accounts.get(id);
    }

    public void performTransaction(int accountId, double amount, boolean isDeposit) {
        Account account = getAccount(accountId);
        if (account != null) {
            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        }
    }
}

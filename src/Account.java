import java.util.ArrayList;
import java.util.List;

public class Account {
    private int id;
    private double balance;
    private List<String> transactionHistory;

    public Account(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            String transaction = String.format("deposit: +%.2f | Date: %s", amount, new java.util.Date());
            transactionHistory.add(transaction);
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            String transaction = String.format("withdrawal: -%.2f | Date: %s", amount, new java.util.Date());
            transactionHistory.add(transaction);
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public int getId() {
        return id;
    }
}

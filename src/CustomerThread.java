public class CustomerThread extends Thread {
    private Bank bank;
    private int accountId;
    private double amount;
    private boolean isDeposit;

    public CustomerThread(Bank bank, int accountId, double amount, boolean isDeposit) {
        this.bank = bank;
        this.accountId = accountId;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {
        bank.performTransaction(accountId, amount, isDeposit);
    }
}

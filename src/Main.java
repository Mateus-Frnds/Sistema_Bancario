public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addAccount(1, 1000.0);
        bank.addAccount(2, 2000.0);
        bank.addAccount(3, 3000.0);

        CustomerThread[] customers = new CustomerThread[6];
        customers[0] = new CustomerThread(bank, 1, 500.0, true);
        customers[1] = new CustomerThread(bank, 1, 200.0, false);
        customers[2] = new CustomerThread(bank, 2, 1000.0, true);
        customers[3] = new CustomerThread(bank, 2, 300.0, false);
        customers[4] = new CustomerThread(bank, 3, 700.0, true);
        customers[5] = new CustomerThread(bank, 3, 500.0, false);

        for (CustomerThread customer : customers) {
            customer.start();
        }

        for (CustomerThread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 1; i <= 3; i++) {
            Account account = bank.getAccount(i);
            System.out.println("account: " + account.getId());
            System.out.println("balance: " + account.getBalance());
            System.out.println("past transactions: ");
            for (String transaction : account.getTransactionHistory()) {
                System.out.println(transaction);
            }
            System.out.println("------------------");
        }
    }
}

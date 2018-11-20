package account.ru.maslenkin.testtaskplaytox;

import account.ru.maslenkin.testtaskplaytox.account.Account;
import account.ru.maslenkin.testtaskplaytox.transfer.Transfer;

public class Main {


    public static void main(String[] args) {

        Transfer transfer = new Transfer(30);

        Account account1 = new Account();
        Account account2 = new Account();

        account1.setRecipient(account2);
        account2.setRecipient(account1);

        account1.setTransfer(transfer);
        account2.setTransfer(transfer);

        Thread thread1 = new Thread(account1);
        Thread thread2 = new Thread(account2);

        thread1.start();
        thread2.start();
    }

}

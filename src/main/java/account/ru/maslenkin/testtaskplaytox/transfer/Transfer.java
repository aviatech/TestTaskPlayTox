package account.ru.maslenkin.testtaskplaytox.transfer;

import account.ru.maslenkin.testtaskplaytox.account.Account;
import account.ru.maslenkin.testtaskplaytox.exception.InsufficientFundsAccountException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Transfer {
    private Logger logger = LoggerFactory.getLogger(Transfer.class);
    private int transferCount;
    private int transferLimit;
    private final Object lock = new Object();

    public Transfer() {
    }

    public Transfer(int transferLimit) {
        this.transferLimit = transferLimit;
    }

    public void setTransferLimit(int transferLimit) {
        this.transferLimit = transferLimit;
    }

    private void getLog() {
    }

    public Condition sendMoney(int transferAmount, Account sender, Account recipient) {
        synchronized (lock) {
            if (transferCount >= transferLimit) {
                return Condition.EXCESS_TRANSFER;
            } else {
                try {
                    sender.sendMoney(transferAmount);
                    recipient.receiveMoney(transferAmount);
                    ++transferCount;
                    getLog();
                    logger.debug("Number of transaction {} Account {} sent {} Recipient {} ",transferCount, sender.getID(), transferAmount, recipient.getID() );

                    return Condition.OK;
                } catch (InsufficientFundsAccountException e) {
                    return Condition.INSUFFICIENT_FUNDS;
                }
            }
        }
    }
}

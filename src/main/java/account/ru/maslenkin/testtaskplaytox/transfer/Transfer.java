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

    public Transfer(int transferLimit) {
        this.transferLimit = transferLimit;
    }

    public ConditionTransfer sendMoney(int transferAmount, Account sender, Account recipient) {
        synchronized (lock) {
            if (transferCount >= transferLimit) {
                return ConditionTransfer.EXCESS_TRANSFER;
            } else {
                try {
                    sender.withdrawMoney(transferAmount);
                    recipient.addMoney(transferAmount);
                    ++transferCount;
                    logger.info("Number of transaction {} Account {} sent {} Sender balance {} Recipient {} Recipient balance {}", transferCount, sender.getID(), transferAmount, sender.getMoney(), recipient.getID(), recipient.getMoney());
                    return ConditionTransfer.OK;
                } catch (InsufficientFundsAccountException e) {
                    logger.warn("Account not enough money", sender.getID());
                    return ConditionTransfer.INSUFFICIENT_FUNDS;
                }
            }
        }
    }

    public ConditionTransfer requireMoney(int transferAmount, Account sender1, Account recipient1) {
        synchronized (lock) {
            if (transferCount >= transferLimit) {
                return ConditionTransfer.EXCESS_TRANSFER;
            } else {
                try {
                    sender1.withdrawMoney(transferAmount);
                    recipient1.addMoney(transferAmount);
                    ++transferCount;
                    logger.info("Number of transaction {} Account {} sent {} Sender balance {} Recipient {} Recipient balance{}", transferCount, sender1.getID(), transferAmount, sender1.getMoney(), recipient1.getID(), recipient1.getMoney());
                    return ConditionTransfer.OK;
                } catch (InsufficientFundsAccountException e) {
                    logger.warn("Account not enough money", sender1.getID());
                    return ConditionTransfer.INSUFFICIENT_FUNDS;
                }
            }
        }
    }
}

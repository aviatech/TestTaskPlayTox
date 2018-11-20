package account.ru.maslenkin.testtaskplaytox.account;

import account.ru.maslenkin.testtaskplaytox.exception.InsufficientFundsAccountException;
import account.ru.maslenkin.testtaskplaytox.transfer.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import account.ru.maslenkin.testtaskplaytox.transfer.ConditionTransfer;

import java.util.Random;
import java.util.concurrent.locks.Condition;

public class Account implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Account.class);
    private String ID;
    private int money;
    private Transfer transfer;
    private Account recipient;

    public Account() {
        this.ID = generateId();
        this.money = 10000;
    }

    public String getID() {
        return ID;
    }

    public int getMoney() {
        return money;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public void withdrawMoney(int transferAmount) throws InsufficientFundsAccountException {
        if (money < transferAmount) {
            throw new InsufficientFundsAccountException();
        } else {
            money -= transferAmount;
        }
    }

    public void addMoney(int transferAmount) {
        money += transferAmount;
    }

    private int getSleep() {
        return new Random().nextInt(1001) + 1000;
    }

    private int generateAmount() {
        Random randomAmount = new Random();
        return randomAmount.nextInt(10000);
    }

    private Boolean generateTypeOperation() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private String generateId() {
        int lengthStringId = 5;
        int firstSymbol = 65;
        int lastSymbol = 90;
        Random random = new Random();
        StringBuilder stringIdBuilder = new StringBuilder(lengthStringId);
        for (int i = 0; i < lengthStringId; ++i) {
            int randomStringId = firstSymbol + (int) (random.nextFloat() * (lastSymbol - firstSymbol + 1));
            char id = (char) randomStringId;
            stringIdBuilder.append(id);
        }
        return stringIdBuilder.toString();
    }

    public void run() {
        logger.debug("Create account {}", this.ID);
        Boolean operation = generateTypeOperation();
        try {
            ConditionTransfer conditionTransfer = ConditionTransfer.UNDEFINED;
            while (conditionTransfer != ConditionTransfer.EXCESS_TRANSFER) {
                if (operation) {
                    conditionTransfer = transfer.sendMoney(generateAmount(), this, recipient);
                } else {
                    conditionTransfer = transfer.requireMoney(generateAmount(), this, recipient);
                }
                Thread.sleep(getSleep());
            }
        } catch (InterruptedException e) {
            logger.error("Interrupted account", this.getID());
            Thread.currentThread().interrupt();
        }
    }
}

package account.ru.maslenkin.testtaskplaytox.account;

import account.ru.maslenkin.testtaskplaytox.exception.InsufficientFundsAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Account implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Account.class);
    private String ID;
    private int money;

    public Account() {
        this.ID = generateId();
        this.money = 10000;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void sendMoney(int transferAmount) throws InsufficientFundsAccountException {
        if (money < transferAmount) {
            throw new InsufficientFundsAccountException();
        } else {
            money -= transferAmount;
        }
    }

    public void receiveMoney(int transferAmount) {
        money += transferAmount;
    }

    private int getSleep() {
        return new Random().nextInt(1001) + 1000;
    }

    private String generateId() {
        int lengthStringId = 5;
        int firstSymbol = 65;
        int lastSymbol = 90;
        Random random = new Random();
        StringBuilder stringIdBuilder = new StringBuilder(lengthStringId);
        for (int i = 0; i < lengthStringId; ++i) {
            int randomStringId = firstSymbol + (int)
                    (random.nextFloat() * (lastSymbol - firstSymbol + 1));
            char id = (char) randomStringId;
            stringIdBuilder.append(id);
        }
        return stringIdBuilder.toString();
    }

    public void run() {
        logger.debug("Account {} start", this.ID );
    }
}

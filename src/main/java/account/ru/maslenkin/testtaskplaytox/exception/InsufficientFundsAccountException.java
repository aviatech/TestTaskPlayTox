package account.ru.maslenkin.testtaskplaytox.exception;

public class InsufficientFundsAccountException extends Exception {
    public InsufficientFundsAccountException(String messageException) {
        super(messageException);
    }

    public InsufficientFundsAccountException() {
    }
}

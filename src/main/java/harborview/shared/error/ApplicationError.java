package harborview.shared.error;

public sealed interface ApplicationError
        permits GeneralError, SqlError {
    int getStatus();
    String getMsg();
}


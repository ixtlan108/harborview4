package harborview.rapanui.kernel.error;

public sealed interface Error {

    int BUSINESS_ERROR = 10;
    int TECHNICAL_ERROR = 20;
    int DATABASE_ERROR = 30;
    int DUPLICATE_KEY_ERROR = 30;
    int UNSPECIFIED_ERROR = 100;

    int getStatus();
    String getMsg();

    //sealed interface BusinessError extends Error {
    //  record SomethingWrong (String msg) implements BusinessError {
    //}

    record BusinessError (String msg) implements Error {
        @Override
        public int getStatus() {
            return BUSINESS_ERROR;
        }
        @Override
        public String getMsg() {
            return msg;
        }
    }

    record TechnicalError (String msg) implements Error {
        @Override
        public int getStatus() {
            return TECHNICAL_ERROR;
        }
        @Override
        public String getMsg() {
            return msg;
        }
    }

    sealed interface DatabaseError extends Error {
        record GeneralError(String msg) implements DatabaseError {
            @Override
            public int getStatus() {
                return DATABASE_ERROR;
            }
            @Override
            public String getMsg() {
                return msg;
            }
        }
        record DuplicateKeyError(String msg) implements DatabaseError {
            @Override
            public int getStatus() {
                return DUPLICATE_KEY_ERROR;
            }
            @Override
            public String getMsg() {
                return msg;
            }
        }
    }

    record UnspecifiedError (String msg) implements Error {
        @Override
        public int getStatus() {
            return UNSPECIFIED_ERROR;
        }
        @Override
        public String getMsg() {
            return msg;
        }
    }
}
    /*
    record ValidationErrors(List<ValidationError> errors, String msg) implements Error {
        @Override
        public int getStatus() {
            return 0;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    record MultipleErrors(List<Error> errors, String msg) implements Error {
        @Override
        public int getStatus() {
            return 0;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

     */

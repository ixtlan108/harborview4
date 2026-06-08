package harborview.rapanui.core.application.common;

import harborview.shared.functional.Either;
import org.jspecify.annotations.NonNull;
import org.mybatis.spring.MyBatisSystemException;
//import org.postgresql.util.PSQLException;
import org.springframework.dao.DuplicateKeyException;
import harborview.rapanui.kernel.error.Error;
import org.springframework.jdbc.BadSqlGrammarException;

import java.util.function.Supplier;

public class Handler {

    @FunctionalInterface
    public interface SaveCommand<T> {
        void handle() throws Exception;
    };

    public static <T> Either<Error, Void> handleCommand(SaveCommand<T> cmd) {
        try {
            cmd.handle();
            return Either.right(null);
        }
        catch (DuplicateKeyException ex) {
            return duplicateKeyError(ex);
        }
        catch (MyBatisSystemException mex) {
            return mybatisError(mex);
        }
        catch (BadSqlGrammarException bex) {
            return badGrammarError(bex);
        }
        catch (Exception ex) {
            return unspecifiedError(ex);
        }
    }

    public static <T> Either<Error, T> handleQuery(Supplier<T> cmd) {
        try {
            var result = cmd.get();
            return Either.right(result);
        }
        catch (DuplicateKeyException ex) {
            return duplicateKeyError(ex);
        }
        catch (MyBatisSystemException mex) {
            return mybatisError(mex);
        }
        catch (BadSqlGrammarException bex) {
            return badGrammarError(bex);
        }
        catch (Exception ex) {
            return unspecifiedError(ex);
        }
    }

    private static <T> @NonNull Either<Error, T> badGrammarError(BadSqlGrammarException bex) {
        return Either.left(new Error.DatabaseError.GeneralError(bex.getMessage()));
    }

    private static <T> @NonNull Either<Error, T> duplicateKeyError(DuplicateKeyException ex) {
        return Either.left(new Error.DatabaseError.DuplicateKeyError(ex.getMessage()));
    }

    private static <T> @NonNull Either<Error, T> mybatisError(MyBatisSystemException mex) {
        if (mex.getCause() != null) {
            var msg = mex.getCause().getLocalizedMessage();
            return Either.left(new Error.DatabaseError.GeneralError(msg));
        }
        else {
            var msg = mex.getMessage();
            return Either.left(new Error.DatabaseError.GeneralError(msg));
        }
    }
    private static <T> @NonNull Either<Error, T> unspecifiedError(Exception ex) {
        return Either.left(new Error.UnspecifiedError(ex.getMessage()));
    }

}

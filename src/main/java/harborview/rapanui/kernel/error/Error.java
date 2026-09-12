package harborview.rapanui.kernel.error;


import java.io.Serializable;
import java.util.List;

/**
 * Hierarchy of possible error values.
 *
 * @author Enrique Medina Montenegro
 */
public sealed interface Error extends Serializable {

    record ValidationErrors(List<ValidationError> errors) implements Error {
    }

    record MultipleErrors(List<Error> errors) implements Error {
    }

    sealed interface BusinessError extends Error {

        record UnknownArticle(String id) implements BusinessError {
        }

        record InvalidId(String id) implements BusinessError {
        }

    }

    sealed interface TechnicalError extends Error {

        record SomethingWentWrong(String message) implements TechnicalError {
        }

    }

}

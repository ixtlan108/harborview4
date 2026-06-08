package harborview.rapanui.core.application.common;

import harborview.rapanui.kernel.error.Error;
import harborview.shared.api.response.AppStatusCode;
import harborview.shared.api.response.PayloadResponse;
import harborview.shared.functional.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtil {
    public static <T> ResponseEntity<PayloadResponse<T>> mapQuery(Either<? extends Error, T> result) {
        if (result.isRight()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new PayloadResponse<>(result.getRight(), 0, null));
        }
        else {
            var err = result.getLeft();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new PayloadResponse<>(null, err.getStatus(), err.getMsg()));
        }
    }
    public static <T> ResponseEntity<PayloadResponse<T>> mapCommand(Either<? extends Error, Void> result) {
        return null;

    }
}

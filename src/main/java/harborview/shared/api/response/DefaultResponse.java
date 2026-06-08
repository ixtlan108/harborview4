package harborview.shared.api.response;

import com.fasterxml.jackson.annotation.JsonGetter;

public record DefaultResponse(@JsonGetter("status") int status,
                              @JsonGetter("msg") String msg) {
}

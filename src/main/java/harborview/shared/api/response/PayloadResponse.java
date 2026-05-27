package harborview.shared.api.response;

public record PayloadResponse<T>(T payload, int appStatusCode, String error) {
}

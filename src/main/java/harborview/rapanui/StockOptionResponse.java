package harborview.rapanui;

public record StockOptionResponse(double spot, StockOptionDTO option, int optionStatus, String msg) {
}

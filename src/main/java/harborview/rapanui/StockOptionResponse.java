package harborview.rapanui;

import harborview.rapanui.kernel.dto.StockOptionDTO;

public record StockOptionResponse(double spot, StockOptionDTO option, int optionStatus, String msg) {
}

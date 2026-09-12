package harborview.rapanui.core.api.critter.command;

public final class FetchCrittersCommand {
    private FetchCrittersCommand() {

    }
    public static FetchCrittersCommand create () {
        return new FetchCrittersCommand();
    }
}

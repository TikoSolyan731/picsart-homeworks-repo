package reception;

public abstract class AbstractReception implements Reception {
    private final Map placement;

    public AbstractReception(Map placement) {
        this.placement = placement;
    }

    public Map getPlacement() {
        return placement;
    }
}

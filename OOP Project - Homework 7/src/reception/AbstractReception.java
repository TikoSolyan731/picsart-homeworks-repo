package reception;

public abstract class AbstractReception implements Reception {
    private final City placement;

    public AbstractReception(City placement) {
        this.placement = placement;
    }

    public City getPlacement() {
        return placement;
    }
}

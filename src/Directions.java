/**
 * @author Shivashriganesh Mahato
 */
public enum Directions {
    NORTH(null), EAST(null), SOUTH(NORTH), WEST(EAST);

    private Directions opposite;

    Directions(Directions opposite) {
        if (opposite != null) {
            this.opposite = opposite;
            opposite.setOpposite(this);
        }
    }

    public Directions getOpposite() {
        return opposite;
    }

    private void setOpposite(Directions opposite) {
        this.opposite = opposite;
    }

    public Vector coorsAfterMove(Vector curCores) {
        Vector afterCoors = new Vector(-1, -1);

        switch (this) {
            case NORTH:
                afterCoors.x = curCores.x;
                afterCoors.y = curCores.y - 1;
                break;
            case EAST:
                afterCoors.x = curCores.x + 1;
                afterCoors.y = curCores.y;
                break;
            case SOUTH:
                afterCoors.x = curCores.x;
                afterCoors.y = curCores.y + 1;
                break;
            case WEST:
                afterCoors.x = curCores.x - 1;
                afterCoors.y = curCores.y;
                break;
        }

        return afterCoors;
    }
}

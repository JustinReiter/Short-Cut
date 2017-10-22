package NMCS;

import Objects.Arrangement;
import Objects.Item;

public class Action {
    Item item;
    Arrangement arrangement;

    public Action(Item item, Arrangement arrangement) {
        this.item = new Item(item);
        this.arrangement = new Arrangement(arrangement);
    }
}

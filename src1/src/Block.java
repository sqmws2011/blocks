/**
 * Created by IntelliJ IDEA.
 * User: grumpold
 * Date: 21.10.11
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public class Block {

    public int id;
    public boolean orientation;
    public int x;
    public int y;
    public int length;

    public Block(int i, boolean h, int i1, int i2, int i3) {
        this.id = i;
        this.orientation = h;
        this.x = i1;
        this.y = i2;
        this.length = i3;
    }

}

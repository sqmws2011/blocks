import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int noBlocks = Integer.parseInt(args[2]);
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int i = 3; i < noBlocks*5 + 3; i+= 5)
        {
            blocks.add(new Block(Integer.parseInt(args[i]), (args[i+1].equals("h")) ? true:false, Integer.parseInt(args[i+2]), Integer.parseInt(args[i+3]), Integer.parseInt(args[i+4])));
        }

        int id = Integer.parseInt(args[args.length - 2]);
        int move = Integer.parseInt(args[args.length - 1]);

        Main main = new Main();
        main.start(width, height, noBlocks, blocks, id, move);
    }

    ArrayList<Block> blocks;

    public void start(int width, int height, int noBlocks, ArrayList<Block> blocks, int id, int move) {

        this.blocks = blocks;

        if(checkAllIntersects(blocks) || !allBlocksInField(blocks, width, height))
        {
            System.out.println("Initial invalid");
            return;
        }

        Block target = null;
        for(Block x : blocks)
        {
            if(x.id == id)
            {
                target = x;
                break;
            }
        }
        if(target == null)
        {
            System.out.println("bad target");
            return;
        }

        int step = move > 0 ? 1 : -1;
        for(int i = 0;i < Math.abs(move); i++)
        {
            if(target.orientation)
            {
                target.x += step;
            }
            else
            {
                target.y += step;
            }
            if(checkAllIntersects(blocks) || !allBlocksInField(blocks, width, height))
            {
                System.out.println("true - invalid move");
                return;
            }


        }

        System.out.print("false - move is allowed");
        return;
    }

    private boolean allBlocksInField(ArrayList<Block> blocks, int width, int height)
    {
        for(Block b : blocks)
        {
            if(b.orientation)
            {
                if(b.x <= 0 ||b.y <= 0 || b.x + b.length > width+1)
                {
                    return false;
                }
            }
            else
            {
                if(b.x <= 0 ||b.y <= 0 || b.y + b.length > height+1)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkAllIntersects(ArrayList<Block> blocks)
    {
        for(Block a : blocks)
        {
            for(Block b : blocks)
            {
                if(a.id != b.id)
                {
                    if(intersect(a, b))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean intersect(Block a, Block b) {
        if(a.orientation)
        {   //a is hor
            for(int xa = a.x; xa < a.x + a.length; xa++)
            {
                if(b.orientation)
                {
                    for(int xb = b.x; xb < b.x+ b.length; xb++)
                    {
                        if(a.y == b.y && xa == xb)
                        {
                            return true;
                        }
                    }
                }
                else
                {
                    for(int yb = b.y; yb < b.y + b.length; yb++)
                    {
                         if(a.y == yb && xa == b.x)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        else
        {//a is ver
            for(int ya = a.y; ya < a.y + a.length; ya++)
            {
               if(b.orientation)
                {
                    for(int xb = b.x; xb < b.x+ b.length; xb++)
                    {
                        if(ya == b.y && a.x == xb)
                        {
                            return true;
                        }
                    }
                }
                else
                {
                    for(int yb = b.y; yb < b.y + b.length; yb++)
                    {
                        if(ya == yb && a.x == b.x)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void initField() {
        //To change body of created methods use File | Settings | File Templates.
    }
}

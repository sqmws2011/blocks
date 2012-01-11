import java.util.ArrayList;
import java.util.Collections;

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

        //int id = Integer.parseInt(args[args.length - 2]);
        //int move = Integer.parseInt(args[args.length - 1]);

        Main main = new Main();
        main.start(width, height, noBlocks, blocks);
    }

    ArrayList<Block> blocks;

    public void start(int width, int height, int noBlocks, ArrayList<Block> blocks) {

        this.blocks = blocks;

        if(checkAllIntersects(blocks) || !allBlocksInField(blocks, width, height))
        {
            System.out.println("Initial invalid");
            return;
        }

        ArrayList<Integer> blockedOnes = new ArrayList<Integer>();
        for(Block b : blocks)
        {
            boolean vor = canMove(blocks, b, width, height, 1);
            boolean back = canMove(blocks, b, width, height, -1);
            if(!vor && !back)
            {
                blockedOnes.add(b.id);
            }
        }

        Collections.sort(blockedOnes);

        for(Integer i : blockedOnes)
        {
            System.out.print(i + " ");
        }

        return;
    }

    private boolean canMove(ArrayList<Block> blocks, Block target, int width, int height, int steps)
    {
        int step = steps > 0 ? 1 : -1;
        int i;
        for(i = 1;i <= Math.abs(steps); i++)
        {
            if(target.orientation)
            {
                target.x += step;
            }
            else
            {
                target.y += step;
            }

            if(intersectsBlock(blocks, target) || !blockInField(target, width, height))
            {
                if(target.orientation)
                {
                    target.x -= step*(i);
                }
                else
                {
                    target.y -= step*(i);
                }
                return false;
            }
        }
        if(target.orientation)
        {
            target.x -= step*(i);
        }
        else
        {
            target.y -= step*(i);
        }
        return true;
    }

    private boolean blockInField(Block candidate, int width, int height)
    {
        int dummy = 0;
        if(candidate.x < 1 || candidate.y < 1 )
        {
            return false;
        }
        else
        {dummy++;}

        if(candidate.orientation && (candidate.x + candidate.length) > width+1)
        {
            return false;
        }
        else
        { dummy++; }
        if(!candidate.orientation && (candidate.y + candidate.length) > height+1)
        {
            return false;
        }
        else
        {dummy++;}
        return true;

    }

    private boolean allBlocksInField(ArrayList<Block> blocks, int width, int height)
    {
        for(Block candidate : blocks)
        {
            int dummy = 0;
            if(candidate.x < 1 || candidate.y < 1 )
            {
                return false;
            }
            else
            {dummy++;}

            if(candidate.orientation && (candidate.x + candidate.length) > width+1)
            {
                return false;
            }
            else
            { dummy++; }
            if(!candidate.orientation && (candidate.y + candidate.length) > height+1)
            {
                return false;
            }
            else
            {dummy++;}
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

    private boolean intersectsBlock(ArrayList<Block> blocks, Block target)
    {
        for(Block a : blocks)
        {
            if(a.id != target.id)
            {
                if(intersect(a, target))
                {
                    return true;
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
}

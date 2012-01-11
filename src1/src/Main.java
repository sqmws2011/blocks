import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int arg0 = Integer.parseInt(args[0]);
        String arg1 = args[1];
        int arg2 = Integer.parseInt(args[2]);
        int arg3 = Integer.parseInt(args[3]);
        int arg4 = Integer.parseInt(args[4]);
        int arg5 = Integer.parseInt(args[5]);
        String arg6 = args[6];
        int arg7 = Integer.parseInt(args[7]);
        int arg8 = Integer.parseInt(args[8]);
        int arg9 = Integer.parseInt(args[9]);


        ArrayList<Block> blocks = new ArrayList<Block>();
        for(int i = 0; i < args.length; i+= 5)
        {
            blocks.add(new Block(Integer.parseInt(args[i]), (args[i+1].equals("h")) ? true:false, Integer.parseInt(args[i+2]), Integer.parseInt(args[i+3]), Integer.parseInt(args[i+4])));
        }

        Main main = new Main();
        main.start(blocks);
    }

    int[][] field;

    public void start(ArrayList<Block> blocks) {

        for(Block a : blocks)
        {
            for(Block b : blocks)
            {
                if(a.id != b.id)
                {
                    if(intersect(a, b))
                    {
                        System.out.print("true");
                        return;
                    }
                }
            }
        }
        System.out.print("false");
        return;
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

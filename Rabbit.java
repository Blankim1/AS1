public class Rabbit extends Animal 
{
    private boolean haveSeenFox= false;
    private boolean canSeeFoxNow = false;
    private int distanceToFox;
    private int directionToFox;
    private int currentDirectionR;
    private boolean haveSeenBush= false;
    private int distanceToBush;
    private int directionToBush;
    private boolean canSeeBushNow = false;

    public Rabbit(Model model, int row, int column) 
    {
        super(model, row, column);
        currentDirectionR = Model.random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
    }
    
    // Find the bush row
    int bushRow(int bushDir, int bushDis)
    {
        switch(bushDir)
        {
            case Model.NW:
            case Model.N:
            case Model.NE:
                return this.row - bushDis;
            case Model.W:
            case Model.E:
                return this.row;
            case Model.SW:
            case Model.S:
            case Model.SE:
                return this.row + bushDis;       
            default:
                return 0;
        }       
    }
    
    // Find the bush column
    int bushCol(int bushDir, int bushDis)
    {
        switch(bushDir)
        {
            case Model.NW:
            case Model.W:
            case Model.SW:
                return this.column - bushDis;
            case Model.N:
            case Model.S:
                return this.column;
            case Model.NE:
            case Model.E:
            case Model.SE:
                return this.column + bushDis;       
            default:
                return 0;
        }
    }
    
    // Determine if the bush is at the edge
    boolean edgeBush(int bushDir, int bushDis)
    {
        if(bushRow(bushDir, bushDis) == 0 || bushRow(bushDir, bushDis) == 19 
        || bushCol(bushDir, bushDis) == 0 || bushCol(bushDir, bushDis) == 19)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    
    int decideMove() 
    {
        // look all around for bush;
        canSeeBushNow = false;
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) 
        {
            if (look(i) == Model.BUSH && !edgeBush(i, distance(i)))
            {
                canSeeBushNow = haveSeenBush = true;
                directionToBush = i;
                distanceToBush = distance(i);
            }
            
            // look all around for fox;
            canSeeFoxNow = false;
            if (look(i) == Model.FOX) 
            {
                canSeeFoxNow = haveSeenFox = true;
                directionToFox = i;
                distanceToFox = distance(i);
                
                if(canMove(Model.turn(i, 5)))
                {
                    return Model.turn(i, 5);
                }
                else if(canMove(Model.turn(i, 3)))
                {
                    return Model.turn(i, 3);
                }
                else if(canMove(Model.turn(i, 6)))
                {
                    return Model.turn(i, 6);
                }
                else if(canMove(Model.turn(i, 2)))
                {
                    return Model.turn(i, 2);
                }
                else if(canMove(Model.turn(i, 7)))
                {
                    return Model.turn(i, 7);
                }
                else if(canMove(Model.turn(i, 1)))
                {
                    return Model.turn(i, 1);
                }
                else
                {
                    return Model.turn(i, 4);
                }
            }
            
        }
        
        // If have seen the bush, distance closer.
        // If not go randomly
        if(haveSeenBush)
        {
            if (distanceToBush > 0) 
            {
                distanceToBush--;
                return directionToBush;
            }
            else 
            {
                haveSeenBush = false;
                currentDirectionR = Model.random(Model.MIN_DIRECTION,
                                                Model.MAX_DIRECTION);
            }
        }
        
        // if haven't seen bush
        // continue with current direction
        if (canMove(currentDirectionR))
        {
            return currentDirectionR;
        }
        else if (canMove(Model.turn(currentDirectionR, 1)))
        {
            return Model.turn(currentDirectionR, 1);
        }
        else if (canMove(Model.turn(currentDirectionR, -1)))
        {
            return Model.turn(currentDirectionR, -1);
        }
        else 
        {
            currentDirectionR = Model.random(Model.MIN_DIRECTION,
                                            Model.MAX_DIRECTION);
            for (int i = 0; i < 8; i++) 
            {
                if (canMove(currentDirectionR))
                    return currentDirectionR;
                else
                    currentDirectionR = Model.turn(currentDirectionR, 1);
            }
        }
        
        // If have seen fox, rabbit will move depend on the location of bush.
        // This part does not work so good.....
        if(haveSeenFox)
        {
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.E) 
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, 1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, -1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.N && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, -3);
            }
            
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, 2);  
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, -1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, 1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.E && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, 3);
            }
                
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, -1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.S && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, 1);
            }
            
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, 1);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.W && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, -1);
            }
            
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NE && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, 4);
            }
            
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.NW && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, 4);
            }
                  
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SE && directionToBush == Model.SW)
            {
                return Model.turn(directionToFox, 2);
            }
            
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.N)
            {
                return Model.turn(directionToFox, 3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.E)
            {
                return Model.turn(directionToFox, -3);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.S)
            {
                return Model.turn(directionToFox, -2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.W)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.NE)
            {
                return Model.turn(directionToFox, 4);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.NW)
            {
                return Model.turn(directionToFox, 2);
            }
            if(canMove(currentDirectionR) && directionToFox == Model.SW && directionToBush == Model.SE)
            {
                return Model.turn(directionToFox, -2);
            }
        }
            
        return Model.STAY;
    }
        
        
}

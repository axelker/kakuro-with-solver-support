package solver;
import java.lang.*;
import java.util.*;

import game.CaseBlanche;

import java.awt.im.spi.*;


public interface Constraint{
    public Set<CaseBlanche> getScope();
    public boolean isSatisfiedBy(Map<CaseBlanche,Integer>verifMap);
    public boolean equals(Object c);


}
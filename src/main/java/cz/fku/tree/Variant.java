package cz.fku.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 05.03.2017.
 */
public class Variant {
    private List<ENProduct> constraints = new ArrayList<>();

    public void addConstraint(ENProduct constraint){
        this.constraints.add(constraint);
    }

    //GETTERS SETTERS
    public List<ENProduct> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ENProduct> constraints) {
        this.constraints = constraints;
    }
}


package cz.fku.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 05.03.2017.
 */
public class Condition {
    private String name;
    private List<Variant> variants = new ArrayList<>();

    public void addVariant(Variant variant) {
        this.variants.add(variant);
    }

    //GETTERS SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}

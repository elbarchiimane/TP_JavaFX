package ma.enset.tpjavafxorm.dao.entities;

import java.io.Serializable;

public class Category implements Serializable {
    private  long id;
    private String name;

    public Category() {
    }

    public Category( String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

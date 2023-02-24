package ma.enset.tpjavafxorm.dao.entities;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private String reference;
    private float prix;
    private Category category;

    public Product() {
    }

    public Product(String name, String reference, float prix) {
        this.name = name;
        this.reference = reference;
        this.prix = prix;

    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReference() {
        return reference;
    }

    public float getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", prix=" + prix +
                ", category=" + category +
                '}';
    }
}

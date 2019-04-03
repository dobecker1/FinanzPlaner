package models.category;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryImpl implements Category {


    private int id;

    private String name;

    @Override
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

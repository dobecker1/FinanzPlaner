package models.category;

import models.basic.BasicModel;

public interface Category extends BasicModel {

    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

}

package model;

import java.util.Objects;

public class Item {

    private String url;
    private String name;
    private String size;
    private double cost;
    private String count;

    public Item(String url, String name, String size, double cost) {
        this.url = url;
        this.name = name;
        this.size = size;
        this.cost = cost;
    }
    public Item(String name, String size, double cost,String count) {
        this.name = name;
        this.size = size;
        this.cost = cost;
        this.count = count;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String name) {
        this.name = name;
    }


    public double getItemCost() {
        return cost;
    }

    public void setItemCost(double cost) {
        this.cost = cost;
    }
    public String getItemUrl() {
        return url;
    }

    public void setItemUrl(String url) {
        this.url = url;
    }
    public String getItemSize() {
        return size;
    }

    public void setItemSize(String size) {
        this.size = size;
    }
    public String getItemCount() {
        return count;
    }

    public void setItemCount(String count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getItemName(), item.getItemName()) &&
                        Objects.equals(getItemSize(), item.getItemSize())&&
                                Objects.equals(getItemCost(), item.getItemCost());
    }
}
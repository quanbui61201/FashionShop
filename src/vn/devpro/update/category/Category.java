package vn.devpro.update.category;

public class Category {

    private int id;
    private String code;
    private String name;

    public void display() {
        System.out.printf("%5d %-10s %-30s%n", this.id, this.code, this.name);
    }

    public Category() {
    }

    public Category(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

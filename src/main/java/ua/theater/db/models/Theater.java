package ua.theater.db.models;

public class Theater {

    private int id;
    private String name;
    private String tel;
    private String address;
    private int PlaysCount;

    public int getPlaysCount() {
        return PlaysCount;
    }

    public void setPlaysCount(int playsCount) {
        PlaysCount = playsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

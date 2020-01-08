package shop.bean;

public class CartBean  {
    private String id;
    private String car_name;
    private String car_introduction;
    private String car_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_introduction() {
        return car_introduction;
    }

    public void setCar_introduction(String car_introduction) {
        this.car_introduction = car_introduction;
    }

    public String getCar_price() {
        return car_price;
    }

    public void setCar_price(String car_price) {
        this.car_price = car_price;
    }
}

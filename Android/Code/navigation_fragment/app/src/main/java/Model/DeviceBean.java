package Model;

/**
 * Created by eason on 2016-04-22.
 */
public class DeviceBean {
    private String name;
    private String date;
    private String id;

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package paliwo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaliwoDetails {

    private final StringProperty name;
    private final StringProperty cost;

    public  PaliwoDetails(String name, String cost){
        this.name = new SimpleStringProperty(name);
        this.cost = new SimpleStringProperty(cost);
    }

    public String getName() {
        return name.get();
    }

    public String getCost() {
        return  cost.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public void setCost(String value) {
        cost.set(value);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public StringProperty costProperty(){
        return cost;
    }
}

package Observer;

//import java.util.Observable; deprecated since 9

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventSource{
    private String value;

    private PropertyChangeSupport support;

    public EventSource() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }

    public void setValue(String value){
        support.firePropertyChange("value", this.value, value);
        this.value = value;
    }
}
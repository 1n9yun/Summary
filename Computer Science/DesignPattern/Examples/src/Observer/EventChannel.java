package Observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventChannel implements PropertyChangeListener {
    private String value;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("changed! " + value + " -> " + evt.getNewValue());
        this.setValue((String) evt.getNewValue());
    }

    private void setValue(String newValue) {
        this.value = newValue;
    }
}
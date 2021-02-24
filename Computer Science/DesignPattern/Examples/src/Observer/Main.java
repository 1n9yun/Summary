package Observer;

public class Main {
    public static void main(String[] args) {
        EventChannel eventListener = new EventChannel();
        EventSource eventSource = new EventSource();

        eventSource.addPropertyChangeListener(eventListener);
        eventSource.setValue("hey");
    }
}

package GUI.Comunication;

public interface Sender {
    public void registerListener(Listener l);
    public void removeListener(Listener l);
    public void notifyListeners();
}

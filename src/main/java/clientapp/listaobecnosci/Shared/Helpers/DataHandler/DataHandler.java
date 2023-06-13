package clientapp.listaobecnosci.Shared.Helpers.DataHandler;

public class DataHandler<T> {
    private String actionName;
    private T object;

    public DataHandler() {
    }

    public DataHandler(String actionName, T object) {
        this.actionName = actionName;
        this.object = object;
    }

    public String getActionName() {
        return actionName;
    }

    public T getObject() {
        return object;
    }
}


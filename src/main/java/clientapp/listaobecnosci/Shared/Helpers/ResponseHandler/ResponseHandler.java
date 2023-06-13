package clientapp.listaobecnosci.Shared.Helpers.ResponseHandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHandler<T> {
    private T _data;
    private ArrayList<String> _errors;
    private boolean _isSuccess;

    public ResponseHandler() {
    }
    public ResponseHandler(T data, ArrayList<String> errors, boolean isSuccess) {
        _data = data;
        _errors = errors;
        _isSuccess = isSuccess;
    }

    public ResponseHandler(T data, String error, boolean isSuccess) {
        _data = data;
        _errors = new ArrayList<String>();
        _errors.add(error);
        _isSuccess = isSuccess;
    }

    public ResponseHandler(T data, boolean isSuccess) {
        _data = data;
        _errors = new ArrayList<String>();
        _isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return _isSuccess;
    }

    public void setSuccess(boolean success) {
        this._isSuccess = success;
    }

    public T getData() {
        return _data;
    }

    public void setData(T data) {
        this._data = data;
    }

    public ArrayList<String> getErrors() {
        return _errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this._errors = errors;
    }

}

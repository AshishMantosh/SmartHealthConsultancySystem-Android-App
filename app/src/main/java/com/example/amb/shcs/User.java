package com.example.amb.shcs;


public class User {

    private int _id;
    private String _email;
    private String _password;
    private String _name;
    private String _type;

    public User() {
    }

    public User(String email, String password, String name, String type) {
        this._email = email;
        this._password = password;
        this._name = name;
        this._type = type;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public int get_id() {
        return _id;
    }

    public String get_email() {
        return _email;
    }

    public String get_password() {
        return _password;
    }

    public String get_name() {
        return _name;
    }

    public String get_type() {
        return _type;
    }
}

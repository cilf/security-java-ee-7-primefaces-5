package cz.cilf.security;

public class User {
    private final String name;

    private final String password;

    private final String page;

    public User(String name, String password, String page) {
        this.name = name;
        this.password = password;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPage() {
        return page;
    }

}

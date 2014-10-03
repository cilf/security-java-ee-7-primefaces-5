package cz.cilf.security;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserService {

    private static Map<String, User> map;

    @PostConstruct
    private void init() {
        map = new HashMap<>();
        map.put("john", new User("John", "a", "index.xhtml"));
        map.put("david", new User("David", "1", "second-page.xhtml"));
    }

    public User findByName(String name) {
        return map.get(name.toLowerCase());
    }
}

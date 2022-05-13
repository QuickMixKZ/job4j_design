package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<User, Integer> union = new HashMap<>();
        previous.forEach(user -> union.put(user, user.getId()));
        current.forEach(user -> union.put(user, user.getId()));
        Set<Integer> ids = new HashSet<>(union.values());
        int changed = union.size() - ids.size();
        int added = ids.size() - previous.size();
        int deleted = ids.size() - current.size();
        return new Info(added, changed, deleted);
    }

}

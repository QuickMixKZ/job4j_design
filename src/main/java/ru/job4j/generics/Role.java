package ru.job4j.generics;

public class Role extends Base {

    private final String roleName;

    public String getRoleName() {
        return roleName;
    }


    public Role(String id, String name) {
        super(id);
        this.roleName = name;
    }
}

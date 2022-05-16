package ru.job4j.gc;

public class User {

    String name;
    int age;

    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    public User(int age) {
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Finalized: %s%n", this.name);
    }

    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        User user1 = new User(28);
        User user2 = new User("Mikhail");
        User user3 = new User("(Mikhail)");
        for (int i = 0; i < 500; i++) {
            Thread.sleep(1);
            new User(String.valueOf(i));
        }
    }
}

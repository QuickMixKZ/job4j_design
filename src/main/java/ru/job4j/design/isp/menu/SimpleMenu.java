package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result;
        if (Objects.equals(parentName, ROOT) && findItem(childName).isEmpty()) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            result = true;
        } else {
            Optional<ItemInfo> parentItem = findItem(parentName);
            result = parentItem.isPresent();
            MenuItem menuItem;
            if (result) {
                menuItem = parentItem.get().menuItem;
                List<MenuItem> children = menuItem.getChildren();
                children.add(new SimpleMenuItem(childName, actionDelegate));
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> result = Optional.empty();
        Optional<ItemInfo> optionalItemInfo = findItem(itemName);
        if (optionalItemInfo.isPresent()) {
            ItemInfo itemInfo = optionalItemInfo.get();
            MenuItemInfo menuItemInfo = new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            result = Optional.of(menuItemInfo);
        }

        return result;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<MenuItemInfo>() {

            DFSIterator dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo itemInfo = dfsIterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator iterator = new DFSIterator();
        Optional<ItemInfo> result = Optional.empty();
        while (iterator.hasNext()) {
            ItemInfo info = iterator.next();
            String currentName = info.menuItem.getName();
            if (Objects.equals(currentName, name)) {
                result = Optional.of(info);
                break;
            }
        }
        return result;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}

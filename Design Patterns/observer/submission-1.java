interface Observer {
    void notify(final String itemName);
}

class Customer implements Observer {
    private final String name;
    private int notifications;

    public Customer(final String name) {
        this.name = name;
        this.notifications = 0;
    }

    public void notify(final String itemName) {
        this.notifications += 1;
    }

    public int countNotifications() {
        return this.notifications;
    }
}

class OnlineStoreItem {
    private final String itemName;
    private int stock;
    private final List<Observer> subscribers;

    public OnlineStoreItem(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(final Observer observer) {
        subscribers.add(observer);
    }

    public void unsubscribe(final Observer observer) {
        subscribers.remove(observer);
    }

    public void updateStock(final int newStock) {
        if(stock == 0 && newStock > 0) {
            subscribers.forEach(s -> s.notify(itemName));
        }

        stock = newStock;
    }
}

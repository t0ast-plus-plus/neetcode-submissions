class Order {
    private final String contents;
    private final boolean takeOut;

    public Order(final String contents, final boolean takeOut) {
        this.contents = contents;
        this.takeOut = takeOut;
    }

    public String getOrder() {
        return contents;
    }

    public boolean isTakeOut() {
        return takeOut;
    }
}

class Cashier {
    public Order takeOrder(final String contents, final boolean takeOut) {
        return new Order(contents, takeOut);
    }
}

class Food {
  private final String contents;

  public Food(final String order) {
    this.contents = order;
  }

  public String getFood() {
    return contents;
  }
}

class Chef {
    public Food prepareFood(final Order order) {
        return new Food(order.getOrder());
    }
}

class PackagedFood extends Food {
    public PackagedFood(final Food food) {
        super(food.getFood() + " in a bag");
    }
}

class KitchenStaff {
    public PackagedFood packageOrder(final Food food) {
        return new PackagedFood(food);
    }
}

class DriveThruFacade {
    private final Cashier cashier = new Cashier();
    private final Chef chef = new Chef();
    private final KitchenStaff kitchenStaff = new KitchenStaff();

    public Food takeOrder(String orderContents, boolean takeOut) {
        // cashier takes order (always)
        final Order order = cashier.takeOrder(orderContents, takeOut);
        
        // chef cooks the order into food (always)
        Food food = chef.prepareFood(order);
        
        // if the order was to-go, kitchen staff apply packaging to the food
        if(takeOut) {
            food = kitchenStaff.packageOrder(food);
        }

        return food;
    }
}

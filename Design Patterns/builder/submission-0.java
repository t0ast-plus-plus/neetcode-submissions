class Meal {
    private double cost;
    private boolean takeOut;
    private String main;
    private String drink;

    double getCost() {
        return this.cost;
    }

    boolean getTakeOut() {
        return this.takeOut;
    }

    String getMain() {
        return this.main;
    }

    String getDrink() {
        return this.drink;
    }

    // private constructor to force usage of the builder
    private Meal(){};

    // private setters to render the object immutable after building
    private void setCost(double cost) {
        this.cost = cost;
    }

    private void setTakeOut(boolean takeOut) {
        this.takeOut = takeOut;
    }

    private void setMain(String main) {
        this.main = main;
    }

    private void setDrink(String drink) {
        this.drink = drink;
    }
}

class MealBuilder {
    private final Meal mealInProgress;

    public MealBuilder() {
        mealInProgress = new Meal();
    }

    public MealBuilder addCost(double cost) {
        mealInProgress.setCost(cost);
        return this;
    }

    public MealBuilder addTakeOut(boolean takeOut) {
        mealInProgress.setTakeOut(takeOut);
        return this;
    }

    public MealBuilder addMainCourse(String main) {
        mealInProgress.setMain(main);
        return this;
    }

    public MealBuilder addDrink(String drink) {
        mealInProgress.setDrink(drink);
        return this;
    }

    Meal build() {
        return mealInProgress;
    }
}

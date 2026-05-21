class Meal {
    private double cost;
    private boolean takeOut;
    private String main;
    private String drink;

    public double getCost() {
        return cost;
    }

    public boolean getTakeOut() {
        return takeOut;
    }

    public String getMain() {
        return main;
    }

    public String getDrink() {
        return drink;
    }

    // private constructor to force usage of the builder
    private Meal(){};

    // private setters to render the object immutable after building
    private void setCost(final double cost) {
        this.cost = cost;
    }

    private void setTakeOut(final boolean takeOut) {
        this.takeOut = takeOut;
    }

    private void setMain(final String main) {
        this.main = main;
    }

    private void setDrink(final String drink) {
        this.drink = drink;
    }
}

class MealBuilder {
    private final Meal mealToBuild;

    public MealBuilder() {
        mealToBuild = new Meal();
    }

    public MealBuilder addCost(final double cost) {
        mealToBuild.setCost(cost);
        return this;
    }

    public MealBuilder addTakeOut(final boolean takeOut) {
        mealToBuild.setTakeOut(takeOut);
        return this;
    }

    public MealBuilder addMainCourse(final String main) {
        mealToBuild.setMain(main);
        return this;
    }

    public MealBuilder addDrink(final String drink) {
        mealToBuild.setDrink(drink);
        return this;
    }

    public Meal build() {
        return mealToBuild;
    }
}

class SquareHole {
    private final double sideLength;

    public SquareHole(final double sideLength) {
        this.sideLength = sideLength;
    }

    public boolean canFit(final Square square) {
        return this.sideLength >= square.getSideLength();
    }
}

class Square {
    private double sideLength;

    public Square() {}

    public Square(final double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return this.sideLength;
    }
}

class Circle {
    private final double radius;

    public Circle(final double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }
}

class CircleToSquareAdapter extends Square {
    private final Circle circle;

    public CircleToSquareAdapter(final Circle circle) {
      this.circle = circle;
    }

    @Override
    public double getSideLength() {
      return circle.getRadius() * 2;
    }
}

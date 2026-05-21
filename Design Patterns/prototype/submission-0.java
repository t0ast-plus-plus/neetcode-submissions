interface Shape {
    Shape clone();
}

class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(width, height);
    }
}

class Square implements Shape {
    private final int length;

    public Square(final int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

    @Override
    public Shape clone() {
        return new Square(length);
    }
}

class Test {
    public List<Shape> cloneShapes(final List<Shape> shapes) {
        final List<Shape> clones = new ArrayList<>(shapes.size());
        shapes.forEach(s -> clones.add(s.clone()));
        return clones;
    }
}

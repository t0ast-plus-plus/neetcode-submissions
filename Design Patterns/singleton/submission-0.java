static class Singleton {
    private static Singleton instance = null;
    private String value = null;

    private Singleton() {
        //private to prevent direct instantiation
    }

    public static Singleton getInstance() {
        // instantiate internally if not previously done, otherwise return the internal instance
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }
    
}

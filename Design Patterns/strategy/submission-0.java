class Person {
    private final String lastName;
    private final int age;
    private final boolean isMarried;

    public Person(final String lastName, final int age, final boolean isMarried) {
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return isMarried;
    }
}

interface PersonFilter {
    public boolean apply(final Person person);
}

class AdultFilter implements PersonFilter {
    // Implement Adult filter
    @Override
    public boolean apply(final Person person) {
        return person.getAge() >= 18;
    }
}

class SeniorFilter implements PersonFilter {
    // Implement Senior filter
    @Override
    public boolean apply(final Person person) {
        return person.getAge() >= 65;
    }
}

class MarriedFilter implements PersonFilter {
    // Implement Married filter
    @Override
    public boolean apply(final Person person) {
        return person.isMarried();
    }
}

class PeopleCounter {
    private PersonFilter filter;

    public void setFilter(PersonFilter filter) {
        this.filter = filter;
    }

    public int count(final List<Person> people) {
        int count = 0;
        for (final Person p : people) {
            if(filter.apply(p)) {
                count++;
            }
        }
        
        return count;
    }
}

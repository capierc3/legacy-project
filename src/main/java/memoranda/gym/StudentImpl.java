package memoranda.gym;

public class StudentImpl implements Student {
    private ClassList list;

    public ClassList getClasses() {
        return list;
    }

    public void addClass(GymClass gymClass) {
        list.addClass(gymClass);
    }
}

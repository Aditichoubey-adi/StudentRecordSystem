// Student.java
public class Student {
    // Fields (yeh student ki properties hongi)
    private int id;
    private String name;
    private double marks;

    // Constructor (jab hum naya Student object banayenge, toh yeh use initialize karega)
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getter methods (data ko access karne ke liye)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    // Setter methods (data ko update karne ke liye, khaaskar name aur marks)
    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // toString method (student object ko readable format mein print karne ke liye)
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}
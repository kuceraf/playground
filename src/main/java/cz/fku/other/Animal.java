package cz.fku.other;

/**
 * Created by Filip on 27.11.2016.
 */
public class Animal {

    public Animal(){}
    public Animal(int age){
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    public String makeSound() {
        return "Animal sound";
    }
}

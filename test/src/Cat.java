public class Cat {
    private String name ;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void meow() {
        System.out.println("Meow");
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }    

}

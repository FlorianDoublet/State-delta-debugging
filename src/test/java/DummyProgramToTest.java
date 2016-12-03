/**
 * Created by FlorianDoublet on 03/12/2016.
 */
public class DummyProgramToTest {

    public static void main(String[] args) {
        DummyProgramToTest dum = new DummyProgramToTest();
        String name = "bob";
        if(args.length > 0){
            name = args[0];
        }
        dum.doSomething(name);
    }

    public void doSomething(String name){
        try{
            String s = "hello " + name;
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void uncalledMethod(){
        try{
            String s2 = "I'm not supposed to be called ?";
        }catch (Exception e){

        }
    }
}

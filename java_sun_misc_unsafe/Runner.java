
import sun.misc.Unsafe;
import java.lang.reflect.Constructor;

public class Runner {

    public static User getUserWithoutConstructor() throws Exception {
        Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        Unsafe unsafe = unsafeConstructor.newInstance();
        User user = (User) unsafe.allocateInstance(User.class);
        return user;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("TRACER : start");

        User user = getUserWithoutConstructor();
        System.out.println("TRACER : got user : " + user);
    }
}


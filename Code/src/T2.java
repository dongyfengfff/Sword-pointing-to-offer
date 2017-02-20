import java.io.Serializable;

/**
 * Author: zhangxin
 * Time: 2017/2/20 0020.
 * Desc:单利模式的各种写法进阶；
 */
public class T2 {

    class Singleton {

    }


}

/*
* (1)懒汉式,在需要的时候在创建,所以比较懒;
* 在第一次调用的时候先执行了getInstance()方法，再执行构造方法。
* NOTE:懒汉式是一种非线程安全的写法;
* 当有多个线程并发执行Lazy.getInstance()方法时,可能产生:线程1:Lazy.getInstance(),此时判断t2==null,但就在这时线程切换,执行到线程2
* 线程2:Lazy.getInstance(),也是先判断t2==null,由于在线程1中判断t2==null,还没来得及创建,线程就被切换了,所以此时线程2创建t2;并获取到t2,此时再回到线程1
* 线程1:继续执行方法未完成的操作,进行t2的实例化,返回;
* 因此这是一种非线程安全的写法
*
* 使用场景:如果我们需要在getInstance()方法的时候传入参数进来辅助构造方法初始化，那就得用懒汉式.
* */
class Lazy {
    private static T2 t2;

    private Lazy() {
    }

    public static T2 getInstance() {
        if (t2 == null) {
            t2 = new T2();
        }
        return t2;
    }
}

/*
* (2)饿汉式,不管有没有人调用单例模式,只要已加载该类,那么就实例化一个单例
* 在第一次调用的时候先执行了构造方法，再执行getInstance()方法。
* NOTE:饿汉式是一种线程安全的写法;
* 缺点:当我们想获取Hungry中的其它静态变量时,在类加载的时候还是会对t2实例进行初始化，导致时间比较久
* */
class Hungry {
    private static T2 t2 = new T2();

    private Hungry() {
    }

    public static T2 getInstance() {
        return t2;
    }
}

/*
* (3)线程安全式,鉴于懒汉式是线程非安全的,那么对此进行改进,改进的方式很简单,直接在getInstance()方法前加上synchronized,一旦线程一获取到锁，线程二只能在外面等待着。
* 这种方法虽然实现了需求,但是还有可以优化的空间
* */
class ThreadSafe {
    private static T2 t2;

    private ThreadSafe() {
    }

    public synchronized static T2 getInstance() {
        if (t2 == null) {
            t2 = new T2();
        }
        return t2;
    }
}

/*
* (4)线程安全之双重检验锁
* 需要注意的点:
* 1)使用volatile来修饰单例变量
* JVM在创建一个变量时并不是原子操作,而是分为以下三个步骤:
* - 在堆空间里分配一部分空间；
* - 执行T2的构造方法进行初始化
* - 把t2对象指向在堆空间里分配好的空间。
* 但是，当我们编译的时候，编译器在生成汇编代码的时候会对流程顺序进行优化。优化的结果是有可能按照1-2-3顺序执行，也可能按照1-3-2顺序执行。
* 如果是按照1-3-2的方式来执行,如果先执行3,那么t2此时就不为null,但是其构造方法还没有执行,对象还为初始化,此时另一个线程进来,一看t2!=null,直接返回t2,去使用T2中的方法,那肯定报空指针错误;
* 2)两次单例变量为null的判断:这里要对比饿汉式中发生的线程不安全的情况来说明;
* 第一次检验:当有多有线程调用getInstance()方法的时候，先让他们进来。如果t2实例不为空，那最好了，直接return实例t2，跟synchronized一点都扯不上关系，所以也不会影响到性能。这是双重检验中的第一次检验。”
* 第二次检验:当有多个线程通过第一次检验时，假设线程拿到锁进入synchronized语句块，对t2实例进行初始化，释放t2.class锁之后，线程二持有这个锁进入synchronized语句块，此时又对fileIO
* 对象就行初始化。所以在这里进行第二次检验防止这种意外发生
*
* */
class DoubleCheckLock {
    private static volatile T2 t2; //(1)线程间同步;(2)防止编译器指令优化;这里用到了其第二个特性;

    private DoubleCheckLock() {
    }

    public static T2 getInstance() {
        if (t2 == null) {
            synchronized (DoubleCheckLock.class) {
                if (t2 == null) {
                    t2 = new T2();
                }
            }
        }
        return t2;
    }
}


/*
* (4)线程安全之静态内部类
* 根据使用场景不同来使用不一样的单例模式。
* 如果我们需要在getInstance()方法的时候传入参数进来辅助构造方法初始化，那就得用懒汉式了
* 其他情况就可以使用饿汉式了,但是饿汉式也有其缺点:当我们想获取Hungry中的其它静态变量时,在类加载的时候还是会对t2实例进行初始化，导致时间比较久
* 所以针对饿汉式的这个缺点,提出以下改进:
*
* 当执行getInstance()方法的时候就去调用StaticInnerHolder内部类里面的t2实例，此时StaticInnerHolder内部类才会被加载到内存里，在类加载的时候就对t2实例进行初始化。
* 和饿汉式一个道理，保证了只有一个实例，而且在调用getInstance()方法的时候才进行t2实例的初始化，又具有懒汉式的部分特性。”
* */

class StaticInner {
    //该类并不会随着StaticInner的加载而加载,只有在使用到StaticInnerHolder这个类时才会被加载到内存中;
    private static final class StaticInnerHolder {
        private static final T2 t2 = new T2();
    }

    private StaticInner() {
    }

    //
    private static T2 getInstance() {
        return StaticInnerHolder.t2;
    }
}


/*
* (5)黑客的破坏:反射-序列化/反序列化,而不是通过单例的方法来获取实例,这样我们写的单例的目的就被破坏了
* 如果只按照上面介绍的写法使用单利模式，很有可能被破坏
* 1)通过反射调用构造方法初始化新的变量
* 解决思路:对于通过反射调用构造方法的破坏方式我们可以通过在增加全局变量flag，在第一次初始化的时候就设置为true，第二次初始化的时候判断到flag为true就抛出异常。
* 但这种办法也只能避免破坏，无法彻底阻止，因为他们可以反射flag来修改flag的值。
*
* 2)序列化和反序列化产生新的实例
* 解决思路:可以增加readResolve()方法来预防。
* 这是反序列化机制决定的,在反序列化的时候会判断如果实现了serializable或者externalizable接口的类中又包含readResolve()方法的话,会直接调用readResolve()方法来获取实例。
* 于是如果黑客调用反序列化的方法的话,我们实现readResolve(),其内部依然返回我们的t2变量,那么此时即使是反序列化,得到的依然是我们之前创建的单例;
* */
class StaticInner2 implements Serializable {
    //该类并不会随着StaticInner的加载而加载,只有在使用到StaticInnerHolder这个类时才会被加载到内存中;
    private static final class StaticInnerHolder {
        private static final T2 t2 = new T2();
    }

    private StaticInner2() {
    }


    private static T2 getInstance() {
        return StaticInnerHolder.t2;
    }

    //防止反序列创建对象,这里返回的依然是我们创建好的单例
    // NOTE:静态变量在序列化时可以被序列化进去吗???
    private Object readResolve() {
        return StaticInnerHolder.t2;
    }
}



/*
* (6)
* 这种单例模式是利用枚举来实现的，在调用的时候直接ENUM.t2
* 枚举实际上就是一个继承Enum的类。所以本质还是一个类，因为枚举的特点，可以保证只会有一个实例，同时保证了线程安全、反射安全和反序列化安全。
* */
enum ENUM{
    t2;
}
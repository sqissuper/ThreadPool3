package thread0527;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClassName:ThreadDemo1
 * Package:thread0527
 * Description:
 *
 * @Author:HP
 * @date:2021/5/27 19:07
 */
public class ThreadDemo3 {
    private static AtomicReference money = new AtomicReference(100);

    public static void main(String[] args) throws InterruptedException {
        Thread m1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //转出100元
                boolean res = money.compareAndSet(100,0);
                System.out.println("第一次" + res);
            }
        });
        m1.start();
        m1.join();

        Thread m3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //转入100元
                boolean res = money.compareAndSet(0,100);
                System.out.println("第三次" + res);
            }
        });
        m3.start();
        m3.join();
        Thread m2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //转出100元
                boolean res = money.compareAndSet(100,0);
                System.out.println("第二次" + res);
            }
        });
        m2.start();


    }
}

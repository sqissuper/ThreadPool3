package thread0527;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:ThreadDemo1
 * Package:thread0527
 * Description:
 *
 * @Author:HP
 * @date:2021/5/27 19:07
 */
public class ThreadDemo2 {
    private static AtomicInteger count = new AtomicInteger(0);
    private static final int MAXSIZE = 100000;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAXSIZE; i++) {
                    count.getAndIncrement();
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < MAXSIZE; i++) {
                    count.getAndDecrement();
                }
            }
        });
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count);

//        AtomicInteger count = new AtomicInteger(0);
//        System.out.println(count.getAndIncrement());
//        System.out.println(count.incrementAndGet());
    }
}

package thread0527;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:ThreadDemo6
 * Package:thread0527
 * Description:
 *
 * @Author:HP
 * @date:2021/5/27 21:06
 */
public class ThreadDemo6 {
    private static Object lock = new Object();
    public static void main(String[] args) {
        //第一次进入锁
        synchronized (lock) {
            System.out.println("第一次进入锁");
            synchronized (lock) {
                System.out.println("第二次进入锁");
            }
        }
    }
}

package thread0527;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName:ThreadDemo1
 * Package:thread0527
 * Description:
 *
 * @Author:HP
 * @date:2021/5/27 19:07
 */
public class ThreadDemo5 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

        //读锁
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

        //写锁
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        //线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,new LinkedBlockingDeque<>(1000));
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //加锁
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " " + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //加锁
                readLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " " + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                writeLock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " " + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                writeLock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " " + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }
        });

    }
}

package threadpool_0525;

import java.util.Random;

/**
 * ClassName:MyBlockingQueue
 * Package:threadpool_0525
 * Description:
 *
 * @Author:HP
 * @date:2021/6/17 15:05
 */
public class MyBlockingQueue {
    private int[] val;
    private int fir;
    private int last;
    private int size;

    public MyBlockingQueue(int ini) {
        val = new int[ini];
        fir = 0;
        last = 0;
        size = 0;
    }

    //添加元素
    public void offer(int value) {
        synchronized (this) {
            if(size == val.length) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            val[last++] = value;
            this.size++;

            if(last == val.length) last = 0;
            this.notify();
        }
    }

    //弹出
    public int poll() {
        int res = -1;
        synchronized (this) {
            if(size == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res = val[fir++];
            this.size--;
            if(fir == val.length) fir = 0;
            this.notify();
        }
        return res;
    }

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(10);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int num = new Random().nextInt(10);
                    System.out.println("生产了随机数：" + num);
                    queue.offer(num);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    int res = queue.poll();
                    System.out.println(res);
                }
            }
        });
        t2.start();
    }
}

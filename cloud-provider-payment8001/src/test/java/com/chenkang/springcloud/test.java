package com.chenkang.springcloud;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.chenkang.springcloud.Controller.Singleton;
import com.chenkang.springcloud.mapper.PaymentDao;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;


/**
 * @author ChenKang
 * @date 2023/5/11 16:20
 */
@SpringBootTest
public class test {

    private final PaymentDao paymentDao;

    @Autowired
    public test(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Test
    @DisplayName("treeMap 使用测试")
    void test1() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("E", "24");
        treeMap.put("B", "21");
        treeMap.put("A", "20");
        treeMap.put("C", "22");
        treeMap.put("D", "23");
        treeMap.put("F", "25");
        treeMap.put("I", "28");
        treeMap.put("G", "26");
        treeMap.put("H", "27");
        System.out.println(treeMap.toString());
    }

    @Test
    void test2() {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1" + Thread.currentThread().isInterrupted());
                    Thread.sleep(5000);
                    System.out.println("2" + Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    System.out.println("3" + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("3.5" + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                    return;
                }
                System.out.println("4" + Thread.currentThread().isInterrupted());
            }
        });
        a.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.interrupt();
        System.out.println("5" + a.isInterrupted());
        try {
            Thread.sleep(6000);
            System.out.println("6" + a.isInterrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("线程阻塞中断")
    @Test
    void test3() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 两种方法均可以
                while (!Thread.interrupted()) {
                    // while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + ": 别管我，我忙着转账呢!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + ": 有内鬼，终止交易！");
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": 啊！险些误了大事");
            }
        });
        System.out.println(Thread.currentThread().getName() + ": 让李四开始转账。");
        thread.start();
        Thread.sleep(10 * 1000);
        System.out.println(Thread.currentThread().getName() + ": 老板来电话了，得赶紧通知李四对方是个骗子！");
        thread.interrupt();
    }


    @DisplayName("4种常用线程池")
    @Test
    void test4() {
        //固定线程大小线程池 队列无限  内存容易被挤爆
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //可缓存无线程大小上限线程池
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        //单线程线程池
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        //定时任务、周期执行的线程池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //线程池submit 可以接受 callable和runnable   callable返回值由future接受
        Future<Integer> submit = executorService.submit(new A(1, 2));
        //线程池execute只能接受runnable接口
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1 + 2);
            }
        });
    }

    @DisplayName("使用callable接口，futuretask包装类 创建一个可以有返回值可以抛异常的线程")
    @Test
    void test5() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new A(1, 2));

        Thread thread = new Thread(futureTask);
        thread.start();
        //get方法会等待执行结果（会阻塞逻辑） 或者会抛出异常
        System.out.println(futureTask.get());


    }

    /**
     * 实际开发中，需要注意产生异常时候捕获的信息处理，留存异常的数据 之后二次尝试，或者整个逻辑全部事务回滚。
     */
    @DisplayName("使用多线程批量处理将目标路径下的所有视频复制到存储路径然后将新的路径存储数据库")
    @Test
    void test6() throws ExecutionException, InterruptedException {
        //目标路径
        final String file_target_path = "E:" + File.separator + "study" + File.separator + "video";
        //存储路径
        final String file_save_path = "D:" + File.separator + "photo" + File.separator + "video";
        File file = new File(file_target_path);

        ExecutorService pool = Executors.newCachedThreadPool();

        String[] list = file.list();
        for (String name : list) {
            Future<?> submit = pool.submit(() -> {
                try {
                    BufferedInputStream in = FileUtil.getInputStream(file_target_path + File.separator + name);
                    BufferedOutputStream out = FileUtil.getOutputStream(file_save_path + File.separator + name);
                    long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
                    System.out.println("复制文件成功，文件大小:" + copySize + " 文件名:" + name);
                    if (name.equals("test - 副本 (2).mp4")) {
                        int a = 1 / 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
//            //获取执行结果，会阻塞
//            submit.get();
        }
        //判断是否所有子线程结束
        //方法一
        pool.shutdown();
        //30分钟内没有执行完毕 返回false 逻辑继续走   会阻塞
        System.out.println(pool.awaitTermination(30, TimeUnit.MINUTES));
        //方法二
//        pool.shutdown();
//        while (true) {
//            Thread.sleep(100);
//            if (pool.isTerminated()) {
//                System.out.println("线程池关闭。。");
//                break;
//            }
//        }
        //方法三 使用CountDownLatch计数器，在每个子线程中调用countDown()使计数器减一，使用await()阻塞主线程直到计数器为0 或者使用await(long timeout, TimeUnit unit)设置超时时间
        System.out.println("所有子线程都完成任务了！！");
    }


    @DisplayName("测试静态内部类懒汉式单例模式")
    @Test
    void test7(){
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1);
        System.out.println(instance2);
    }

    @DisplayName("条形码识别")
    @Test
    void test8() {
        while (true) {
            readCode(new File("C:\\Work\\Temp\\Photo\\0201_20231101151426574.jpg"));
        }
    }

    /**
     * @param readImage 读取一维码图片名
     * @return void
     */
    public static void readCode(File readImage) {
        try {
            BufferedImage read = ImageIO.read(readImage);
            if (null == read) {
                return;
            }
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(read);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "GBK");
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

            Result decode = new MultiFormatReader().decode(bitmap, hints);
            System.out.println("条形码的内容是：" + decode.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}

class A implements Callable<Integer> {

    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public A(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {

        return a + b;
    }
}

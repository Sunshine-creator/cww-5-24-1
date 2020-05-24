package cww524;

import sun.security.pkcs11.P11Util;

public class Sequence1 {
    public static void print1(){
        Thread t1 = new Thread(new print("A",null));        //同时执行，存在前后依赖关系
        Thread t2 = new Thread(new print("B",t1));
        Thread t3 = new Thread(new print("C",t2));
        t3.start();
        t2.start();
        t1.start();
    }
    public static class print implements Runnable{
        private String content;   //打印字母
        private Thread t;
//        public print(String content) {
//            this.content = content;
//        }
        public print(String content, Thread t) {
            this.content = content;
            this.t = t;
        }

        @Override
        public void run() {
//            System.out.println(content);
            try {
                if(t!=null)
                    t.join();
                    System.out.println(content);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
//
//        print1();
        print2();
    }
    public static void print2(){
//        Thread t1 = new Thread(new print2("A"));        //同时执行，存在前后依赖关系
//        Thread t2 = new Thread(new print2("B"));
//        Thread t3 = new Thread(new print2("C"));
//        t3.start();
//        t2.start();
//        t1.start();
        for (int i = 0; i <print2.ARRARY.length; i++) {
            new Thread(new print2(i)).start();
        }
    }
    public  static  class print2 implements Runnable{
//        private String content;
        private int idx;
        private  static String [] ARRARY ={"A","B","C"};
        private static int  INDEX;  //数组下标索引
//        public print2(String content) {
//            this.content = content;
//        }

        public print2(int idx) {
            this.idx = idx;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i <10 ; i++) {
                    synchronized (ARRARY) {
                        while (idx!=INDEX){
                            ARRARY.wait();
                        }
                        System.out.println(ARRARY[idx]);
                        if(INDEX==ARRARY.length-1)
                            System.out.println();
                        INDEX =( INDEX +1 ) % ARRARY.length;
                        ARRARY.notifyAll();

                }
            }

//            for (int i = 0; i <10 ; i++) {
//                synchronized (ARRARY) {
//                    while (!ARRARY[INDEX].equals(content)) {
//                        try {
//                            ARRARY[INDEX].wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println(content);
//                    if(INDEX==ARRARY.length-1){
//                        System.out.println();
//                    }
//                    INDEX = (INDEX +1) % ARRARY.length;
//                    ARRARY.notifyAll();
//                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

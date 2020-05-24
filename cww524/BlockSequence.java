package cww524;
public class BlockSequence<T> {        //阻塞队列，基于数组的循环队列实现
        private synchronized int  size(){    // 不用votalie不加锁的 方法进行调用，如果size put并发并行执行，
            // 可能导致put操作之前就返回一个未修改过的值
            return size;
        }
    public static void main(String[] args) {
        BlockSequence <Integer>queue = new BlockSequence(100);
        for (int i = 0; i <5 ; i++) {  //生产面包
            new Thread(()->{
                while (true) {
                    synchronized (queue) {
                        try {
                            queue.put(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("存放面包+1 :" + queue.size);
                    }
                }
//                System.out.println("存放面包+1");
            }).start();
        }
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                while (true) {
                    synchronized (queue) {
                        try {
                            Integer e = queue.take();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        System.out.println("消费面包-1:"+ queue.size);
                    }
                }
            }).start();
        }
    }
    private Object[] table;   //数组昌都
    private int takeIndex;
    private int putIndex;
    private int size;
    public BlockSequence(int capacity){
        table = new Object[capacity];
    }
    public  synchronized void put(T element) throws InterruptedException {      //泛型,生产者
        while (size==table.length)   //判断两者是否相等
            wait();
        table[putIndex]=element;
        putIndex = (putIndex + 1) % table.length;
        size++;
        notifyAll();
    }
    public synchronized T take() throws InterruptedException {
        while (size==0)
            wait();
        Object element = table[takeIndex];
        takeIndex =(takeIndex+1) % table.length;
        size--;
        notifyAll();
        return (T) element;
    }

}

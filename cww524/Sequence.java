package cww524;

public class Sequence {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            while (true){
                new Thread(()->{
                    synchronized (Sequence.class){
                        if(i==1&& i==2){
                            try {
                                Sequence.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("A");
                        }else if(i==0 && i==2){
                            try {
                                Sequence.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("B");
                        }else{
                            try {
                                Sequence.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("C");
                        }
                    }
                });
            }
        }
    }
}

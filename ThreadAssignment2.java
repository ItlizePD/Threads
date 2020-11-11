class PrintA implements Runnable{
    private ThreadAssignment2 threadAssignment2;
    public PrintA(ThreadAssignment2 threadAssignment2){
        this.threadAssignment2 = threadAssignment2;
    }

    public void run(){
        try{
            synchronized (threadAssignment2){
                for(int i=0;i<10;i++){
                    while(threadAssignment2.status!=1){
                        threadAssignment2.wait();
                    }
                    System.out.print('A');
                    threadAssignment2.status = 2;
                    threadAssignment2.notifyAll();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

class PrintB implements Runnable {
    private ThreadAssignment2 threadAssignment2;

    public PrintB(ThreadAssignment2 threadAssignment2) {
        this.threadAssignment2 = threadAssignment2;
    }

    public void run() {
        try {
            synchronized (threadAssignment2) {
                for (int i = 0; i < 10; i++) {
                    while (threadAssignment2.status != 2) {
                        threadAssignment2.wait();
                    }
                    System.out.print('B');
                    threadAssignment2.status = 3;
                    threadAssignment2.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintC implements Runnable{
    private ThreadAssignment2 threadAssignment2;
    public PrintC(ThreadAssignment2 threadAssignment2){
        this.threadAssignment2 = threadAssignment2;
    }

    public void run(){
        try{
            synchronized (threadAssignment2){
                for(int i=0;i<10;i++){

                    if(threadAssignment2.status!=3){
                        threadAssignment2.wait();
                    }
                    System.out.print('C');
                    threadAssignment2.status = 1;
                    threadAssignment2.notifyAll();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


public class ThreadAssignment2 {
    int status = 1;
    public static void main(String[] args){
        ThreadAssignment2 threadAssignment2 = new ThreadAssignment2();
        new Thread(new PrintA(threadAssignment2)).start();
        new Thread(new PrintB(threadAssignment2)).start();
        new Thread(new PrintC(threadAssignment2)).start();
    }
}

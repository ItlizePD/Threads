
class PrintNumber implements Runnable{
    private Object object;
    public PrintNumber(Object object){
        this.object = object;
    }

    public void run(){
        synchronized(object){
            for(int i=1;i<=52;i++){
                System.out.print(i);
                if(i%2==0){
                    object.notifyAll();
                    try{
                        object.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class PrintChar implements Runnable{
    private Object object;
    public PrintChar(Object object){
        this.object = object;
    }

    public void run(){
        synchronized (object){
            for(char i='A';i<='Z';i++){
                System.out.print(i);
                System.out.print(" ");
                object.notifyAll();
                if(i<'Z'){
                    try{
                        object.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class ThreadAssignment1 {

    public static void main(String[] args) {
        Object object = new Object();
        new Thread(new PrintNumber(object)).start();
        new Thread(new PrintChar(object)).start();
    }

}

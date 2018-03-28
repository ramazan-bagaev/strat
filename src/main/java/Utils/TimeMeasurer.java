package Utils;

public class TimeMeasurer {

    private long startTime;
    private String processName;

    public TimeMeasurer(){
        startTime = -1;
    }

    public void start(String name){
        startTime = System.currentTimeMillis();
        processName = name;
    }

    public void intermediateResult(String name){
        long current = System.currentTimeMillis();
        System.out.println(name + " delta = "  + (current - startTime));
        startTime = current;
    }

    public void stop(){
        if(startTime == -1){
            System.out.println("not started");
            return;
        }
        System.out.println(processName + " delta = " + (System.currentTimeMillis() - startTime));
        startTime = -1;
        processName = "";
    }

    public void stop(int a){
        if(startTime == -1){
            System.out.println("not started");
            return;
        }
        long delta = (System.currentTimeMillis() - startTime);
        if (delta < a) return;
        System.out.println(processName + " delta = " + delta);
        startTime = -1;
        processName = "";
    }

}

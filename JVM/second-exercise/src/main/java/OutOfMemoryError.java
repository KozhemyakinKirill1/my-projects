import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryError {
    public static void main(String[] args) throws InterruptedException {
        List<Object> list = new ArrayList();

        Thread.sleep(6000);

        while(true){
            list.add(new Object());
        }

        //34,254,488 (99.8%) Live Objects with -Xmx1024m
        //105,136,897 (99.9%) Live Objects with -Xmx2048m

    }
}

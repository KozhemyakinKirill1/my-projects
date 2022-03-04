import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorWithStrings {
    public static void main(String[] args) throws InterruptedException {

        final String string1 ="Hello EPAM!!!";

        List<Object> list = new ArrayList();

        Thread.sleep(6000);

        while(true) {
            list.add(new String(string1));
        }

//        long secs = System.currentTimeMillis();
//        while ((System.currentTimeMillis() - secs) <= 100 ){
//            list.add(new String(string1));
//        }
//        System.out.println("Count of strings : " + list.size());



        //810326 if use deduplication in 100 millis
        //732202 if not use in 100 millis
    }
}

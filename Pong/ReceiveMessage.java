import java.io.IOException;
import java.io.InputStream;

class ReceiveMessage implements Runnable{

    private byte mesin[];
    private InputStream in;

    public ReceiveMessage(byte[] bytes,InputStream inputStream){
        mesin = bytes;
        in = inputStream;
    }

    @Override
    public void run() {

        try {
            while(true){
                int n = in.read(mesin);
                String str = new String(mesin,0,n);
                System.out.println(str);
                if (str.equals("end")){
                    System.out.println("對方已離線");
                    System.exit(0);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
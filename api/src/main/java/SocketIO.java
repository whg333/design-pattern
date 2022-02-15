import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIO {

    public static void main(String[] args) throws Exception {
        int port = 9090;
        ServerSocket server = new ServerSocket(port,20);
        System.out.println("server listen on "+port);

        while (true) {
            // System.in.read();
            Socket client = server.accept();  //阻塞1
            System.out.println(client+" accept");

            new Thread(() -> {
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while(true){
                        // System.in.read();
                        String line = reader.readLine(); //阻塞2
                        if(null == line){
                            client.close();
                            break;
                        }else{
                            System.out.println(client+":"+line);
                            if("quit".equals(line)){
                                client.close();
                                break;
                            }
                        }
                    }
                    System.out.println(client+" close");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

}

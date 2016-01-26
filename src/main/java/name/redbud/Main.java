package name.redbud;


import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Robin Yang
 * Created At: 2016-01-15 14:18
 */
public class Main {

    public static void main(String[] args) throws IOException{
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("kumofs");
        src.add("viver");

        MessagePack msgpack = new MessagePack();

        byte[] raw = msgpack.write(src);

        List<String> dest = msgpack.read(raw, Templates.tList(Templates.TString));

        System.out.println(dest);
    }
}

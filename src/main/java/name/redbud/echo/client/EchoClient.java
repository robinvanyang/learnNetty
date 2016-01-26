package name.redbud.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.msgpack.MsgpackDecoder;
import io.netty.handler.codec.msgpack.MsgpackEncoder;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String    host;
    private final int       port;
    private final int       sendNumber;


    public EchoClient(String host, int port, int number) {
        this.host = host;
        this.port = port;
        this.sendNumber = number;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel.class)            //3
                    .remoteAddress(new InetSocketAddress(host, port))    //4
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                            ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());

                            ch.pipeline().addLast(new EchoClientHandler(sendNumber));
                        }
                    });

            ChannelFuture f = b.connect().sync();        //6

            f.channel().closeFuture().sync();            //7
        } finally {
            group.shutdownGracefully().sync();            //8
        }
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = 8080;

        final int sendNumber = 1000;

        new EchoClient(host, port, sendNumber).start();
    }
}

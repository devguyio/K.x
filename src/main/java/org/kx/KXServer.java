package org.kx;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class KXServer {
    private static final Logger logger = LoggerFactory.getLogger(KXServer.class);
    private int port;

    public KXServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new KXServer(9999).start();
    }

    public void start() throws InterruptedException {
        logger.atInfo().setMessage("Starting K.x server")
                .addKeyValue("port", port).log();
        final var handler = new KXClientHandler();
        EventLoopGroup serverGroup = new NioEventLoopGroup(1);
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(serverGroup, clientGroup )
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            logger.atDebug().setMessage("New connection")
                                            .addKeyValue("source", ch.remoteAddress())
                                                    .log();
                            ch.pipeline().addLast(handler);
                        }
                    });
            b.bind().sync().channel().closeFuture().sync();
        } finally {
            serverGroup.shutdownGracefully();
            clientGroup.shutdownGracefully();
        }
    }
}

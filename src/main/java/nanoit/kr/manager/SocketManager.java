package nanoit.kr.manager;

import lombok.extern.slf4j.Slf4j;
import nanoit.kr.config.GlobalConstant;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Slf4j
public class SocketManager {
    private final LinkedBlockingQueue<Socket> socketPool;
    private final ScheduledExecutorService scheduledExecutorService;

    public SocketManager() {
        socketPool = new LinkedBlockingQueue<>(GlobalConstant.MAX_POOL_SIZE);
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    }

    private void registrySocket() throws IOException {
        try {
            for (int i = 0; i < GlobalConstant.MAX_POOL_SIZE; i++) {
                Socket socket = createNewSocket();
                socketPool.offer(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Socket acquireSocket() throws IOException {
        Socket socket = null;
        try {
            while (socket == null) {
                socket = socketPool.poll();
                if (socket != null && !isSocketConnected(socket)) {
                    closeSocket(socket);
                    socket = null;
                }
                if (socket == null && socketPool.size() < GlobalConstant.MAX_POOL_SIZE) {
                    socket = createNewSocket();
                }
                if (socket == null) {
                    socket = socketPool.take();
                }
            }
            return socket;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("[SOCKET-MANAGER ] Failed to acquire socket from the pool", e);
        }
        return null;
    }


    public void releaseSocket(Socket socket) {
        socketPool.offer(socket);
    }

    private Socket createNewSocket() throws IOException {
        try {
            return new Socket(GlobalConstant.SERVER_HOST, GlobalConstant.SERVER_PORT);
        } catch (IOException e) {
            throw new IOException("Failed to create a new socket", e);
        }
    }

    private boolean isSocketConnected(Socket socket) {
        return socket.isConnected() && !socket.isClosed() && socket.isBound() && !socket.isInputShutdown()
                && !socket.isOutputShutdown();
    }

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            // Handle or log the exception as needed
        }
    }

    public boolean isSocketPoolReady() {
        return socketPool.size() == GlobalConstant.MAX_POOL_SIZE;
    }
}

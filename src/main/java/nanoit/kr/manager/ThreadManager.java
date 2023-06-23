package nanoit.kr.manager;

import nanoit.kr.cli.PropertyDto;
import nanoit.kr.cli.PropertyReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadManager {
    private ScheduledExecutorService scheduledExecutorService;
    private final Map<String, PropertyDto> propertyDtoMap;
    private final ConcurrentHashMap<String, Thread> threadMap;

    /*
    1. 하나의 계정당 한개의 UserThread가 생성
    2. 1개의 UserThread는 2개의 Sub Thread를 가짐
    3. SendThread, Receive Thread
    4. UserThread가 죽었을 경우 핸들링 필요
    5. Sub Thread 가 죽었을 경우는?
    **/

    public ThreadManager() {
        this.threadMap = new ConcurrentHashMap<>();
        this.propertyDtoMap = PropertyReader.getPropertyDtoMap();
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
    }

    public void start() {

    }

    public boolean isThreadManagerReady() {
        return threadMap.size() == 4;
    }
}

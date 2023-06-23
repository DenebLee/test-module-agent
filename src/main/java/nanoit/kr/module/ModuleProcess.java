package nanoit.kr.module;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ModuleProcess {

    private final Map<String, Module> moduleMap;
    private final Map<String, Thread> threadMap;
    private final ScheduledExecutorService scheduledExecutorService;
    private static final long DEAD_LINE = 3 * 60 * 1000L;

    public ModuleProcess() {
        this.moduleMap = new ConcurrentHashMap<>();
        this.threadMap = new ConcurrentHashMap<>();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
        this.scheduledExecutorService.scheduleAtFixedRate(this::monitor, 1000, 1000, TimeUnit.MICROSECONDS);
    }

    private void monitor() {
        for (Map.Entry<String, Module> entry : moduleMap.entrySet()) {
            if (!threadMap.containsKey(entry.getKey()) && entry.getValue().getStatus() == Status.INIT) {
            }
        }
    }
}

package nanoit.kr.manager;

import nanoit.kr.UserResource;
import nanoit.kr.cli.PropertyDto;
import nanoit.kr.cli.PropertyReader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SessionManager {
    public ConcurrentHashMap<String, UserResource> userResourceMap;
    private final ScheduledExecutorService scheduledExecutorService;

    public SessionManager() {
        this.userResourceMap = new ConcurrentHashMap<>();
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(2);
        registrySession();
    }

    private void registrySession() {
        try {
            for (Map.Entry<String, PropertyDto> entry : PropertyReader.getPropertyDtoMap().entrySet()) {
                String UUID = java.util.UUID.randomUUID().toString();
                UserResource resource = new UserResource(UUID, entry.getValue());
                userResourceMap.put(UUID, resource);
                // UUID 는 해당 계정에 생성된 모든 전송 , 수신 스레드와 동일하게 작성하며 메시지에 UUID값을 넣어
                // 뺄 수 있도록 함
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(resourceManagement, 0, 5, TimeUnit.SECONDS);
    }

    private final Runnable resourceManagement = () -> {
        try {
            for (Map.Entry<String, UserResource> entry : userResourceMap.entrySet()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public boolean isSessionManagerReady() {
        if (PropertyReader.getPropertyDtoMap().size() != userResourceMap.size()) {
            // PropertyReader 의 Map 과 UserResource 맵의 크기는 같아야 한다 . 1:1 이기에
            return false;
        }

        // UserResource 두개의 스레드 준비 상태 체크 로직 추후 추가
        return true;
    }
}

package nanoit.kr;

import lombok.extern.slf4j.Slf4j;
import nanoit.kr.cli.PropertyDto;

@Slf4j
public class UserResource {

    private final PropertyDto dto;
    private final String uuid;
    private final SendThread sendThread;
    private final ReceiveThread receiveThread;
    private final Thread sendT;
    private final Thread receiveT;

    public UserResource(String uuid, PropertyDto dto) {
        this.dto = dto;
        this.uuid = uuid;
        this.sendThread = new SendThread(uuid);
        this.receiveThread = new ReceiveThread(uuid);

        this.sendT = new Thread(sendThread);
        this.receiveT = new Thread(receiveThread);
        this.sendT.setName("SEND-THREAD{}");
        this.receiveT.setName("RECEIVE-THREAD : {}");
    }

    public void start() {
        try {
            // HTTP 통신을 통해 인증 서버 인증이 성공 되며 전송 서버에 연결이 된다면
            this.sendT.start();
            this.receiveT.start();

        } catch (Exception e) {
            log.error("[SESSION-RESOURCE] Critical Error detected ", e);
        }
    }

    public void writeThreadCleaner(String calledClassName) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package nanoit.kr.cli;


import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString

public class PropertyDto {

    /*Service 설정 값*/
    private String KEY;                                 // UUID
    private String URL;                                 // SERVER URL
    private String AGENT_ID;                            // DBMS ID OR SERVER ID
    private String AGENT_PWD;                           // DBMS PWD OR SERVER PWD
    private String AGENT_ENCRYPT_KEY;                   // 암호화 KEY

    /*DB 설정 값 */
    private String DBMS;                                // ORACLE, MYSQL, MSSQL
    private String DB_IP;                               // DBMS IP OR SERVER IP
    private String DB_PORT;                             // DBMS PORT OR SERVER PORT
    private String DB_ID;                               // DBMS ID
    private String DB_PWD;                              // DBMS PASSWORD
    private String DB_NAME;                             // ORACLE SERVICE NAME or LISTENER NAME, MSSQL MYSQL DB NAME
    private String DB_MEMBER_ID;                        // 일단 미사용
    private String MSG_TABLE_NAME;                      // 발송 테이블명
    private String LOG_TABLE_NAME;                      // 로그 테이블명
    private String QUERY_SET;                           // MYBATIS 쿼리 셋 이름

    private String IS_USING_LOG_TABLE;                  // MONTH or FIXED or NO
    private String DATA_BASE_MEMBER_IDENTIFY;           // delivery에서 사용하는 DB정보 ID
    private String MAX_TRANSACTION_PER_SECONDS;         // 초당 발송 속도
    private String LIMIT_SEND_PER_SECONDS;              // 초당 최대 발송 속도
    private String MULTI_MEDIA_MAX_SEND_PER_SECONDS;    // L/MMS 초당 발송속도
    private String KAKAO_MAX_SEND_PER_SECONDS;          // 친구톡 초당 발송속도
    private String ALIVE_SEND_INTERVAL;                 // Alive 패킷 주기
    private String IMAGE_FOLDER_PATH;                   // 이미지파일 디렉토리
    private int MAX_FILE_SIZE;                          // 파일최대 크기
    private int SEND_BUFFER_SIZE;                       // write buffer size
    private int RECEIVE_BUFFER_SIZE;                    // receive buffer size
    private int SAVE_QUEUE_SIZE;                        // permanence queue size
    private String SEND_VALIDATION_TIME;                // 메시지 발송 유효시간
    private int USER_INDEX_MSG;                         // 발송테이블에 등록할 index 개수
    private int USER_INDEX_LOG;                         // 로그테이블에 등록할 index 개수
    private int SEND_QUEUE_SIZE;                        // SELECT 후 전송 요청시 대기시킬 큐 사이즈
    private long MYSQL_AUTO_INCREMENT_SLEEP;            // auto increment sleep time (ms)
    private int TIMEOUT_INTERVAL;                       // timeout interval (second)
    private String IDENTIFICATION_CODE;

    public PropertyDto(String pdKey) {
        setKEY(pdKey);
        initialize();
    }

    public PropertyDto() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void initialize() {
        MULTI_MEDIA_MAX_SEND_PER_SECONDS = "0";
        MAX_TRANSACTION_PER_SECONDS = "0";
        LIMIT_SEND_PER_SECONDS = "0";
        SEND_VALIDATION_TIME = "86400";
        TIMEOUT_INTERVAL = 60;
        SEND_BUFFER_SIZE = 8192;
        RECEIVE_BUFFER_SIZE = 8192;
        SAVE_QUEUE_SIZE = 20000;
        SEND_QUEUE_SIZE = 10;
        MAX_FILE_SIZE = 500000;
        USER_INDEX_MSG = 0;
        USER_INDEX_LOG = 0;
        MYSQL_AUTO_INCREMENT_SLEEP = 3600000;
        IS_USING_LOG_TABLE = "MONTH";
        ALIVE_SEND_INTERVAL = "29";
        IDENTIFICATION_CODE = "NONE";
    }
}

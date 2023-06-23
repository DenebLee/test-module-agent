package nanoit.kr.cli;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Root")
@Accessors(chain = true)
public class XmlDto {

    /*Service 설정 값*/
    @XmlElement(name = "URL")
    private String url;
    @XmlElement(name = "AGENT_ID")
    private String agentId;
    @XmlElement(name = "AGENT_PASSWORD")
    private String agentPwd;
    @XmlElement(name = "AGENT_ENCRYPT_KEY")
    private String agentEncryptKey;

    /*DB 설정 값 */
    @XmlElement(name = "DBMS")K
    private String dbms;
    @XmlElement(name = "DB_IP")
    private String dbIp;
    @XmlElement(name = "DB_PORT")
    private String dbPort;
    @XmlElement(name = "DB_ID")
    private String dbId;
    @XmlElement(name = "DB_PASSWORD")
    private String dbPwd;
    @XmlElement(name = "DB_NAME")
    private String dbName;

    /* 서비스 사용 옵션 */
    @XmlElement(name = "SMS")
    private boolean smsEnabled;
    @XmlElement(name = "MMS")
    private boolean mmsEnabled;
    @XmlElement(name = "KMS")
    private boolean kmsEnabled;

    /*사용자 설정 옵션*/
    @XmlElement(name = "ENCRYPTION")
    private String encryption;
    @XmlElement(name = "MSG_TABLE_NAME")
    private String msgTableName;
    @XmlElement(name = "LOG_TABLE_NAME")
    private String logTableName;
}
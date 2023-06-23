package nanoit.kr.cli;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nanoit.kr.config.GlobalConstant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.*;


@Slf4j
public class PropertyReader {
    @Getter
    private static final Map<String, PropertyDto> propertyDtoMap = new HashMap<>();
    private final File[] configureFiles;
    private InitialSetting setting;

    private List<Document> xmlList;

    public PropertyReader() {
        this.configureFiles = new File(GlobalConstant.CONFIG_FILE_PATH).listFiles();
        this.setting = new InitialSetting();
        this.xmlList = new ArrayList<>();
    }

    private void readUserXmlFile() {
        try {
            for (File file : configureFiles) {
                if (file.getName().contains(GlobalConstant.FILE_FORMAT) && file.getName().contains("_USER")) {
                    xmlList.add(setting.parseXml(file));
                }
                // XML 파일이 아닌 파일이 있을 경우 가져 오지 않으므로 핸들링 불필요
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PropertyDto getPropertyDto(String key) {
        return propertyDtoMap.get(key);
    }

    public void initializeSettingEnv() {
        try {
            readUserXmlFile();

            // config 폴더 내 사용자 설정 xml 파일에 대한 에러 핸들링 필요
            // 1. XML 파일이며 MS 가 포함 되어 있는 다른 파일일 경우
            // 2. 파일을 가져와 XML 파싱 하는 도중 에러가 발생 한다면?
            // 3. 만약 config 폴더 내에 파일이 한 개도 없다면?

            setPropertyDtoMap();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validationPropertyDto(PropertyDto dto) {

        return false;
    }

    private void setPropertyDtoMap() {

        try {
            for (Document xml : xmlList) {
                Element root = xml.getDocumentElement();
                NodeList xmlChildList = root.getChildNodes();
                PropertyDto dto = new PropertyDto();
                for (int listIndex = 0; listIndex < xmlChildList.getLength(); listIndex++) {

                    // 각 Property에 고유한 UUID 값  삽입
                    String UUID = java.util.UUID.randomUUID().toString();
                    switch (xmlChildList.item(listIndex).getNodeName()) {

                        /*사용자 정보*/
                        case "URL":
                            dto.setURL(getValue(xmlChildList, listIndex));
                            break;
                        case "AGENT_ID":
                            dto.setAGENT_ID(getValue(xmlChildList, listIndex));
                            break;
                        case "AGENT_PASSWORD":
                            dto.setAGENT_PWD(getValue(xmlChildList, listIndex));
                            break;
                        case "AGENT_ENCRYPTKEY":
                            dto.setAGENT_ENCRYPT_KEY(getValue(xmlChildList, listIndex));
                            break;

                        /*사용자 DB 정보*/
                        case "DBMS":
                            dto.setDBMS(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_IP":
                            dto.setDB_IP(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_PORT":
                            dto.setDB_PORT(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_ID":
                            dto.setDB_ID(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_PASSWORD":
                            dto.setDB_PWD(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_NAME":
                            dto.setDB_NAME(getValue(xmlChildList, listIndex));
                            break;
                        case "QUERYSET":
                            dto.setQUERY_SET(getValue(xmlChildList, listIndex));
                            break;
                        case "MSG_TABLE_NAME":
                            dto.setMSG_TABLE_NAME(getValue(xmlChildList, listIndex));
                            break;
                        case "LOG_TABLE_NAME":
                            dto.setLOG_TABLE_NAME(getValue(xmlChildList, listIndex));
                            break;
                        case "DB_MEMBER_ID":
                            dto.setDB_MEMBER_ID(getValue(xmlChildList, listIndex));
                            break;
                        case "USER_INDEX_MSG":
                            dto.setUSER_INDEX_MSG(Integer.parseInt(getValue(xmlChildList, listIndex)));
                            break;
                        case "USER_INDEX_LOG":
                            dto.setUSER_INDEX_LOG(Integer.parseInt(getValue(xmlChildList, listIndex)));
                            break;
                        case "SEND_QUEUE_SIZE":
                            dto.setSEND_QUEUE_SIZE(Integer.parseInt(getValue(xmlChildList, listIndex)));
                            break;
                        case "IMAGE_FOLDER_PATH":
                            break;
                    }
                    dto.setKEY(UUID);
                }
                propertyDtoMap.put(dto.getAGENT_ID(), dto);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("[INITIALIZE] 형식에 맞지 않은 사용자 설정 파일 존재", e);
        }
    }

    private static String getValue(NodeList xmlChildList, int listIndex) {
        return xmlChildList.item(listIndex).getTextContent();
    }


}

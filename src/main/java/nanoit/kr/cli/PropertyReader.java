package nanoit.kr.cli;

import nanoit.kr.config.GlobalConstant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyReader {
    private static final Map<String, PropertyDto> propertyDtoMap = new HashMap<>();
    private final File[] configureFiles;
    private InitialSetting setting;

    public PropertyReader() {
        this.configureFiles = new File(GlobalConstant.CONFIG_FILE_PATH).listFiles();
        this.setting = new InitialSetting();
    }

    public void readUserXmlFile() throws ParserConfigurationException, IOException, SAXException {
        for (File file : configureFiles) {
            if (file.getName().contains(GlobalConstant.FILE_FORMAT)) {
                Document xml = setting.parseXml(file);
            }
        }
    }

    private void setPropertyDtoMap(Document xml) {
        Element root = xml.getDocumentElement();
        NodeList xmlChildList = root.getChildNodes();
        PropertyDto dto = new PropertyDto();

        for (int listIndex = 0; listIndex < xmlChildList.getLength(); listIndex++) {
            switch (xmlChildList.item(listIndex).getNodeName()) {

                /*사용자 정보*/
                case "KEY":
                    dto.setKEY(getValue(xmlChildList, listIndex));
                    break;
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
        }
    }

    private String getValue(NodeList xmlChildList, int listIndex) {
        return xmlChildList.item(listIndex).getTextContent();
    }
}

package nanoit.kr.cli;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class XmlDtoStore {
    @Getter
    private final Map<Integer, XmlDto> xmlConfigList;

    public XmlDtoStore() {
        this.xmlConfigList = new HashMap<>();
    }
    // 만약에 이중화 계정 설정 옵션으로 인해 생성된 XML이 여러개 일 경우?

    public void cleanXmlConfigList() {
        xmlConfigList.clear();
    }

    public XmlDto getXmlDto(int index){
        return xmlConfigList.get(index);
    }


}

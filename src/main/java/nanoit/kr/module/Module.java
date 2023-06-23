package nanoit.kr.module;

import nanoit.kr.cli.PropertyDto;

public abstract class Module {

    protected final String uuid;
    protected final PropertyDto dto;
    protected final Status status;

    abstract public void shutDown();

    abstract public void sleep();

    abstract public Status getStatus();

    abstract public String getUuid();


    protected Module(String uuid, PropertyDto dto, Status status) {
        this.uuid = uuid;
        this.dto = dto;
        this.status = status;
    }
}

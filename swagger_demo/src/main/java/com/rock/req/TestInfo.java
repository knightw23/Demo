package com.rock.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TestInfo {

    @ApiModelProperty(value = "自增主键")
    private long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

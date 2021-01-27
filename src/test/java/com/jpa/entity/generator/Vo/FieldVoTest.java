package com.jpa.entity.generator.Vo;

import com.mysql.cj.MysqlType;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldVoTest {

    @Test
    public void getFieldString(){
        FieldVo fieldVo = new FieldVo("fieldName", "fieldNameAlias"
                , true, true
                , "comment", MysqlType.INT.getName());
        assertEquals(fieldVo.getDataType(), "int");
    }

}

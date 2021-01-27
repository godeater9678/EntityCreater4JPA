package com.jpa.entity.generator.Vo;

import lombok.Data;

import java.util.HashMap;

@Data
public class FieldVo {
    String fieldName;
    String fieldNameAlias;
    Boolean isId;
    Boolean isGeneratedValue;
    String comment;
    String type;
    String dataType;
    static HashMap<String, String> types;

    public FieldVo(String fieldName, String fieldNameAlias
            , Boolean isId, Boolean isGeneratedValue
            , String comment, String type
    ) {
        setFieldName(fieldName);
        setFieldNameAlias(fieldNameAlias);
        setIsId(isId);
        setIsGeneratedValue(isGeneratedValue);
        setComment(comment);
        setType(type);

        if(types == null){
            types = new HashMap<>();
            types.put("CHAR"			,"String");
            types.put("VARCHAR"			,"String");
            types.put("LONGTEXT"		,"String");
            types.put("NUMERIC"         ,"java.math.BigDecimal");
            types.put("DECIMAL"         ,"java.math.BigDecimal");
            types.put("BIT"             ,"boolean");
            types.put("TINYINT"         ,"byte");
            types.put("SMALLINT"        ,"short");
            types.put("INT"             ,"int");
            types.put("BIGINT"          ,"long");
            types.put("REAL"            ,"float");
            types.put("FLOAT"           ,"double");
            types.put("DOUBLE"          ,"double");
            types.put("BINARY"          ,"byte[]");
            types.put("VARBINARY"       ,"byte[]");
            types.put("LONGBLOB"        ,"byte[]");
            types.put("DATE"            ,"java.sql.Date");
            types.put("DATETIME"        ,"java.sql.Date");
            types.put("TIME"            ,"java.sql.Time");
            types.put("TIMESTAMP"       ,"java.sql.Timestamp");
        }
        dataType = types.get(type);
        if(dataType ==  null){
            dataType = "String";
        }
    }

    public String toString(){
        StringBuilder sbProperty = new StringBuilder();
        if(isId){
            sbProperty.append("\t@Id\n");
        }
        if(isGeneratedValue){
            sbProperty.append("\t@GeneratedValue(strategy = GenerationType.AUTO)\n");
        }
        sbProperty.append(String.format("\t@Column(name=\"%s\")\n", fieldName));
        sbProperty.append(String.format("\tprivate %s %s;\n\n", dataType, fieldNameAlias));

        return sbProperty.toString();
    }



}

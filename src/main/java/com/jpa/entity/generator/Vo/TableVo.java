package com.jpa.entity.generator.Vo;

import lombok.Data;

import java.util.List;

@Data
public class TableVo {
    String tableName;
    String tableNameAlias;
    String tableSchema;
    List<FieldVo> columns;

    public TableVo(String tableName, String tableNameAlias, String tableSchema, List<FieldVo> columns){
        setTableName(tableName);
        setTableSchema(tableSchema);
        setTableNameAlias(tableNameAlias);
        setColumns(columns);
    }

}

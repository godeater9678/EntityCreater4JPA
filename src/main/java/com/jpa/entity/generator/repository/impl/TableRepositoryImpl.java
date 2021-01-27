package com.jpa.entity.generator.repository.impl;

import com.jpa.entity.generator.Vo.FieldVo;
import com.jpa.entity.generator.Vo.TableVo;
import com.jpa.entity.generator.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableRepositoryImpl implements TableRepository {


    @Value("${spring.gen.target-schema}")
    String targetSchema;

    protected DataSource dataSource;

    @Autowired
    TableRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<TableVo> getTables() {
        try {
            List<TableVo> returnTables = new ArrayList<TableVo>();
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            ResultSet tables = metaData.getTables(targetSchema, null, null, new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                String tableSchema = tables.getString(1);
                ResultSet pks = metaData.getPrimaryKeys(null, null, tableName);
                List<String> primaryKeys = new ArrayList<>();
                while(pks.next()) {
                    primaryKeys.add(pks.getString("COLUMN_NAME"));
                }
                pks.close();
                ResultSet columns = metaData.getColumns(null, null, tableName, "%");
                List<FieldVo> fieldVoList = new ArrayList<>();
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    Boolean isId = primaryKeys.contains(columnName);
                    String type = columns.getString("TYPE_NAME");
                    String comment = columns.getString("REMARKS");
                    Boolean isAi = columns.getString("IS_AUTOINCREMENT").equals("YES");
                    //Boolean isGen = columns.getString(23).equals("YES");
                    fieldVoList.add(new FieldVo(columnName, getCamelName(columnName), isId, isAi, comment, type));
                }
                returnTables.add(new TableVo(tableName, getCamelName(tableName), tableSchema, fieldVoList));
                columns.close();
            }
            tables.close();
            return returnTables;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String getCamelName(String name){
        StringBuilder sbCamelName = new StringBuilder();
        boolean metUnderbar = false;
        for(char c : name.toCharArray()){
            if(c == '_'){
                metUnderbar = true;
            }else if(metUnderbar){
                sbCamelName.append( Character.toString(c).toUpperCase() );
                metUnderbar = false;
            }else{
                sbCamelName.append( Character.toString(c).toLowerCase() );
            }
        }

        return sbCamelName.toString();
    }


}

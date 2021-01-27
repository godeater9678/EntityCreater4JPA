package com.jpa.entity.generator.service.impl;

import com.jpa.entity.generator.Vo.TableVo;
import com.jpa.entity.generator.repository.TableRepository;
import com.jpa.entity.generator.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class EntityServiceImpl implements EntityService {
    TableRepository tableRepository;
    @Value("${spring.gen.location}")
    String entityLocation;

    ClassPathResource templateResource;

    enum entityPropertis {
        TABLE_NAME("tableName"),
        TABLE_SCHEMA("tableSchema"),
        CLASS_NAME("className"),
        CLASS_MEMBERS("members");
        private String property;
        public String val(){return property;}
        entityPropertis(String property) {
            this.property = property;
        }
    }

    @Autowired
    EntityServiceImpl(@Qualifier("tableRepositoryImpl") TableRepository tablesRepository){
        this.tableRepository = tablesRepository;
        this.templateResource = new ClassPathResource("entityTemplate.java.txt");
    }


    @Override
    public void genEntityClasses() {
        getTables().forEach(this::makeEntityFile);
    }

    @Override
    public List<TableVo> getTables() {
        return tableRepository.getTables();
    }

    private void makeEntityFile(TableVo tableVo){
        try{
            Reader reader = new InputStreamReader(templateResource.getInputStream(), UTF_8);
            String strRead = FileCopyUtils.copyToString(reader);
            String className = tableVo.getTableNameAlias().substring(0,1).toUpperCase()+tableVo.getTableNameAlias().substring(1);
            strRead = setValue(strRead, entityPropertis.CLASS_NAME.val(), className);
            strRead = setValue(strRead, entityPropertis.TABLE_SCHEMA.val(), tableVo.getTableSchema());
            strRead = setValue(strRead, entityPropertis.TABLE_NAME.val(), tableVo.getTableName());
            StringBuilder sbMembers = new StringBuilder();
            tableVo.getColumns().forEach((fieldVo)->{
                sbMembers.append(fieldVo.toString());
            });
            strRead = setValue(strRead, entityPropertis.CLASS_MEMBERS.val(), sbMembers.toString());
            writeFile(strRead, className);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private String setValue(String strEntity, String key, String value){
        String strKey = String.format(":{{%s}}", key);
        return strEntity.replace(strKey, value);
    }

    private void writeFile(String strEntity, String className){
        try {
            String fullPath = String.format("%s/%s.java", entityLocation, className);
            File file = new File(fullPath);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()), "UTF8"));
            writer.write(strEntity);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

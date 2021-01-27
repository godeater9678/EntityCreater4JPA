package com.jpa.entity.generator.service;

import com.jpa.entity.generator.Vo.TableVo;

import java.util.List;

public interface EntityService {
    void genEntityClasses();

    List<TableVo> getTables();
}

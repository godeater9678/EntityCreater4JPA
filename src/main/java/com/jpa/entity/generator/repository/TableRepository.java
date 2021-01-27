package com.jpa.entity.generator.repository;

import com.jpa.entity.generator.Vo.TableVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository{
    List<TableVo> getTables();
}

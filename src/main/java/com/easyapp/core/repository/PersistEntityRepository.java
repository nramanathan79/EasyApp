package com.easyapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyapp.core.entity.PersistEntity;

@Repository
public interface PersistEntityRepository<T extends PersistEntity> extends JpaRepository<T, String> {
}

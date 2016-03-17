package com.easyapp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyapp.core.model.PersistModel;

@Repository
public interface PersistModelRepository<T extends PersistModel> extends JpaRepository<T, String> {
}

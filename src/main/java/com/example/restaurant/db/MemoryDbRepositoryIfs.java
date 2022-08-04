package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index); // 검색
    T save(T entity); // 저장
    void deleteById(int index); // 삭제
    List<T> findAll(); // 전체 엔티티 출력

}
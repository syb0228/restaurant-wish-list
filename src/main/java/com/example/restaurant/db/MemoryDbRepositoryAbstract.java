package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    // ArrayList 형태로 DB 생성
    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {

        // DB에 해당 index의 엔티티가 존재하면 반환
        return db.stream().filter(it -> it.getIndex() == index).findFirst();

    }

    @Override
    public T save(T entity) {

        // 먼저 이미 존재하는 엔티티인지 DB에서 찾아봄
        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        // db에 엔티티가 없는 경우 - 엔티티 추가
        if(optionalEntity.isEmpty()){
            index++; // 인덱스 증가
            entity.setIndex(index); // 새로운 엔티티에 인덱스를 세팅해줌

            db.add(entity); // db에 새로운 엔티티 추가

            return entity; // 추가한 엔티티 반환
        }
        // db에 이미 엔티티가 있는 경우 - 엔티티 업데이트
        else{
            var preIndex = optionalEntity.get().getIndex(); // 기존 인덱스를 찾음
            entity.setIndex(preIndex); // 변경 엔티티에 기존 인덱스를 세팅해줌

            deleteById(preIndex); // 이전 엔티티 삭제
            db.add(entity); // db에 변경 엔티티 추가

            return entity;
        }

    }

    @Override
    public void deleteById(int index) {

        // 삭제하고자 하는 인덱스의 엔티티를 DB에서 찾아봄
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();

        if(optionalEntity.isPresent()){ // 해당 엔티티가 존재하면
            db.remove(optionalEntity.get()); // db에서 엔티티 삭제
        }

    }

    @Override
    public List<T> findAll() {
        return db; // db의 모든 엔티티 리턴
    }
}
package com.example.restaurant.wishlist.repository;

import com.example.restaurant.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    // 반복적으로 사용될 엔티티 생성 과정을 메소드로 만듦
    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("roadAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest(){

        // 새로운 엔티티 생성한 뒤 저장소에 저장
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        // 저장소에 엔티티가 제대로 저장 됐는지 확인(Null이 아니여야 함)
        Assertions.assertNotNull(expected);
        // 맨 처음 저장하는 엔티티이므로 index가 1인지 확인
        Assertions.assertEquals(1, expected.getIndex());

    }

    @Test
    public void updateTest(){

        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        // 생성한 엔티티의 title 정보를 변경하고 다시 저장소에 저장
        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        // 변경한 내용이 제대로 들어갔는지 확인
        Assertions.assertEquals("update test", saveEntity.getTitle());
        // 이경우 생성이 아닌 업데이트이기 때문에 저장소의 총 엔티티가 1개인지 확인
        Assertions.assertEquals(1, wishListRepository.findAll().size());

    }

    @Test
    public void findByIdTest(){

        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        // 저장소에서 index가 1인 엔티티 검색
        var expected = wishListRepository.findById(1);

        // 검색 결과 엔티티가 반환 됐는지 확인
        Assertions.assertEquals(true, expected.isPresent());
        // 검색한 엔티티의 index가 1이 맞는지 확인
        Assertions.assertEquals(1, expected.get().getIndex());

    }

    @Test
    public void deleteTest(){

        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        // 저장소에서 index가 1인 엔티티 삭제
        wishListRepository.deleteById(1);

        // 삭제 후 저장소에 저장된 엔티티 개수가 0이 맞는지 확인
        int count = wishListRepository.findAll().size();
        Assertions.assertEquals(0, count);

    }

    @Test
    public void listAllTest(){

        // 엔티티 두개 생성
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        // 생성 후 저장소에 저장된 엔티티 개수가 2가 맞는지 확인
        int count = wishListRepository.findAll().size();
        Assertions.assertEquals(2, count);

    }

}
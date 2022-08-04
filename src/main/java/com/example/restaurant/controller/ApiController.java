package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;

    @ApiOperation("맛집 검색")
    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }

    @ApiOperation("맛집 위시리스트 추가")
    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);
        return wishListService.add(wishListDto);
    }

    @ApiOperation("맛집 위시리스트 출력")
    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }

    @ApiOperation("맛집 위시리스트 삭제")
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }

    @ApiOperation("맛집 방문 추가")
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }

}

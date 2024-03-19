package com.spring.mmm.domain.mukgroups.controller;

import com.spring.mmm.domain.mukgroups.controller.request.MukgroupCreateRequest;
import com.spring.mmm.domain.mukgroups.controller.request.MukgroupModifyRequest;
import com.spring.mmm.domain.mukgroups.controller.response.MukgroupResponse;
import com.spring.mmm.domain.mukgroups.service.MukgroupService;
import com.spring.mmm.domain.users.infra.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("groups")
public class MukGroupController {

    static UserEntity user = UserEntity.builder()
            .id(1L)
            .email("ssafy@ssafy.com")
            .nickname("ssafy")
            .password("ssafy")
            .build();

    private final MukgroupService mukgroupService;

    @GetMapping
    public ResponseEntity<MukgroupResponse> findMukgroup(){
        return ResponseEntity.ok(mukgroupService.findMyMukgroup(user).createMukgroupResponse());
    }

    @PostMapping
    public ResponseEntity<Void> createMukGroup(
            @RequestPart(value = "data", required = true) MukgroupCreateRequest mukgroupCreateRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        mukgroupService.saveMukGroup(mukgroupCreateRequest.getName(), user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{groupId}/name")
    public ResponseEntity<Void> modifyGroupName(
            @PathVariable Long groupId,
            @RequestBody MukgroupModifyRequest mukgroupModifyRequest){
        mukgroupService.modifyGroupName(groupId, mukgroupModifyRequest.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("{groupId}/image")
    public ResponseEntity<Void> modifyGroupImage(
            @PathVariable Long groupId,
            @RequestPart(value = "image") MultipartFile image){
        mukgroupService.modifyGroupImage(groupId, image);
        return ResponseEntity.ok().build();
    }

}

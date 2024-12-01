package com.jinsungwon99.user.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRelationListResponseDto {

    private String name;
    private String profileImageUrl;
    private Long relationUserId;
}
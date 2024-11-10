package com.jinsungwon99.admin.ui.query;

import com.jinsungwon99.admin.ui.dto.GetTableListResponse;
import com.jinsungwon99.admin.ui.dto.users.GetUserTableRequestDto;
import com.jinsungwon99.admin.ui.dto.users.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto dto);
}
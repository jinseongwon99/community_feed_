package com.jinsungwon99.admin.ui.query;

import com.jinsungwon99.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import java.util.List;

public interface UserStatsQueryRepository {

    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStats(int beforeDays);
}

package com.jinsungwon99.user.domain;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private final Long id;
    private final UserInfo info;
    private final PositiveIntegerCounter followingCount;
    private final PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.info = userInfo;
        this.followingCount = new PositiveIntegerCounter();
        this.followerCount = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.increase();
        targetUser.increaseFollowerCount();
    }

    public void changeName(String name) {
        info.patchProfileName(name);
    }

    public void unfollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException();
        }

        followingCount.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCount.increase();
    }

    private void decreaseFollowerCount() {
        followerCount.decrease();
    }

    // 동일값 판단
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int getFollowerCount() {
        return followerCount.getCount();
    }

    public int getFollowingCount() {
        return followingCount.getCount();
    }

    public String getName() {
        return info.getName();
    }

    public String getProfileImageUrl() {
        return info.getProfileImageUrl();
    }

    public void patchProfileImageUrl(String imageUrl) {
        this.info.patchProfileImageUrl(imageUrl);
    }
}

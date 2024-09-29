package com.example.springbootproject.user.dto;

import com.example.springbootproject.user.entity.User;

/**
 * 用户
 *
 * @param id       用户 id
 * @param nickname 昵称
 * @param avatar   头像
 */
public record UserDto(Long id, String nickname, String avatar) {

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getNickname(), user.getAvatar());
    }
}

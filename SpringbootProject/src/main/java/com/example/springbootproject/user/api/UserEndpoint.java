package com.example.springbootproject.user.api;

import com.example.springbootproject.user.dto.UserDto;
import com.example.springbootproject.user.entity.User;
import com.example.springbootproject.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api("用户管理相关Api")
public class UserEndpoint {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户分页", notes = "获取用户分页数据", httpMethod = "GET", tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码，从 0 开始", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页记录数量，默认 20", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序，格式:property,property(,ASC|DESC)", dataType = "String", paramType = "query")
    })
    @GetMapping
    public List<UserDto> findAll(Pageable pageable) {
        return userService.getPage(pageable)
                .getContent()
                .stream()
                .map(UserDto::fromUser)
                .toList();
    }

    @ApiOperation(value = "获取用户详细信息", notes = "获取用户详细信息", httpMethod = "GET", tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户 id", dataType = "int", paramType = "path")
    })
    @GetMapping("/{id}")
    public UserDto detail(@PathVariable Long id) {
        return UserDto.fromUser(userService.load(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户详情数据", notes = "更新用户详情数据", httpMethod = "POST", tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的主键", dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "userDto", value = "用户详情数据", dataType = "UserDto", paramType = "body"),
    })
    @PostMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userService.save(new UserDto(id, userDto.nickname(), userDto.avatar()));
        return user == null ? null : UserDto.fromUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除指定用户", notes = "删除指定用户", httpMethod = "DELETE", tags = "用户管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "所要删除用户的主键", dataType = "int", paramType = "path")
    })
    public boolean delete(@PathVariable Long id){
        this.userService.delete(id);
        return true;
    }
}

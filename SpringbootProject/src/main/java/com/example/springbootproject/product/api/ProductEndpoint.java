/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.springbootproject.product.api;

import com.example.springbootproject.product.dto.ProductCommentDto;
import com.example.springbootproject.product.dto.ProductDto;
import com.example.springbootproject.product.entity.Product;
import com.example.springbootproject.product.service.ProductService;
import com.example.springbootproject.user.dto.UserDto;
import com.example.springbootproject.user.entity.User;
import com.example.springbootproject.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Api("商品管理相关Api")
public class ProductEndpoint {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserRepository userRepository;

    /**
     * 产品信息列表
     */
    @ApiOperation(value = "获取商品分页数据", notes = "获取商品分页数据", httpMethod = "GET", tags = "商品管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页，从0开始，默认为第0页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每一页记录数的大小，默认为20", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort",
                    value = "排序，格式:property,property(,ASC|DESC)，如sort=firstname&sort=lastname,desc",
                    dataType = "String",
                    paramType = "query")
    })
    @GetMapping
    public List<ProductDto> list(Pageable pageable) {
        return productService.getPage(pageable)
                .getContent().stream()
                .map(ProductDto::fromProduct)
                .toList();
    }

    @ApiOperation(value = "获取商品详情数据", notes = "获取商品详情数据", httpMethod = "GET", tags = "商品管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品的主键", dataType = "int", paramType = "path")
    })
    @GetMapping("/{id}")
    public ProductDto detail(@PathVariable Long id) {
        Product product = productService.load(id);
        return ProductDto.fromProduct(product);
    }

    @ApiOperation(value = "获取商品的评论列表", notes = "获取商品的评论列表", httpMethod = "GET", tags = "商品管理相关Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品的主键", dataType = "int", paramType = "path")
    })
    @GetMapping("/{id}/comments")
    public List<ProductCommentDto> comments(@PathVariable Long id) {
        ProductDto product = ProductDto.fromProduct(productService.load(id));
        return productService.findAllByProduct(id).stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getAuthorId()).orElseThrow();
                    return ProductCommentDto.build(comment, product, UserDto.fromUser(user));
                })
                .toList();
    }

}

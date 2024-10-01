insert into TBPRODUCT (NAME, COVER_IMAGE, PRICE)
VALUES ('测试商品-001', '/products/cover/001.png', 100),
       ('测试商品-002', '/products/cover/002.png', 200),
       ('测试商品-003', '/products/cover/003.png', 300),
       ('测试商品-004', '/products/cover/004.png', 400),
       ('测试商品-005', '/products/cover/005.png', 500);

insert into TBUSER (NICKNAME, AVATAR)
VALUES ('zhangSan', '/users/avatar/zhangsan.png'),
       ('lisi', '/users/avatar/lisi.png'),
       ('wangwu', '/users/avatar/wangwu.png'),
       ('yanxiaoliu', '/users/avatar/yanxiaoliu.png');

insert into TBPRODUCT_COMMENT (PRODUCT_ID, AUTHOR_ID, CONTENT, CREATED)
VALUES (3, 1, '非常不错的商品', CURRENT_TIMESTAMP()),
       (3, 3, '非常不错的商品', CURRENT_TIMESTAMP()),
       (3, 4, '哈哈，谁用谁知道', CURRENT_TIMESTAMP());

drop table if exists tbProduct;
drop table if exists tbUser;
drop table if exists tbProduct_Comment;

-- product

create table tbProduct
(
    id          int auto_increment not null comment '商品 id',
    name        varchar(20)        not null comment '商品名称',
    cover_image varchar(100) comment '商品图片',
    price       int                not null default 0 comment '价格，单位(分)',

    primary key (id)
);

-- comment

create table tbProduct_Comment
(
    id         int auto_increment not null comment '评论 id',
    product_id int                not null comment '评论商品',
    author_id  int                not null comment '评论用户',
    content    text      default '' comment '评论内容',
    created    TIMESTAMP default (now()) comment '评论时间',

    primary key (id)
);

-- user

create table tbUser
(
    id       int auto_increment not null comment '用户 id',
    nickname varchar(30)        not null comment '用户昵称',
    avatar   varchar(255) comment '用户头像',

    primary key (id)
);

-- liquibase formatted sql
-- changeset chanki5451 : 2024-08-22

-- CafeMenu
create table public.cafe_menu
(
    id          int generated always as identity primary key,
    name        varchar(70)          not null,
    description varchar(255),
    price       int                  not null,
    category    varchar(50)          not null,
    available   boolean default true not null
);
comment on column public.cafe_menu.id is '카페 메뉴 ID';
comment on column public.cafe_menu.name is '메뉴 이름';
comment on column public.cafe_menu.description is '메뉴 설명';
comment on column public.cafe_menu.price is '메뉴 가격';
comment on column public.cafe_menu.category is '메뉴 카테고리';
comment on column public.cafe_menu.available is '판매 가능 여부';

-- CafeCart
create table public.cafe_cart
(
    id            int generated always as identity primary key,
    title         varchar(70)                         not null,
    description   varchar(255),
    created_at    timestamp default current_timestamp not null,
    expires_at    timestamp                           not null,
    shared_url    varchar(255) unique                 not null,
    created_by_id varchar(36)                         not null
);
comment on column public.cafe_cart.id is '카페 장바구니 ID';
comment on column public.cafe_cart.title is '장바구니 제목';
comment on column public.cafe_cart.description is '장바구니 설명';
comment on column public.cafe_cart.created_at is '장바구니 생성 시간';
comment on column public.cafe_cart.expires_at is '장바구니 만료 시간';
comment on column public.cafe_cart.shared_url is '장바구니 공유 URL';
comment on column public.cafe_cart.created_by_id is '작성자 UUID';

-- CafeCartItem
create table public.cafe_cart_item
(
    id              int generated always as identity primary key,
    cafe_cart_id    int references public.cafe_cart (id) on delete cascade not null,
    cafe_menu_id    int references public.cafe_menu (id)                   not null,
    quantity        int                                                    not null,
    created_by_id   varchar(36)                                            not null,
    created_by_name VARCHAR(30)                                            not null,
    created_at      timestamp default current_timestamp                    not null
);
comment on column public.cafe_cart_item.id is '카페 장바구니 항목 ID';
comment on column public.cafe_cart_item.cafe_cart_id is '참조된 카페 장바구니 ID';
comment on column public.cafe_cart_item.cafe_menu_id is '참조된 카페 메뉴 항목 ID';
comment on column public.cafe_cart_item.quantity is '담긴 수량';
comment on column public.cafe_cart_item.created_by_id is '작성자 UUID';
comment on column public.cafe_cart_item.created_by_name is '작성자 이름';
comment on column public.cafe_cart_item.created_at is '항목 생성 시각';
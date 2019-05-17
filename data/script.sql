create extension citext;


create table app_user (
                        id bigserial primary key,
                        full_name varchar(128) not null,
                        email citext unique not null ,
                        password varchar(128) not null,
                        encrypted_password varchar(256) not null,
                        enabled  boolean not null
);

create table post (
                    id bigserial primary key,
                    post_title  text,
                    post_text  text,
                    app_user_id bigint not null ,
                    creation_date timestamp,

                    foreign key (app_user_id) references app_user(id)
);

create table user_post_reaction (
                                  id bigserial primary key,
                                  post_id bigint not null ,
                                  app_user_id bigint not null ,
                                  comment_text text,
                                  reaction_type  varchar(10),

                                  foreign key (app_user_id) references app_user(id),
                                  foreign key (post_id) references post(id)
);

create table follow (
  main_user_id bigint not null,
  following_user_id bigint not null
);


INSERT INTO public.user_post_reaction (post_id, app_user_id, comment_text, reaction_type) VALUES (4, 25044, 'Comment from 25044', 'COMMENT');
INSERT INTO public.user_post_reaction (post_id, app_user_id, comment_text, reaction_type) VALUES (4, 25043, 'Comment from 25043', 'COMMENT');
INSERT INTO public.user_post_reaction (post_id, app_user_id, comment_text, reaction_type) VALUES (4, 25043, '', 'LIKE');
INSERT INTO public.user_post_reaction (post_id, app_user_id, comment_text, reaction_type) VALUES (4, 25042, '', 'LIKE');
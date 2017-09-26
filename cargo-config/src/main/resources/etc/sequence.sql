CREATE SEQUENCE book_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE user_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE role_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE permission_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE user_role_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE role_permission_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

CREATE SEQUENCE system_log_sequence
INCREMENT BY 1　 -- 每次加几个
START WITH 1　　 -- 从1开始计数
NOMAXVALUE　　　 -- 不设置最大值
NOCYCLE　　　　　-- 一直累加，不循环
NOCACHE;

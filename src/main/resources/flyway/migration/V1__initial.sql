CREATE TABLE public.users (
	id int8 NOT NULL,
	user_name varchar(255) NULL,
	"password" varchar(255) NULL,
	authority varchar(255) NULL,
	enabled boolean NOT NULL DEFAULT true,
	"locked" boolean NOT NULL DEFAULT true,
	credentials_expired boolean NOT NULL DEFAULT true,
	expired boolean NOT NULL DEFAULT true,
	expired_at date NULL,
	creator int8 NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);
comment on table users is '使用者資料表';
comment on column users.id is 'ID';
comment on column users.authority is '權限';
comment on column users.creator is '建立者ID';
comment on column users.credentials_expired is '是否驗簽過期';
comment on column users.enabled is '是否鎖定';
comment on column users.expired is '是否過期';
comment on column users.expired_at is '試用過期日';
comment on column users.locked is '是否鎖定';
comment on column users.password is '密碼';
comment on column users.user_name is '帳號';

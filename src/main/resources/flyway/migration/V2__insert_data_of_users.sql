INSERT INTO public.users (id, user_name, "password", authority,credentials_expired, "enabled", "locked", expired, expired_at, creator)
VALUES (1, 'admin@test.com', '', 'ADMIN', false, false, false, false, '2030-02-16', null);

INSERT INTO public.users (id, user_name, "password", authority,credentials_expired, "enabled", "locked", expired, expired_at, creator)
VALUES (2, 'staff@test.com', '', 'STAFF', false, false, false, false, '2030-02-16', null);

INSERT INTO public.users (id, user_name, "password", authority,credentials_expired, "enabled", "locked", expired, expired_at, creator)
VALUES (3, 'guest@test.com', '', 'GUEST', false, false, false, false, '2030-02-16', null);

--DROP TABLE IF EXISTS public.app_users;
--DROP SEQUENCE IF EXISTS public.app_users_seq_id;

CREATE SEQUENCE IF NOT EXISTS app_users_seq_id AS BIGINT;
CREATE TABLE IF NOT EXISTS public.app_users(
    id BIGINT PRIMARY KEY DEFAULT nextval('app_users_seq_id'),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    enabled BOOLEAN NOT NULL DEFAULT FALSE);

INSERT INTO public.app_users (
  id, username, password, enabled)
SELECT nextval('app_users_seq_id'), 'gregoriojesus0@gmail.com', '$2a$10$QNyGH80mI/E/0GWpQ4gUoucXQBY2XDbBQ3zQ5yTWO3zrlTn4KUMga', TRUE
WHERE NOT EXISTS (SELECT * FROM app_users WHERE username='gregoriojesus0@gmail.com');
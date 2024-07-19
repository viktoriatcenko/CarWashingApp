INSERT INTO person_app (name, role, created_at, created_by, deleted_at,
                        can_give_discount, removed, password)
VALUES ('admin', 'ROLE_ADMIN', CURRENT_TIMESTAMP, null, NULL, TRUE, FALSE, 'password');
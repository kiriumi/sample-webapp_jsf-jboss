DROP VIEW IF EXISTS user_role;

CREATE OR REPLACE
    VIEW user_role AS
        SELECT
                u.emailaddress
                ,u.last_name
                ,u.first_name
                ,r.role
            FROM
                public.user u
                ,public.role r
            WHERE
                u.emailaddress = r.emailaddress;

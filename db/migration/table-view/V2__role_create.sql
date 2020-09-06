CREATE
    TABLE public.role(
        emailaddress TEXT NOT NULL
        ,role TEXT NOT NULL
        ,CONSTRAINT role_pkey PRIMARY KEY (emailaddress, role)
    );

COMMENT ON TABLE public.role IS 'ロール';
COMMENT ON COLUMN public.role.emailaddress IS 'Eメールアドレス';
COMMENT ON COLUMN public.role.role IS 'ロール';

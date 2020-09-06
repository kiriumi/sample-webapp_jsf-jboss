CREATE
    TABLE public.user(
        emailaddress TEXT NOT NULL
        ,last_name TEXT NOT NULL
        ,first_name TEXT NOT NULL
        ,password TEXT NOT NULL
        ,postal_code CHAR(7) NOT NULL
        ,region TEXT NOT NULL
        ,locality TEXT NOT NULL
        ,street_address TEXT NOT NULL
        ,extended_address TEXT DEFAULT ''
        ,version INTEGER NOT NULL DEFAULT 1
        ,createdtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ,updatedtime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ,CONSTRAINT user_pkey PRIMARY KEY (emailaddress)
    );

CREATE INDEX ON public.user(postal_code);

COMMENT ON TABLE public.user IS 'ユーザ';
COMMENT ON COLUMN public.user.emailaddress IS 'Eメールアドレス';
COMMENT ON COLUMN public.user.last_name IS '姓';
COMMENT ON COLUMN public.user.first_name IS '名';
COMMENT ON COLUMN public.user.password IS 'パスワード';
COMMENT ON COLUMN public.user.postal_code IS '郵便番号';
COMMENT ON COLUMN public.user.region IS '都道府県';
COMMENT ON COLUMN public.user.locality IS '市区町村';
COMMENT ON COLUMN public.user.street_address IS '町域';
COMMENT ON COLUMN public.user.extended_address IS 'その他住所';
COMMENT ON COLUMN public.user.version IS 'バージョン';
COMMENT ON COLUMN public.user.createdtime IS '作成日時';
COMMENT ON COLUMN public.user.updatedtime IS '更新日時';

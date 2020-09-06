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

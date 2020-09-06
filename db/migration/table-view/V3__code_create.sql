CREATE
    TABLE public.code(
        kind TEXT NOT NULL
        ,value TEXT NOT NULL
        ,label TEXT NOT NULL
        ,description TEXT NOT NULL DEFAULT ''
        ,sort_order INTEGER NOT NULL DEFAULT 0
    );

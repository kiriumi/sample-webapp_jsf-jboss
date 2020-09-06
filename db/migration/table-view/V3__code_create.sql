CREATE
    TABLE public.code(
        kind TEXT NOT NULL
        ,value TEXT NOT NULL
        ,label TEXT NOT NULL
        ,description TEXT NOT NULL DEFAULT ''
        ,sort_order INTEGER NOT NULL DEFAULT 0
        ,CONSTRAINT code_pkey PRIMARY KEY (kind, value)
    );

COMMENT ON TABLE public.code IS 'コード';
COMMENT ON COLUMN public.code.kind IS 'コード種別';
COMMENT ON COLUMN public.code.value IS '値';
COMMENT ON COLUMN public.code.label IS 'ラベル';
COMMENT ON COLUMN public.code.description IS '説明';
COMMENT ON COLUMN public.code.sort_order IS '順序';


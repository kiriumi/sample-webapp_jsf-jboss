DELETE FROM public.code;

INSERT INTO public.code ( kind, value, label, description, sort_order ) VALUES ('ROLE','admin','管理者','管理者だよ',1);
INSERT INTO public.code ( kind, value, label, description, sort_order ) VALUES ('ROLE','user','ユーザ','ユーザだよ',2);

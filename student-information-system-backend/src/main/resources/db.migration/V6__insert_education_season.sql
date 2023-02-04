ALTER TABLE public.education_season
    ADD CONSTRAINT "uc_educatıon_season_name" UNIQUE (name);

INSERT INTO public.education_season (education_season_id, name)
VALUES (1, '2022-2023');

INSERT INTO public.education_season (education_season_id, name)
VALUES (2, '2023-2024');

INSERT INTO public.education_season (education_season_id, name)
VALUES (3, '2024-2025');

INSERT INTO public.education_season (education_season_id, name)
VALUES (4, '2025-2026');

INSERT INTO public.education_season (education_season_id, name)
VALUES (5, '2026-2027');
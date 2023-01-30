INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('AA', 90, 100, 4, TRUE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('BA', 80, 89, 3.5, TRUE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('BB', 74, 79, 3, TRUE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('CB', 70, 74, 2.5, TRUE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('CC', 60, 69, 2, TRUE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('DC', 50, 59, 1.5, null);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('DD', 40, 49, 1, FALSE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('FD', 30, 39, 0.5, FALSE);

INSERT INTO public.letter_grade (grade, min, max, coefficient, is_pass)
VALUES ('FF', 0, 29, 0, FALSE);
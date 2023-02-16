CREATE TABLE public.lesson_request
(
    lesson_request_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    live_lesson_id    BIGINT,
    student_id        BIGINT,
    is_approved       BOOLEAN,
    CONSTRAINT pk_lesson_request PRIMARY KEY (lesson_request_id),
    CONSTRAINT FK_LESSON_REQUEST_ON_LIVE_LESSON_ID FOREIGN KEY (live_lesson_id) REFERENCES public.live_lesson (live_lesson_id),
    CONSTRAINT FK_LESSON_REQUEST_ON_STUDENT_ID FOREIGN KEY (student_id) REFERENCES public.student (student_id)
);
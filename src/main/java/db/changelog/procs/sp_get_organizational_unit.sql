CREATE OR REPLACE FUNCTION sp_get_organizational_unit(
    page_number INTEGER = NULL,
    page_size INTEGER = NULL
)
    RETURNS SETOF public.organizational_unit AS
$BODY$
DECLARE
    PageOffset INTEGER :=0;
BEGIN

    PageOffset := page_number * page_size;

    RETURN QUERY
        SELECT *
        FROM public.organizational_unit
        ORDER BY id
        LIMIT page_size
            OFFSET PageOffset;
END;
$BODY$
    LANGUAGE plpgsql;
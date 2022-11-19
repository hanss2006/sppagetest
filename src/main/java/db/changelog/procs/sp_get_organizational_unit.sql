CREATE OR REPLACE FUNCTION sp_get_organizational_unit (
    Page_number INTEGER,
    Page_size INTEGER
)
    RETURNS SETOF organizational_unit AS
$BODY$
DECLARE
    PageOffset INTEGER;
BEGIN
    PageOffset := Page_number * Page_size;

    RETURN QUERY
        SELECT *
        FROM organizational_unit
        ORDER BY id LIMIT Page_size OFFSET PageOffset;

END;
$BODY$
    LANGUAGE plpgsql;

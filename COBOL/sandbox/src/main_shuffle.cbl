        IDENTIFICATION DIVISION.
        PROGRAM-ID. MAIN.

        DATA DIVISION.
        WORKING-STORAGE SECTION.
          78  Table-Len    VALUE 10.
          01  ttable-area.
            03  ttable     PIC 9(2) OCCURS Table-Len TIMES.
          01 IDX PIC 9(2) VALUE 0.

        PROCEDURE DIVISION.
          PERFORM POP-HAND VARYING IDX FROM 1 BY 1 UNTIL IDX>Table-Len.
          CALL 'knuth-shuffle' USING ttable-area.
          PERFORM LOG-HAND VARYING IDX FROM 1 BY 1 UNTIL IDX=Table-Len.
          DISPLAY 'TRACER Ready.'
        STOP RUN.

        POP-HAND.
          MOVE IDX TO ttable (IDX).

        LOG-HAND.
          DISPLAY 'hand [' IDX '] = ' ttable (IDX).

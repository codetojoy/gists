            *> setup the identification division
            IDENTIFICATION DIVISION.
            *> setup the program id
            PROGRAM-ID. WARO.
            DATA DIVISION.
              WORKING-STORAGE SECTION.
              01 MESSAGE-VAR PIC A(10) VALUE 'WARO COBOL'.
              01 PLAYER1-REC.
                 02 PLAYER1-NAME PIC X(4) VALUE 'Bach'.      
                 02 PLAYER1-POINTS PIC 9(2).
                 02 PLAYER1-HAND PIC 9(2) OCCURS 4 TIMES.
              01 PLAYER2-REC.
                 02 PLAYER2-NAME PIC X(6) VALUE 'Chopin'.      
                 02 PLAYER2-POINTS PIC 9(2).
                 02 PLAYER2-HAND PIC 9(2) OCCURS 4 TIMES.
              01 PLAYER3-REC.
                 02 PLAYER3-NAME PIC X(6) VALUE 'Mozart'.      
                 02 PLAYER3-POINTS PIC 9(2).
                 02 PLAYER3-HAND PIC 9(2) OCCURS 4 TIMES.
              01 KITTY-REC.
                 02 KITTY-HAND PIC 9(2) OCCURS 4 TIMES.
              01 DECK-REC.
                 02 CARDS PIC 9(2) OCCURS 4 TIMES.
            *> setup the procedure division (like 'main' function)
            PROCEDURE DIVISION.
            BEGIN.
              *> print a string
              DISPLAY "TRACER : " MESSAGE-VAR.
              DISPLAY "TRACER : " PLAYER1-NAME.
              DISPLAY "TRACER : " PLAYER2-NAME.
              DISPLAY "TRACER : " PLAYER3-NAME.
            *> end our program
            STOP RUN.

       IDENTIFICATION DIVISION.
       PROGRAM-ID. log-kitty.
 
       DATA DIVISION.
       LOCAL-STORAGE SECTION.
          01 IDX PIC 9(2).

       LINKAGE SECTION.
       78 NUM-CARDS               VALUE 4.
       01 KITTY-REC.
         02 CARDS PIC 9(2) OCCURS NUM-CARDS TIMES.

       PROCEDURE DIVISION USING KITTY-REC.
          DISPLAY " "
            
          PERFORM SHOW-KITTY VARYING IDX FROM 1 BY 1 UNTIL IDX>NUM-CARDS.

          GOBACK
          .

        SHOW-KITTY.
          DISPLAY 'kitty [' IDX '] = ' CARDS (IDX).

       IDENTIFICATION DIVISION.
       PROGRAM-ID. log-player.
 
       DATA DIVISION.
       LOCAL-STORAGE SECTION.
          01 IDX PIC 9(2).

       LINKAGE SECTION.
       78 NUM-CARDS               VALUE 4.
       01 PLAYER-REC.
          02 PLAYER-NAME PIC X(6).      
          02 PLAYER-POINTS PIC 9(2).
          02 PLAYER-HAND PIC 9(2) OCCURS NUM-CARDS TIMES.

       PROCEDURE DIVISION USING PLAYER-REC.
          DISPLAY " "
          DISPLAY "name: " PLAYER-NAME
          DISPLAY "points: " PLAYER-POINTS
            
          PERFORM LOG-HAND VARYING IDX FROM 1 BY 1 UNTIL IDX>NUM-CARDS.

          GOBACK
          .

        LOG-HAND.
          DISPLAY 'hand [' IDX '] = ' PLAYER-HAND (IDX).

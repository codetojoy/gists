       IDENTIFICATION DIVISION.
       PROGRAM-ID. WARO.
       DATA DIVISION.
         WORKING-STORAGE SECTION.
         01 GREETING PIC A(10) VALUE 'WARO COBOL'.
         01 I PIC 9(2).
         01 X PIC 9(2).
         01 Y PIC 9(2).
         01 TMP PIC 9(2).
         78 NUM-CARDS-HAND VALUE 4.
         78 NUM-CARDS-DECK VALUE 16.

         *> Players should probably be parallel arrays/tables:
         *> e.g. PLAYER-NAMES PIC X(6) OCCURS NUM-PLAYERS TIMES.
         *> e.g. PLAYER-BIDS  PIC 9(2) OCCURS NUM-PLAYERS TIMES.
         *> and not splayed out as below. 
         *> This is a toy example!
         01 PLAYER1-REC.
            02 PLAYER1-NAME PIC X(6) VALUE '  Bach'.      
            02 PLAYER1-BID PIC 9(2).
            02 PLAYER1-POINTS PIC 9(2).
            02 PLAYER1-HAND PIC 9(2) OCCURS NUM-CARDS-HAND TIMES.
         01 PLAYER2-REC.
            02 PLAYER2-NAME PIC X(6) VALUE 'Chopin'.      
            02 PLAYER2-BID PIC 9(2).
            02 PLAYER2-POINTS PIC 9(2).
            02 PLAYER2-HAND PIC 9(2) OCCURS NUM-CARDS-HAND TIMES.
         01 PLAYER3-REC.
            02 PLAYER3-NAME PIC X(6) VALUE 'Mozart'.      
            02 PLAYER3-BID PIC 9(2).
            02 PLAYER3-POINTS PIC 9(2).
            02 PLAYER3-HAND PIC 9(2) OCCURS NUM-CARDS-HAND TIMES.
         01 KITTY-REC.
            02 PRIZE-CARD PIC 9(2).
            02 KITTY-CARDS PIC 9(2) OCCURS NUM-CARDS-HAND TIMES.
         01 DECK-REC.
            02 DECK-CARDS PIC 9(2) OCCURS NUM-CARDS-DECK TIMES.
       PROCEDURE DIVISION.
       BEGIN.
         DISPLAY "TRACER : " GREETING.
         PERFORM INIT-DECK VARYING I FROM 1 BY 1 UNTIL I>NUM-CARDS-DECK.
         CALL 'knuth-shuffle' USING DECK-REC.
         CALL 'log-deck' USING DECK-REC.
         PERFORM ASSIGN-CARD 
           VARYING I FROM 1 BY 1 UNTIL I > NUM-CARDS-DECK.
         CALL 'log-kitty' USING KITTY-REC.
         CALL 'log-player' USING PLAYER1-REC.
         CALL 'log-player' USING PLAYER2-REC.
         CALL 'log-player' USING PLAYER3-REC.

         PERFORM PLAY-ROUND
           VARYING I FROM 1 BY 1 UNTIL I > NUM-CARDS-HAND.

         DISPLAY "TRACER Ready."
       STOP RUN.

       PLAY-ROUND.
         MOVE KITTY-CARDS (I) TO PRIZE-CARD.
         DISPLAY "TRACER round " I " prize: " PRIZE-CARD.
         CALL 'player-turn' USING PRIZE-CARD, PLAYER1-REC.
         CALL 'player-turn' USING PRIZE-CARD, PLAYER2-REC.
         CALL 'player-turn' USING PRIZE-CARD, PLAYER3-REC.
         CALL 'log-player' USING PLAYER1-REC.
         CALL 'log-player' USING PLAYER2-REC.
         CALL 'log-player' USING PLAYER3-REC.
  
       ASSIGN-CARD.
         DIVIDE NUM-CARDS-HAND INTO I GIVING X REMAINDER Y.
         IF Y = 0
           MOVE 4 TO Y
         END-IF.

         IF I >= 1 AND I <= 4
           MOVE DECK-CARDS (I) TO KITTY-CARDS (Y)
         ELSE IF I >= 5 AND I <= 8
           MOVE DECK-CARDS (I) TO PLAYER1-HAND (Y)
         ELSE IF I >= 9 AND I <= 12
           MOVE DECK-CARDS (I) TO PLAYER2-HAND (Y)
         ELSE IF I >= 13 AND I <= 16
           MOVE DECK-CARDS (I) TO PLAYER3-HAND (Y)
         ELSE
           DISPLAY "TRACER SEVERE ERROR I: " I
         END-IF.

       INIT-DECK.
         MOVE I TO DECK-CARDS (I).

        IDENTIFICATION DIVISION.
        PROGRAM-ID. quick.
        DATA DIVISION.
        WORKING-STORAGE SECTION.
          01 A PIC 9(2) VALUE 8.
          01 B PIC 9(2) VALUE 5.
          01 C PIC 9(2) VALUE 0.
          01 D PIC 9(2) VALUE 0.

        PROCEDURE DIVISION.
          DISPLAY 'TRACER COBOL LIVES!'.
          DIVIDE B INTO A GIVING C REMAINDER D.
          DISPLAY 'TRACER C: ' C.
          DISPLAY 'TRACER D: ' D.
        STOP RUN.


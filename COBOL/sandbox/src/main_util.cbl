            IDENTIFICATION DIVISION.
            PROGRAM-ID. MAIN.

            DATA DIVISION.
               WORKING-STORAGE SECTION.
               01 WS-STUDENT-ID PIC 9(4) VALUE 1000.
               01 WS-STUDENT-NAME PIC A(15) VALUE 'Tim'.

            PROCEDURE DIVISION.
               CALL 'UTIL' USING WS-STUDENT-ID, WS-STUDENT-NAME.
               DISPLAY 'Student Id : ' WS-STUDENT-ID
               DISPLAY 'Student Name : ' WS-STUDENT-NAME
            STOP RUN.

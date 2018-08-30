
module net.codetojoy {
    // instead of 'exporting', we must 'open' because Jackson
    // uses deep reflection.
    //
    // exports net.codetojoy;
    opens net.codetojoy;

    requires jackson.databind;
}

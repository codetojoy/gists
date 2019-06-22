package net.codetojoy.client;

import net.codetojoy.service.ItemService;
import net.codetojoy.pojo.*;

public class Client {
    private static final String GET = "g";
    private static final String LIST = "l";
    private static final String CLEAR = "c";

    private final ItemService itemService = new ItemService();

    private void processCommand() throws Exception {
        Prompt prompt = new Prompt();
        String input = prompt.getInput("\n\ncmd: [G=get, L=list, C=clear, Q=quit] ?",
                        GET, LIST, CLEAR);

        if (input.equalsIgnoreCase(GET)) {
            String itemIdStr = prompt.getInput("enter item id: ");
            int itemId = Integer.parseInt(itemIdStr);
            Item item = itemService.getItem(itemId);
            System.out.println("TRACER item is : " + item.toString());
        } else if (input.equalsIgnoreCase(LIST)) {
            itemService.list();
        } else if (input.equalsIgnoreCase(CLEAR)) {
            itemService.clear();
        }
    }

    public void inputLoop() {
        while (true) {
            try {
                processCommand();
            } catch(Exception ex) {
                System.err.println("\nTRACER command failed! ex: " + ex.getMessage());
            }
        }
    }
}

package net.codetojoy.client;

import net.codetojoy.service.ItemService;

public class Client {
    private static final String EDIT = "e";
    private static final String FINALIZE = "f";
    private static final String LIST = "l";
    private static final String VIEW = "v";

    private final ItemService itemService = new ItemService();

    private void processCommand() throws Exception {
        Prompt prompt = new Prompt();
        String input = prompt.getInput("\n\ncmd: [E=edit, F=finalize, L=list, V=view, Q=quit] ?",
                        EDIT, FINALIZE, LIST, VIEW);

        if (input.equalsIgnoreCase(EDIT)) {
            String itemIdStr = prompt.getInput("enter item id: ");
            long itemId = Long.parseLong(itemIdStr);
            String userId = prompt.getInput("enter user id: ");
            itemService.edit(itemId, userId);
        } else if (input.equalsIgnoreCase(FINALIZE)) {
            String itemIdStr = prompt.getInput("enter item id: ");
            long itemId = Long.parseLong(itemIdStr);
            String userId = prompt.getInput("enter user id: ");
            itemService.finalize(itemId, userId);
        } else if (input.equalsIgnoreCase(LIST)) {
            itemService.list();
        } else if (input.equalsIgnoreCase(VIEW)) {
            String itemIdStr = prompt.getInput("enter item id: ");
            long itemId = Long.parseLong(itemIdStr);
            String userId = prompt.getInput("enter user id: ");
            itemService.view(itemId, userId);
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

package com.dk.flowershop.commandline;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.dk.flowershop.CatalogClient;
import com.dk.flowershop.action.FulfilOrderAction;
import com.dk.flowershop.exception.FlowerShopException;
import com.dk.flowershop.input.FulFilOrderInput;
import com.dk.flowershop.response.FulfilOrderResponse;

import java.util.List;

@Parameters(commandNames = "new", commandDescription = "Create a new order at the flower shop!")
public class CommandFulFilOrder implements CommandExecutor {

    @Parameter(names = "-flowers")
    private List<BundleQty> flowers;

    @Parameter(names = "-help", description = "Type new -help to see all available options", help = true)
    private boolean help;

    public boolean isHelp() {
        return help;
    }

    @Override
    public void execute() {
        FulFilOrderInput input = new FulFilOrderInput();
        CatalogClient catalogClient = new CatalogClient();
        FulfilOrderAction fulfilOrderAction = new FulfilOrderAction(catalogClient);

        flowers
                .stream()
                .forEach(b -> {
                    FulFilOrderInput.Item item = new FulFilOrderInput.Item();
                    item.setCatalogCode(b.bundle);
                    item.setQuantity(b.quantity);
                    input.addItem(item);
                });

        try {
            FulfilOrderResponse response = fulfilOrderAction.withInput(input).invoke();
            System.out.println(response);
        } catch (FlowerShopException e) {
            e.printStackTrace();
        }
    }
}

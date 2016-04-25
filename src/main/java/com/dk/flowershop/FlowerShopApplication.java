package com.dk.flowershop;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.dk.flowershop.commandline.BundleQtyConverter;
import com.dk.flowershop.commandline.CommandFulFilOrder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FlowerShopApplication {

    public static void main(String[] args) {
        CommandFulFilOrder commandFulFilOrder = new CommandFulFilOrder();
        JCommander jCommander = new JCommander();
        jCommander.setProgramName("flowershop");
        jCommander.addCommand(commandFulFilOrder);
        jCommander.addConverterFactory(new BundleQtyConverter.Factory());

        try {
            jCommander.parse(args);
            if (commandFulFilOrder.isHelp()) {
                jCommander.usage();
            } else {
                commandFulFilOrder.execute();
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid code used for buying. Use one of " + Arrays.toString(CatalogCode.values()));
        } catch (ParameterException e) {
            jCommander.usage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

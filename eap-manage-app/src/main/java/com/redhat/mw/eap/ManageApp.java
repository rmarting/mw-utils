package com.redhat.mw.eap;

import java.net.URL;

import org.jboss.dmr.ModelNode;

import com.redhat.mw.eap.exceptions.CommandException;
import com.redhat.mw.eap.utils.DeployUtils;

public class ManageApp {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Usage: java com.redhat.mw.eap.ManageApp [deploy /path/to/application | undeploy application]");
        }

        // Command
        String command = args[0];

        // Application Name
        String name = null;

        // Model node
        ModelNode modelNodeCmd = null;

        switch (command) {
            case "deploy":
                // Application File
                URL url = new URL(args[1]);
                String[] pathElements = url.getFile().split("/");
                name = pathElements[pathElements.length - 1];

                modelNodeCmd = DeployUtils.getDeploy(url.toExternalForm(), name);
                break;

            case "undeploy":
                name = args[1];
                modelNodeCmd = DeployUtils.getUndeploy(name);
                break;

            default:
                break;
        }

        if (null != modelNodeCmd) {
            try {
                DeployUtils.invokeCommand(command, modelNodeCmd);
            } catch (CommandException e) {
                System.err.println("Error during invocation command. Message: " + e.getMessage());
            }
        } else {
            System.out.println(command + "\n------------------------------\n");
            System.out.println("Unable to get model node to invoke");
        }

        System.exit(1);
    }

}

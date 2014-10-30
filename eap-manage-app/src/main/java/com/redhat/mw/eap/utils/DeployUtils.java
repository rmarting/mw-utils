package com.redhat.mw.eap.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;

import com.redhat.mw.eap.exceptions.CommandException;

public final class DeployUtils {

    private DeployUtils() {
        // Not instanciable
    }

    public static ModelNode getUndeploy(String name) {
        ModelNode undeployRequest = new ModelNode();
        undeployRequest.get("operation").set("undeploy");
        undeployRequest.get("address", "deployment").set(name);

        ModelNode removeRequest = new ModelNode();
        removeRequest.get("operation").set("remove");
        removeRequest.get("address", "deployment").set(name);

        ModelNode composite = new ModelNode();
        composite.get("operation").set("composite");
        composite.get("address").setEmptyList();
        final ModelNode steps = composite.get("steps");
        steps.add(undeployRequest);
        steps.add(removeRequest);
        return composite;
    }

    public static ModelNode getDeploy(String url, String name) {
        ModelNode deployRequest = new ModelNode();
        deployRequest.get("operation").set("deploy");
        deployRequest.get("address", "deployment").set(name);

        ModelNode addRequest = new ModelNode();
        addRequest.get("operation").set("add");
        addRequest.get("address", "deployment").set(name);
        addRequest.get("content").get(0).get("url").set(url);

        ModelNode composite = new ModelNode();
        composite.get("operation").set("composite");
        composite.get("address").setEmptyList();
        final ModelNode steps = composite.get("steps");
        steps.add(addRequest);
        steps.add(deployRequest);
        return composite;
    }

    public static void invokeCommand(String command, ModelNode modelNode) throws CommandException {
        System.out.println(command + "\n------------------------------\n");
        System.out.println("Formatted:\n" + modelNode.toJSONString(false));

        ModelControllerClient client;
        try {
            client = ModelControllerClient.Factory.create(InetAddress.getByName("127.0.0.1"), 9999);
        } catch (UnknownHostException e) {
            throw new CommandException("Unknow host to connect", e);
        }
        ModelNode returnVal;
        try {
            returnVal = client.execute(modelNode);
        } catch (IOException e) {
            throw new CommandException("Unable to invoke command", e);
        }

        System.out.println("Result:\n" + returnVal.get("result").toString());
        System.out.println("\n------------------------------\n");
    }

}

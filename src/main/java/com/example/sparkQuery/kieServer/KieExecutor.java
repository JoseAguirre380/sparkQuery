package com.example.sparkQuery.kieServer;

import com.example.sparkQuery.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieServiceResponse;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KieExecutor {

    @Autowired
    KieServerProperties kieServerProperties;

    @Autowired
    RuleServicesClient rulesClient;

    @Autowired
    KieServices kieServices;


    private static final String URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String USER = "wbadmin";
    private static final String PASSWORD = "wbadmin";

    private static final MarshallingFormat FORMAT = MarshallingFormat.JSON;

    private static KieServicesConfiguration conf;
    private static KieServicesClient kieServicesClient;

    public static KieServicesClient initialize() {
        conf = KieServicesFactory.newRestConfiguration(URL, USER, PASSWORD);

        Set<Class<?>> allClasses = new HashSet<Class<?>>();
        allClasses.add(Message.class);
        conf.addExtraClasses(allClasses);
        kieServicesClient = KieServicesFactory.newKieServicesClient(conf);

        return kieServicesClient;

    }

    Message responseMessage;

    public Message ruleExecutor(String jsonPayload) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
//            KieServices ks = KieServices.Factory.get ();
//            KieContainer kContainer = ks.getKieClasspathContainer ();
//            KieSession kSession = kContainer.newKieSession ("ksession-rule");

//            KieServices kieServices = KieServices.Factory.get();
//            KieContainer kContainer = kieServices.getKieClasspathContainer();
//            StatelessKieSession statelessKsession = kContainer.newStatelessKieSession("KSession1");
//


            Message message = objectMapper.readValue(jsonPayload, Message.class);

//            kSession.insert(message);
//
//            kSession.fireAllRules();

            Boolean b = new Boolean(true);


            String containerId = "ternium_1.0.0-SNAPSHOT";
            System.out.println("== Sending commands to the server ==");
            RuleServicesClient rulesClient = initialize().getServicesClient(RuleServicesClient.class);

            KieCommands commandsFactory = KieServices.Factory.get().getCommands();

            GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
            getObjectsCommand.setOutIdentifier("objects");


            //Se inserta el objecto y se agrega un identificador con el que se va a recuperar de vuelta
            Command<?> insert = commandsFactory.newInsert(message, "objectIdentifier");
            Command<?> agendaGroup = commandsFactory.newAgendaGroupSetFocus("Transformation");
            Command<?> fireAllRules = commandsFactory.newFireAllRules();
            Command<?> getObjects = commandsFactory.newGetObjects("objects");
//            Command<?> executeTest = commandsFactory.exe


            Command<?> batchCommand = commandsFactory.newBatchExecution(Arrays.asList(insert, agendaGroup, getObjects, fireAllRules));

            ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(containerId, batchCommand);


            if (executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
                System.out.println("Commands executed with success! Response: ");

                //se obtiene el objecto insertado con los valores modificados en la regla
                responseMessage = (Message) executeResponse.getResult().getValue("objectIdentifier");

                System.out.println(("respuesta de objeto:" + responseMessage.toString()));

                String AgendaGroupNname = "";
                if (responseMessage.getData().containsKey("AgendaGroupName")) {
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    AgendaGroupNname = responseMessage.getData().get("AgendaGroupName").toString();
                }
                System.out.println(AgendaGroupNname);


            } else {
                System.out.println("Error executing rules. Message: ");
                System.out.println(executeResponse.getMsg());

            }

        } catch (Exception e) {
            System.out.println("Error executing rules. Message: " + e.getMessage());
        }
        return responseMessage;
    }
}

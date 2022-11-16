package ru.softlab.zikova.efr.processes;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.softlab.zikova.efr.exchange.model.Client;

@Component
public class ClientInfoProcess implements JavaDelegate {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        Client client = new Client("Ivan", "Ivanov");

        System.out.println(client);
    }
}

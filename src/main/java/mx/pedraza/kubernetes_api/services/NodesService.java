package mx.pedraza.kubernetes_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.pedraza.kubernetes_api.helpers.ShellHelper;
import mx.pedraza.kubernetes_api.helpers.JsonHelper;

/**
 * Contains logic to get information about the nodes in the Kubernetes cluster.
 */
@Service
public class NodesService {

    @Autowired
    private ShellHelper shellHelper;

    @Autowired
    private JsonHelper jsonHelper;

    /**
     * Gets the number of nodes running in the Kubernetes cluster.
     * This includes the master node and any other worker node.
     * @return An integer specifing the number of nodes running.
     */
    public int getCount() {
        String script = "kubectl get nodes -o json";
        String json = shellHelper.execute(script);
        if (!jsonHelper.isValid(json)) return 0;
        int result = jsonHelper.getArrayLength(json);
        return result;
    }

    /**
     * Gets the CPU and memory metrics of the nodes running in the Kubernetes cluster.
     * @return A string containing the output of 'kubectl top nodes'
     */
    public String getStatus() {
        String script = "kubectl top nodes";
        String result = shellHelper.execute(script);
        return result;
    }
}
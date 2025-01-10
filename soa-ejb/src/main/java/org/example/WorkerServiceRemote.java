package org.example;
import com.spo.entity.dto.*;


import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface WorkerServiceRemote {

    List<Object> getWorkers(GetRequest getRequest);

    List<Object> createWorker(CreateWorkerDTO worker);

    List<Object> getWorker(String workerId);

    List<Object> deleteWorker(String workerId);

    List<Object> patchWorker(int id, EditWorkerDTO worker);

    List<Object> getSalary();

    List<Object> findCountWorkers(FindPersonDTO personDTO);

}

package org.example;

import com.spo.entity.dto.*;
import com.spo.entity.entity.Worker;
import org.example.repository.WorkerRepository;
import org.example.util.WorkerParser;
import jakarta.inject.Inject;
import org.jboss.ejb3.annotation.Pool;

import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
@Pool("soa-ejb")
public class WorkerService implements WorkerServiceRemote {

    @Inject
    WorkerRepository workerRepository;
    @Inject
    WorkerParser workerParser;


    public List<Object> getWorkers(GetRequest getRequest){
        List<Object> out = new ArrayList<>();
        var sortRes = workerParser.parseSorts(getRequest.getSort());
        var filterRes = workerParser.parseFilters(getRequest.getFilter());
        if (sortRes.get(1) != null){
            out.add(null);
            out.add(sortRes.get(1));
            out.add(422);
            return out;
        }
        if (filterRes.get(1) != null){
            out.add(null);
            out.add(filterRes.get(1));
            out.add(422);
            return out;
        }

        getRequest.setSort((List<String>) sortRes.get(0));
        getRequest.setFilter((List<String>) filterRes.get(0));

        return workerRepository.get(getRequest);
    }


    public List<Object> createWorker(CreateWorkerDTO worker){
        var res = workerParser.createWorker(worker);
        List<Object> out = new ArrayList<>();
        if (res.get(1) != null){
            out.add(null);
            out.add(res.get(1));
            out.add(422);
            return out;
        }
        return workerRepository.save((Worker) res.get(0));
    }

    public List<Object> getWorker(String workerId){
        List<Object> out = new ArrayList<>();
        int int_id;
        try{
            int_id = Integer.parseInt(workerId);
        } catch (NumberFormatException e){
            out.add(null);
            out.add("Id should be integer");
            out.add(422);
            return out;
        }
        return workerRepository.getWorker(int_id);
    }

    public List<Object> deleteWorker(String workerId){
        List<Object> out = new ArrayList<>();
        int int_id;
        try{
            int_id = Integer.parseInt(workerId);
        } catch (NumberFormatException e){
            out.add(null);
            out.add("Id should be integer");
            out.add(422);
            return out;
        }
        return workerRepository.deleteWorker(int_id);
    }

    public List<Object> patchWorker(int id, EditWorkerDTO worker){
        var res = workerParser.patchWorker(id, worker);
        List<Object> out = new ArrayList<>();
        if (res.get(1) != null){
            out.add(null);
            out.add(res.get(1));
            out.add(422);
            return out;
        }
        return workerRepository.patchWorker(id, (Worker) res.get(0));
    }

    public List<Object> getSalary(){
        return workerRepository.getSalary();
    }

    public List<Object> findCountWorkers(FindPersonDTO personDTO){
        var res = workerParser.findCountWorkers(personDTO);
        List<Object> out = new ArrayList<>();
        if (res.get(1) != null){
            out.add(null);
            out.add(res.get(1));
            out.add(422);
            return out;
        }
        return workerRepository.findCountWorkers((Map<String, Object>) res.get(0));
    }
}

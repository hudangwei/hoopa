package cn.huimin100.hmsp.market.service;

import cn.huimin100.hmsp.market.dao.*;
import cn.huimin100.hmsp.market.exception.*;
import cn.huimin100.hmsp.market.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorehouseService {

    @Autowired
    private StorehouseDao storehouseDao;

    @Transactional
    public Storehouse addStorehouse(Storehouse storehouse) {
        return storehouseDao.save(storehouse);
    }

    @Transactional
    public Storehouse updateStorehouse(Storehouse storehouse) throws StorehouseNotFound {
        Storehouse storehouseUpdate = storehouseDao.findOne(storehouse.getId());
        if (storehouseUpdate==null){
            throw new StorehouseNotFound();
        }
        /*TODO: need add logic eg:
        if (user.getName()!=null) {
            userUpdate.setName(user.getName());
        }
        if (user.getAge()!=0) {
            userUpdate.setAge(user.getAge());
        }
        */
        storehouseDao.save(storehouseUpdate);
        return storehouseUpdate;
    }

    @Transactional
    public void deleteStorehouseById(Integer id) throws StorehouseNotFound {
        Storehouse storehouseDelete = storehouseDao.findOne(id);
        if (storehouseDelete==null) {
            throw new StorehouseNotFound();
        }
        storehouseDao.delete(id);
    }

    public Storehouse getStorehouseById(Integer id) {
        return storehouseDao.findOne(id);
    }

	public Page<Storehouse> getAllStorehouse(String query, /*String fields, */String sortby,Integer page, Integer pageSize) {
        if (page == null) {
            page = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        PageRequest pageRequest;
        if (sortby != null && sortby.length() > 0) {
            List<Order> orders = new ArrayList<Order>();
            String[] sortFields = sortby.split(",");
            for (String sortField : sortFields) {
                String[] orderbys = sortField.split(":");
                if(orderbys[1].equals("desc")) {
                    orders.add(new Order(Sort.Direction.DESC,orderbys[0]));
                } else if (orderbys[1].equals("asc")) {
                    orders.add(new Order(Sort.Direction.ASC, orderbys[0]));
                }
            }
            pageRequest = new PageRequest(page, pageSize,new Sort(orders));
        }else{
            pageRequest = new PageRequest(page,pageSize);
        }

        return storehouseDao.findAll(new Specification<Storehouse>() {
            @Override
            public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> preList = new ArrayList<Predicate>();
                if (query != null && query.length() > 0) {
                    String[] queryFields = query.split(",");
                    for (String queryField : queryFields) {
                        String[] queryKv = queryField.split(":");
                        preList.add(cb.equal(root.get(queryKv[0]),queryKv[1]));
                    }
                }
                return cq.where(preList.toArray(new Predicate[preList.size()])).getRestriction();
            }
        },pageRequest);
    }
}

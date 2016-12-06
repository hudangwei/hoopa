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
public class SupermarketadminService {

    @Autowired
    private SupermarketadminDao supermarketadminDao;

    @Transactional
    public Supermarketadmin addSupermarketadmin(Supermarketadmin supermarketadmin) {
        return supermarketadminDao.save(supermarketadmin);
    }

    @Transactional
    public Supermarketadmin updateSupermarketadmin(Supermarketadmin supermarketadmin) throws SupermarketadminNotFound {
        Supermarketadmin supermarketadminUpdate = supermarketadminDao.findOne(supermarketadmin.getSid());
        if (supermarketadminUpdate==null){
            throw new SupermarketadminNotFound();
        }
        /*TODO: need add logic eg:
        if (user.getName()!=null) {
            userUpdate.setName(user.getName());
        }
        if (user.getAge()!=0) {
            userUpdate.setAge(user.getAge());
        }
        */
        supermarketadminDao.save(supermarketadminUpdate);
        return supermarketadminUpdate;
    }

    @Transactional
    public void deleteSupermarketadminBySid(Integer id) throws SupermarketadminNotFound {
        Supermarketadmin supermarketadminDelete = supermarketadminDao.findOne(id);
        if (supermarketadminDelete==null) {
            throw new SupermarketadminNotFound();
        }
        supermarketadminDao.delete(id);
    }

    public Supermarketadmin getSupermarketadminBySid(Integer id) {
        return supermarketadminDao.findOne(id);
    }

	public Page<Supermarketadmin> getAllSupermarketadmin(String query, /*String fields, */String sortby,Integer page, Integer pageSize) {
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

        return supermarketadminDao.findAll(new Specification<Supermarketadmin>() {
            @Override
            public Predicate toPredicate(Root<Supermarketadmin> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
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

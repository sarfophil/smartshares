package com.smartshare.dao.implementation;

import com.smartshare.dao.ShareholderDao;
import com.smartshare.dto.Shareholder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ShareholderDaoImpl implements ShareholderDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Shareholder findShareholderByFullname(String firstname, String lastname, String middlename){
        return entityManager.createQuery("Select s from Shareholder s where s.firstname = :fn and s.lastname = :ln" +
                " and s.middlename = :mn",Shareholder.class)
                .setParameter("fn",firstname.toLowerCase())
                .setParameter("ln",lastname.toLowerCase())
                .setParameter("mn",middlename.toLowerCase())
                .getSingleResult();
    }
}

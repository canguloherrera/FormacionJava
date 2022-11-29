package com.backend.client.infraestructure.repository;

import com.backend.client.criteria.ClientPage;
import com.backend.client.criteria.SearchFilterClient;
import com.backend.client.domain.Client;





import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ClientCriteriaRepository {
//      @PersistenceContext
//
//      private final EntityManager entityManager;
//
//    private final CriteriaBuilder criteriaBuilder;
//
//    public ClientCriteriaRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//        this.criteriaBuilder = entityManager.getCriteriaBuilder();
//    }
//
//    public Page<Client> findAllClientWithCriteria(ClientPage clientPage, SearchFilterClient searchFilterClient){
//        CriteriaQuery<Client> criteriaQuery = criteriaBuilder.createQuery(Client.class);
//        Root<Client> clientRoot = criteriaQuery.from(Client.class);
//        Predicate predicate = getPredicate(searchFilterClient,clientRoot);
//        criteriaQuery.where(predicate);
//        setOrder(clientPage,criteriaQuery,clientRoot);
//        TypedQuery<Client> typedQuery = entityManager.createQuery(criteriaQuery);
//        typedQuery.setFirstResult(clientPage.getPageNumber()*clientPage.getPageSize());
//        typedQuery.setMaxResults(clientPage.getPageSize());
//        Pageable pageable = getPageable(clientPage);
//        long personCount = getClientCount(predicate);
//        return new PageImpl<>(typedQuery.getResultList(),pageable,personCount);
//    }
//
//    private Predicate getPredicate(SearchFilterClient searchFilterPersonfilter, Root personRoot) {
//        List<Predicate> predicates = new ArrayList<>();
//        if(Objects.nonNull(searchFilterPersonfilter.getName())){
//            predicates.add(criteriaBuilder.like(personRoot.get("name"),
//                    "%" + searchFilterPersonfilter.getName()+ "%"));
//        }
//        if(Objects.nonNull(searchFilterPersonfilter.getSurname())){
//            predicates.add(criteriaBuilder.like(personRoot.get("surname"),
//                    "%" + searchFilterPersonfilter.getSurname()+ "%"));
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//    }
//
//    private void setOrder(ClientPage clientPage, CriteriaQuery criteriaQuery, Root clientRoot) {
//        if(clientPage.getSortDirection().equals(Sort.Direction.ASC)){
//            criteriaQuery.orderBy(criteriaBuilder.asc(clientRoot.get(clientPage.getSortBy())));
//        }else{
//            criteriaQuery.orderBy(criteriaBuilder.desc(clientRoot.get(clientPage.getSortBy())));
//        }
//    }
//    private Pageable getPageable(ClientPage personPage) {
//        Sort sort = Sort.by(personPage.getSortDirection(),personPage.getSortBy());
//        return PageRequest.of(personPage.getPageNumber(),personPage.getPageSize(),sort);
//    }
//
//    private long getClientCount(Predicate predicate) {
//        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Client> countRoot = countQuery.from(Client.class);
//        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
//        return entityManager.createQuery(countQuery).getSingleResult();
//    }
}

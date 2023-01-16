//package com.parkingLot.repository;
//
//import com.parkingLot.model.ParkingSlot;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.repository.query.FluentQuery;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class ParkingSlotRepositoryImpl implements ParkingSlotRepository{
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//

//
//    @Override
//    public ArrayList<ParkingSlot> getAllAvailableParkingSlots() {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> S insert(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> List<S> insert(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends ParkingSlot> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends ParkingSlot> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends ParkingSlot, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends ParkingSlot> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<ParkingSlot> findById(String s) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(String s) {
//        return false;
//    }
//
//    @Override
//    public List<ParkingSlot> findAll() {
//        return null;
//    }
//
//    @Override
//    public List<ParkingSlot> findAllById(Iterable<String> strings) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(String s) {
//
//    }
//
//    @Override
//    public void delete(ParkingSlot entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends ParkingSlot> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public List<ParkingSlot> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    public Page<ParkingSlot> findAll(Pageable pageable) {
//        return null;
//    }
//}

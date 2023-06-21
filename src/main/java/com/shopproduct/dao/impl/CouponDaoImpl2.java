//package com.nbpshop.dao.impl;
//
//import com.nbpshop.core.BaseDAO;
//import com.nbpshop.dao.CouponDao;
//import com.nbpshop.entity.Coupon;
//
//import java.util.List;
//
//public class CouponDaoImpl2 extends BaseDAO<Coupon> implements CouponDao {
//
//
//    @Override
//    public int insert(Coupon pojo) {
//        String sql = "insert  Coupon (discount,condition_price,deadline,discount_code) values (?,?,?,?);";
//        processOneColumn(sql, pojo.getDiscount(), pojo.getConditionPrice(), pojo.getDeadline(),pojo.getDiscountCode());
//        return 0;
//    }
//
//    @Override
//    public int deleteById(Integer id) {
//        String sql = "delete FROM Coupon where coupon_id=? ;";
//        processOneColumn(sql,id);
//        return 0;
//    }
//
//    @Override
//    public int update(Coupon pojo) {
//        return 0;
//    }
//
//    @Override
//    public Coupon selectById(Integer id) {
//        String sql = "select * from Coupon where coupon_id=?;";
//        return query(sql,id);
//    }
//
//    @Override
//    public List<Coupon> selectAll() {
//        String sql = "select * from Coupon ;";
//        return querySpecificData(sql);
//    }
//
//
//}

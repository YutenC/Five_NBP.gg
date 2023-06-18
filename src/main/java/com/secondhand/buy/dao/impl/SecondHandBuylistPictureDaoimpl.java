package com.secondhand.buy.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.secondhand.buy.dao.SecondHandBuylistPictureDao;
import com.secondhand.buy.vo.SecondHandBuyPicture;


@Repository
public class SecondHandBuylistPictureDaoimpl implements SecondHandBuylistPictureDao{
	
	@PersistenceContext
	private Session session;

	@Override
	public int insert(SecondHandBuyPicture pojo) {
		session.persist(pojo);
		return 1;
	}

	@Override
	public int deleteById(Integer id) {
		SecondHandBuyPicture pic = session.get(SecondHandBuyPicture.class, id);
		session.remove(pic);
		return 1;
	}

	@Override
	public int update(SecondHandBuyPicture pojo) {
		session.update("SecondHandBuyPicture", pojo);
		return 1;
	}

	@Override
	public SecondHandBuyPicture selectById(Integer id) {
		final String sql = "SELECT * FROM Secondhand_Buy_Picture where image_id = :id  ";
		return session.createNativeQuery(sql, SecondHandBuyPicture.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public List<SecondHandBuyPicture> selectAll() {
		final String sql = "SELECT * FROM Secondhand_Buy_Picture ";
		return session.createNativeQuery(sql, SecondHandBuyPicture.class).getResultList();
	}

	@Override
	public List<SecondHandBuyPicture> selectBylistId(Integer id) {
		final String sql = "SELECT * FROM Secondhand_Buy_Picture where buylist_id = :id  ";
		return session
				.createNativeQuery(sql, SecondHandBuyPicture.class)
				.setParameter("id", id)
				.getResultList();
	}

}

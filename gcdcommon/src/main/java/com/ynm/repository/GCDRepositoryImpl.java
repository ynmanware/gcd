package com.ynm.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ynm.model.Parameters;
import com.ynm.repository.domain.GCD;
import com.ynm.repository.mappers.GCDMapper;

/**
 * @author Yogesh.Manware
 *
 */
@Service
public class GCDRepositoryImpl implements GCDRepository {

	SqlSession sqlSession;

	public GCDRepositoryImpl() {
	}

	public static void main(String[] args) {

		GCD gcd = new GCD();
		gcd.setParam1(90);
		gcd.setParam2(80);
		gcd.setApiKey("29ecd369-3f0d-4153-bdc6-fdc89cda4e0f");
		gcd.setResult(gcd(90, 80));

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();

		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			gcdMapper.updateGCDResult(gcd);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	/**
	 * @param value1
	 * @param value2
	 * @return
	 */
	private static int gcd(int value1, int value2) {
		while (value2 != 0) {
			int temp = value1;
			value1 = value2;
			value2 = temp % value2;
		}

		return Math.abs(value1);
	}

	@Override
	public void persistParameters(Parameters params, String key) {
		GCD gcd = new GCD();
		gcd.setParam1(params.getParam1());
		gcd.setParam2(params.getParam2());
		gcd.setApiKey(key);

		sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			gcdMapper.insertParameters(gcd);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		List<Parameters> params = new ArrayList<>();
		sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			params = gcdMapper.getParametersByApiKey(key);

		} finally {
			sqlSession.close();
		}
		return params;
	}

	@Override
	public void updateGCDResult(GCD gcd) {

		sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			gcdMapper.updateGCDResult(gcd);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public List<GCD> getGCDResults(String key) {
		sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			return gcdMapper.getGCDByApiKey(key);
		} finally {
			sqlSession.close();
		}

	}

	@Override
	public void deleteGCD(String apiKey) {
		sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			GCDMapper gcdMapper = sqlSession.getMapper(GCDMapper.class);
			gcdMapper.deleteGCD(apiKey);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}
}

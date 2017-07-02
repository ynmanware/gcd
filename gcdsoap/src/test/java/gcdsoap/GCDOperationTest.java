package gcdsoap;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import javax.jms.JMSException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ynm.GCDServiceImpl;
import com.ynm.messaging.MessageQueueHandler;
import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;
import com.ynm.repository.GCDRepositoryImpl;
import com.ynm.repository.domain.GCD;

public class GCDOperationTest {

	private static Logger logger = LoggerFactory
			.getLogger(GCDOperationTest.class);

	static GCDServiceImpl gcdservice = new GCDServiceImpl();

	static GCDRepository gcdRepository = new GCDRepositoryImpl();

	static MessageQueueHandler mq = new MessageQueueHandler();

	private static String TEST_QUEUE = "TEST_QUEUE";

	@BeforeClass
	public static void setup() {
		gcdservice.setMessageQueueHandler(mq);
		gcdservice.setGcdRepository(new GCDRepositoryImpl());
		gcdRepository.deleteGCD(TEST_QUEUE);
	}

	@Before
	@AfterClass
	public static void clearTable() {
		gcdRepository.deleteGCD(TEST_QUEUE);
	}

	@Test
	public void testGCD() throws JMSException, IOException {
		Parameters params = new Parameters();
		params.setParam1(10);
		params.setParam2(20);
		mq.queueParameters(params, TEST_QUEUE);
		gcdRepository.persistParameters(params, TEST_QUEUE);
		int d = gcdservice.gcd(TEST_QUEUE);
		assertTrue(d == 10);
		logger.info("GCD RESULT: " + d);
	}

	@Test
	public void testGCDList() throws JMSException, IOException {

		Parameters params = new Parameters();
		params.setParam1(14);
		params.setParam2(20);
		mq.queueParameters(params, "TEST_QUEUE");
		gcdRepository.persistParameters(params, TEST_QUEUE);

		params = new Parameters();
		params.setParam1(3);
		params.setParam2(9);
		mq.queueParameters(params, "TEST_QUEUE");
		gcdRepository.persistParameters(params, TEST_QUEUE);

		params = new Parameters();
		params.setParam1(44);
		params.setParam2(11);
		mq.queueParameters(params, "TEST_QUEUE");
		gcdRepository.persistParameters(params, TEST_QUEUE);

		logger.info("----------->");

		int d = gcdservice.gcd(TEST_QUEUE);
		logger.info(String.valueOf(d));
		d = gcdservice.gcd(TEST_QUEUE);
		logger.info(String.valueOf(d));
		d = gcdservice.gcd(TEST_QUEUE);
		logger.info(String.valueOf(d));
		logger.info("<-----------");
		List<GCD> f = gcdservice.gcdList(TEST_QUEUE);
		assertTrue(f.get(0).getResult() == 2);
		assertTrue(f.get(1).getResult() == 3);
		assertTrue(f.get(2).getResult() == 11);
		logger.info(f.toString());
		int sum = gcdservice.gcdSum(TEST_QUEUE);
		logger.info("Sum:" + sum);
	}
}

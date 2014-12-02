package cms.crontab;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qianqian.cms.model.MapAdLocationEntity;
import com.qianqian.cms.service.IMapAdTaskService;


public class TestMapAd {

	@SuppressWarnings("resource")
	public static void test1() throws Exception{
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/spring/applicationContext.xml");
		IMapAdTaskService service = (IMapAdTaskService) ctx
				.getBean("mapAdTaskService");
		MapAdLocationEntity mapAd = new MapAdLocationEntity();
		mapAd.setId(101L);
		mapAd.setLocationId(101L);
		mapAd.setLocationType(5);
		//mapAd.setStartTime(DateUtil.addMilliseconds(new Date(), 1));
		mapAd.setStartTime(DateUtils.addSeconds(new Date(), 10));
		mapAd.setEndTime(DateUtils.addSeconds(new Date(), 20));
		service.createMapAdTask(mapAd);
	}
	public static void main(String[] args) {
		try {
			test1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

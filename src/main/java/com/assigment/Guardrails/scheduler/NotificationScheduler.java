package com.assigment.Guardrails.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationScheduler {
	@Autowired
	private RedisTemplate<String,Object> rt;
	
	@Scheduled(fixedRate=30000)
	public void chechVirality() {
		for(int i=1;i<=100;i++) {
			String key="post:"+i+":virality_score";
			
			Object val=rt.opsForValue().get(key);
			
			if(val!=null) {
				int sc=Integer.parseInt(val.toString());
				
				if(sc>=100) {
					String not="post:"+ i +" is trending with score " + sc;
					String sk="notification_sent:"+i;
					Boolean as=rt.hasKey(sk);
					if(Boolean.FALSE.equals(as)){
						rt.opsForList().rightPush("notifications",not);
						rt.opsForValue().set(sk,"true");
						System.out.println(not);
					}
				}
			}
		}
	}

}

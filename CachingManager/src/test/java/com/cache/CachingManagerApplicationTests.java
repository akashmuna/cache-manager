package com.cache;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cache.model.Headline;
import com.cache.service.HeadlineServiceTT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CachingManagerApplicationTests {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CachingManagerApplicationTests.class);
	
	private static final String CNN_CHANNEL_SOURCE =  "cnn";
	private static final String GOOGLE_CHANNEL_SOURCE = "google-news"; 
	private static final String API_KEY = "8fe0a96abf4f40cf91f943be9b20903b";
	private static final String REST_URL = "https://newsapi.org/v2/top-headlines?apiKey=";
	
	@Autowired
	private HeadlineServiceTT headlineServiceTT;
	
	@Test
	public void cnnNewsResponseTest() {
		
		final String urlToConsume = REST_URL + API_KEY + "&sources=" + CNN_CHANNEL_SOURCE;
		LOGGER.info("Channel Source :  "+ CNN_CHANNEL_SOURCE);
		
		List<Headline> headlineList = headlineServiceTT.getCNNLatestNews(urlToConsume);
		
		headlineList.forEach(headline->{LOGGER.info("News Title : "+headline.getTitle());
			LOGGER.info("News URL : "+headline.getUrl());
		});
	}

}

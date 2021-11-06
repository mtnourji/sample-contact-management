package com.innovaitagence.contactmanagement;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwaggerGenerator {

	@Autowired
	private WebApplicationContext context;

	@Value("${swagger.target}")
	private String target;

	@Test
	public void generateSwagger() throws Exception {
		final MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/api-docs").accept(MediaType.ALL))
				.andDo((result) -> FileUtils.writeStringToFile(
						new File(target + "swagger.json"),
						result.getResponse().getContentAsString(),
						Charset.defaultCharset()
				));
	}

}

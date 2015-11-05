package br.com.soaresdeandrade.advocacia.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ActiveProfiles("test")
@WebAppConfiguration
@ContextConfiguration(classes = {
        ApplicationConfig.class,
        EmbeddedDataSourceConfig.class,
        JpaConfig.class,
        WebMvcConfig.class
})
@RunWith(SpringJUnit4ClassRunner.class)

public abstract class TestePadrao {



}

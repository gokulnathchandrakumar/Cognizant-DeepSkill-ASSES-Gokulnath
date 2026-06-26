package com.library;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 * MavenConfigTest
 * Verifies that all Maven dependencies are correctly
 * downloaded and available at compile + test time
 */
public class MavenConfigTest {

    @Test
    public void testSpringContextDependency() {
        System.out.println("--- Test 1: Spring Context Available ---");
        // If this class is found, spring-context dependency works
        assertNotNull(
            "Spring ApplicationContext should be loadable",
            ApplicationContext.class.getName()
        );
        System.out.println("PASS: spring-context dependency working");
    }

    @Test
    public void testSpringAopDependency() {
        System.out.println("--- Test 2: Spring AOP Available ---");
        try {
            // Try to load an AOP class
            Class.forName(
                "org.springframework.aop.framework.ProxyFactory"
            );
            System.out.println("PASS: spring-aop dependency working");
        } catch (ClassNotFoundException e) {
            fail("Spring AOP not available: " + e.getMessage());
        }
    }

    @Test
    public void testSpringWebMvcDependency() {
        System.out.println("--- Test 3: Spring WebMVC Available ---");
        try {
            Class.forName(
                "org.springframework.web.servlet.DispatcherServlet"
            );
            System.out.println("PASS: spring-webmvc dependency working");
        } catch (ClassNotFoundException e) {
            fail("Spring WebMVC not available: " + e.getMessage());
        }
    }

    @Test
    public void testApplicationContextLoads() {
        System.out.println("--- Test 4: ApplicationContext Loads ---");
        ApplicationContext context =
            new ClassPathXmlApplicationContext(
                "applicationContext.xml"
            );
        assertNotNull("Spring context should not be null", context);
        System.out.println("PASS: ApplicationContext loaded successfully");
        ((ClassPathXmlApplicationContext) context).close();
    }

    @Test
    public void testJavaVersion() {
        System.out.println("--- Test 5: Java Version Check ---");
        String version = System.getProperty("java.version");
        System.out.println("Running on Java: " + version);
        assertNotNull("Java version should be available", version);
        System.out.println("PASS: Java version confirmed");
    }
}

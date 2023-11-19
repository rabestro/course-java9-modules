module test.app {
    requires spring.webmvc;
    requires spring.boot.autoconfigure;
    requires spring.boot;

	opens com.epam.testapp;
}
